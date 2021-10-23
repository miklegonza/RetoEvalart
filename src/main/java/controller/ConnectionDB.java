package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static final String DB = "evalart_reto";
    private static Connection connection = null;
    private final String HOST;
    private final String PORT;
    private final String USER;
    private final String PASS;

    private ConnectionDB() {
        this.HOST = "localhost";
        this.PORT = "3306";
        this.USER = "root";
        this.PASS = "password";
        this.connect();
    }

    public static Connection getConnection() {
        if (connection == null)
            new ConnectionDB();
        return connection;
    }

    private Connection connect() {
        try {
            String path = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;
            connection = DriverManager.getConnection(path, USER, PASS);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection error");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return connection;
    }

    public static void disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException ex) {
            System.out.println("Disconnection error");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        connection = null;
    }

}
