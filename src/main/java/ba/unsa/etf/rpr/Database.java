package ba.unsa.etf.rpr;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {
    private static String url;
    private static String user;
    private static String password;

    private Database() {}
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            File f = new File("C:/Users/Emir/Desktop/baza.txt");
            Scanner scanner = new Scanner(f);
            url = scanner.nextLine();
            user = scanner.nextLine();
            password = scanner.nextLine();
            scanner.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Došlo je do nekog problema");
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

}
