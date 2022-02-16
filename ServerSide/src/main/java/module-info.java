module ServerSide {  // change this to gov.iti.jets.project if needed
    requires javafx.controls;
    requires javafx.fxml;
    //requires validatorfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    //requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;

    opens gov.iti.jets.project to javafx.fxml;
    exports gov.iti.jets.project;
    exports gov.iti.jets.project.presentation.controllers;
    opens gov.iti.jets.project.presentation.controllers to javafx.fxml;
}
