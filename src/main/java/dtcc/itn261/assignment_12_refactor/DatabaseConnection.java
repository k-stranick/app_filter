/**==================================================
 Author: Kyle Stranick
 Class: ITN261
 Class Section: 201
 Assignment: Assignment 12 - App Store - Databases, JavaFX
 Notes:
 split logging between console and log file
 =====================================================

 Code adapted from: see resources file

 ===================================================== */
package dtcc.itn261.assignment_12_refactor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class provides a static method to establish a connection to a MySQL database.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mobile_apps";
    private static final String USER = "YOUR_USERNAME"; // here
    private static final String PASSWORD = "YOUR_PASSWORD"; // here

    /**
     * Private constructor to prevent instantiation of the DatabaseConnection class.
     */
    private DatabaseConnection() {
    }

    /**
     * Connects to the specified MySQL database using the provided username and password.
     * Common practice to ensure that a class can only be instantiated within the same class or by a subclass.
     * By making the constructor private, it ensures that the class can only be used through its static methods,
     * such as the connect() method.
     * @return A Connection object representing the established connection to the database, or null if an error occurs.
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            ExceptionAlert.showSqlConnectionAlert();
            LogUtility.error("Error connecting to the database");
        }

        return null;
    }
}

