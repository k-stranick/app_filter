/**==================================================
 Author: Kyle Stranick
 Class: ITN261
 Class Section: 201
 Assignment: Assignment 12 - App Store - Databases, JavaFX
 Notes:
split logging between console and log file
 prepared statements
 =====================================================

 Code adapted from: see resources file

 ===================================================== */
package dtcc.itn261.assignment_12_refactor;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point for the application.
 * This class extends the JavaFX Application class and overrides the start method to initialize the GUI and load data.
 *
 */
public class AppStoreMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the GUI and loads data.
     * This method is overridden from the JavaFX Application class.
     * It creates a new instance of the GuiComponent class and sets up its layout.
     * Then it calls the loadData method of the UtilityClass class with the argument "All".
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Google App Viewer");
        GuiComponent userInterface = new GuiComponent(primaryStage);
        userInterface.setupLayout(primaryStage);
        UtilityClass.loadAppData("All");
    }
}