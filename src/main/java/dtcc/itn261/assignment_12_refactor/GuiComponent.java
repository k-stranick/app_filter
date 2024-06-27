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

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * GuiComponent class is responsible for creating and managing the graphical user interface (GUI) for the application.
 * It sets up the main layout, table for displaying data, and controls for filtering the data.
 *
 */
public class GuiComponent {
    private final TableView<App> table;
    private final Stage primaryStage;

    /**
     * Constructor for GuiComponent. Initializes the GUI components and sets up the layout.
     *
     * @param primaryStage The primary stage for the application.
     */
    public GuiComponent(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.table = new TableView<>();
        this.table.setItems(UtilityClass.getData());
        setupLayout(primaryStage);
        setupTable();

    }

    /**
     * Sets up the main layout of the GUI.
     *
     * @param primaryStage The primary stage for the application.
     */
    public void setupLayout(Stage primaryStage) {
        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(table);
        Scene scene = new Scene(rootPane, 400, 400);
        primaryStage.setScene(scene);
        setupControls(rootPane);
        this.primaryStage.show();
    }

    /**
     * Initializes the table for displaying data.
     * This method sets up the columns for the application's name and rating, with predefined widths.
     */
    private void setupTable() {
        TableColumn<App, String> nameCol = new TableColumn<>("App Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("appName"));
        table.getColumns().add(nameCol);
        nameCol.setPrefWidth(300);

        TableColumn<App, Double> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        table.getColumns().add(ratingCol);
        ratingCol.setPrefWidth(100);
    }

    /**
     * Configures the controls for the GUI, including a ComboBox for filtering and a clear button.
     *
     * @param rootPane The root pane of the application to which the controls will be added.
     */
    private void setupControls(BorderPane rootPane) {
        // Setup ComboBox for filtering
        ComboBox<String> filterLabel= new ComboBox<>();
        filterLabel.getItems().addAll(
                "All", "Rating < 1",
                "Rating = 1",
                "Rating = 2",
                "Rating = 3",
                "Rating = 4",
                "Rating = 5");
        filterLabel.setValue("ALL"); // Set default value
        filterLabel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LogUtility.info("Filter changed to: " + newValue);
            UtilityClass.loadAppData(newValue);  // Load data based on the new filter
        });

        Button clearButton = new Button("Clear Filter");
        clearButton.setOnAction(e ->
                filterLabel.getSelectionModel().clearAndSelect(0)
        );

        HBox controls = new HBox(10, filterLabel, clearButton);
        controls.setAlignment(Pos.CENTER);
        rootPane.setTop(controls);
    }
}
