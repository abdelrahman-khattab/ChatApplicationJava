module ClientSide {

    requires javafx.controls;
    requires javafx.fxml;
    //requires validatorfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    requires java.rmi;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.apache.commons.io;
    requires java.sql.rowset;
    requires java.desktop;
    requires chatter.bot.api;

    opens org.iti.project to javafx.fxml;
    opens org.iti.project.presentation.controllers to  javafx.fxml;
    exports org.iti.project;
    exports org.iti.project.presentation.controllers;
    exports org.iti.project.services.interfaces;
    exports  org.iti.project.models;

}
