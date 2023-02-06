package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */

public abstract class  AbstractDAO<T extends Idable> implements DAO<T> {
    private static Connection connection;
    private String tableName;

    /**
     * Instantiates a new Abstract dao.
     *
     * @param tableName stands for the name of the table
     */

    public AbstractDAO(String tableName) {
        this.tableName = tableName;
        if(connection == null) createConnection();
    }

    /**
     * Creates the connection with the database through the data stored in .properties file
     */
    private static void createConnection() {
        if(AbstractDAO.connection == null) {
            try (InputStream input = new FileInputStream("login.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");
                AbstractDAO.connection = DriverManager.getConnection(url,user,password);
            }  catch (Exception io) {
                io.printStackTrace();
                System.exit(0);
            }
        }
    }

    /**
     * Gets the connection
     *
     * @return the connection to the database
     */
    public Connection getConnection() {return AbstractDAO.connection;}


    /**
     * Closes the connection to the database
     */
    public static void closeConnection() {
        System.out.println("Pozvana metoda za zatvaranje konekcije");
        if (connection != null) {
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on database");
            }
        }
    }


    /**
     * Method for mapping ResultSet into Object
     *
     * @param rs - result set from the database
     * @return - returns a Bean object for specific table
     * @throws KarteException in case of error with the database
     */
    public abstract T row2object(ResultSet rs) throws KarteException;


    /**
     * Method for mapping Object into Map
     *
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);


    /**
     * Utility method for executing any kind of query
     *
     * @param upit - SQL query
     * @param params - parameters for the query
     * @return List of objects from the database
     * @throws KarteException in case of an error with the database
     */
    public List<T> executeQuery(String upit, Object[] params) throws KarteException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(upit);
            if (params != null) {
                for (int i = 1; i<= params.length; i++) stmt.setObject(i,params[i-1]);
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    /**
     * Utility method for query execution that always returns single record
     *
     * @param upit - query that returns single record
     * @param params - list of parameters for the sql query
     * @return Object t
     * @throws KarteException in case when the object is not found
     */
    public T executeQueryUnique(String upit, Object[] params) throws KarteException {
        List<T> result = executeQuery(upit,params);
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            throw new KarteException("Object not found");
        }
    }


    /**
     * Accepts KV storage of colomuns names and returns CSV of columns and question marks for insert statement
     *
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String,String> prepareInsertParts(Map<String,Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();
        int counter = 0;
        for (Map.Entry<String,Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     *
     * @param row - row to be converted into string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

    public T getById(int id) throws KarteException{
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id = ?", new Object[]{id});
    }
    public List<T> getAll() throws KarteException {
        return executeQuery("SELECT * FROM "+ tableName, null);
    }

    public void delete(int id) throws KarteException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new KarteException(e.getMessage(), e);
        }
    }
    public T add(T item) throws KarteException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

            return item;
        }catch (SQLException e){
            throw new KarteException(e.getMessage(), e);
        }
    }
    public T update(T item) throws KarteException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new KarteException(e.getMessage(), e);
        }
    }
}
