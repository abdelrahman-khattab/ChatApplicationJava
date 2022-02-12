package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

public class SideChatListController {
    @FXML
    public Circle userStatusStatusBar;

    @FXML
    public Circle contactImageContactBar;

    @FXML
    public Circle contactImageStatusBar;

    @FXML
    public Label contactMessageContactBar;

    @FXML
    public Label contactNameContactBar;

    @FXML
    public Label contactNameStatusBar;

    @FXML
    public Circle contactStatusContactBar;

    @FXML
    public Label mesaageNumbersContactBar;

    @FXML
    public Label messageTimeContactBar;

    @FXML
    public TextField searchBar;

    @FXML
    public VBox contactStatusVBox;
    @FXML
    public HBox contactInfoHBox;

    public void initialize() {
        // ImagePattern pattern = new ImagePattern(new Image("target/classes/view/images/img.png"));
        // contactImageStatusBar.setFill(pattern);
        // contactImageContactBar.setFill(pattern);

    }
   

}
