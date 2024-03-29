package ba.unsa.etf.rpr.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;



public class Database {
    private static String url;
    private static String user;
    private static String password;


    private Database() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try (InputStream input = new FileInputStream("src/main/resources/login.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception io) {
            io.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet result) {
        try {
            if (result != null) result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
