package dtcc.itn261.assignment_12_refactor;

import javafx.scene.control.Alert;

// logic for Alert class
public class ExceptionAlert {
    static final String ERROR = "Error";
    public static void showFileNotFoundAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File ERROR");
        alert.setHeaderText("File Not Found");
        alert.setContentText("The file you are trying to access does not exist.");
        alert.showAndWait();
    }

    public static void showIOAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("IO Error");
        alert.setHeaderText("ERROR");
        alert.setContentText("An error occurred while creating the log file");
        alert.showAndWait();
    }

    public static void showSqlQueryAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SQL Exception ");
        alert.setHeaderText("ERROR");
        alert.setContentText("An error occurred while try to query the database.");
        alert.showAndWait();
    }
    public static void showSqlConnectionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SQL Exception ");
        alert.setHeaderText(ERROR);
        alert.setContentText("An error occurred while try to access the database.");
        alert.showAndWait();
    }
}
