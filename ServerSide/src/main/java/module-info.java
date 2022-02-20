module ServerSide {  // change this to gov.iti.jets.project if needed

    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires javafx.controls;
    //requires validatorfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    requires java.rmi;
    requires java.sql;
    requires org.apache.commons.io;
    requires com.zaxxer.hikari;
    requires mysql.connector.java;
    requires java.sql.rowset;


    opens org.iti.project to javafx.fxml;
    opens org.iti.project.presentation.controllers to javafx.fxml;
    exports org.iti.project;
    exports org.iti.project.presentation.controllers;
    exports org.iti.project.services.interfaces;
}
