module dtcc.itn261.assignment_12_refactor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.logging;
    requires java.sql;

    opens dtcc.itn261.assignment_12_refactor to javafx.fxml;
    exports dtcc.itn261.assignment_12_refactor;
}