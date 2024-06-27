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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * A utility class that manages data operations for the application.
 * This class is responsible for loading data from the database based on given filters
 * and providing this data as an observable list for GUI components.
 */
public class UtilityClass {
    private static final ObservableList<App> nameAndRatingObservLis = FXCollections.observableArrayList();

    // Private constructor to prevent instantiation
    private UtilityClass() {
    }

    /**
            * Returns the observable list containing App nameAndRatingObservList.
     * @return an observable list of App objects
     */
    public static ObservableList<App> getData() {
        return nameAndRatingObservLis;
    }

    /**
     * Loads data from the database using a specified filter.
     * @param filterLabel A string label defining the filter to apply on query results.
     */
    static void loadAppData(String filterLabel) {
        nameAndRatingObservLis.clear();
        String sqlQuery = buildSqlQuery(filterLabel);
        executeQuery(sqlQuery);
    }

    /**
     * Executes a SQL query to fetch app data and populates the observable list.
     * @param sqlQueryPassed The SQL query to execute.
     */
    private static void executeQuery(String sqlQueryPassed) {
        try (Connection dataBaseConn = DatabaseConnection.connect();
             Statement stmt = dataBaseConn.createStatement();
             ResultSet appResultSet = stmt.executeQuery(sqlQueryPassed)) {
            int count = 0;
            while (appResultSet.next()) {
                count++;
                nameAndRatingObservLis.add(new App(appResultSet.getString("appName"), appResultSet.getDouble("rating")));
            }
            LogUtility.info("Apps loaded: {0}", count);
        } catch (SQLException ex) {
            ExceptionAlert.showSqlQueryAlert();
            LogUtility.error("Error occurred while querying data base.");
        }
    }

    /**
     * Builds an SQL query based on the specified filter.
     * @param filterLabel The filter condition label.
     * @return An SQL query .
     */
    private static String buildSqlQuery(String filterLabel) {
        String whereClause = switch (filterLabel) {
            case "All" -> "";
            case "Rating < 1" -> " WHERE rating < 1";
            case "Rating = 1" -> " WHERE rating >= 1 AND rating < 2";
            case "Rating = 2" -> " WHERE rating >= 2 AND rating < 3";
            case "Rating = 3" -> " WHERE rating >= 3 AND rating < 4";
            case "Rating = 4" -> " WHERE rating >= 4 AND rating < 5";
            case "Rating = 5" -> " WHERE rating = 5";
            default -> {
                LogUtility.error("Unexpected filter label received: " + filterLabel);
                throw new IllegalArgumentException("Invalid filter label: " + filterLabel);
            }
        };
        String sqlStatement = "SELECT * FROM apps" + whereClause;
        LogUtility.info("Built SQL statement: " + sqlStatement);

        return sqlStatement;
    }
}
