package ba.unsa.etf.rpr;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Database {
    private static String url;
    private static String user;
    private static String password;


    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            File f = new File("C:/Users/Emir/Desktop/properties.txt");
            Scanner scanner = new Scanner(f);
            url = scanner.nextLine();
            user = scanner.nextLine();
            password = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Došlo je do nekog problema");
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, user, password);
        return connection;
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
