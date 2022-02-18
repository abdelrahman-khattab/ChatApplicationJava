module ClientSide {

    requires javafx.controls;
    requires javafx.fxml;
    //requires validatorfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    requires java.rmi;

    opens org.iti.project to javafx.fxml;
    opens org.iti.project.presentation.controllers to  javafx.fxml;
    exports org.iti.project;
    exports org.iti.project.presentation.controllers;

}
