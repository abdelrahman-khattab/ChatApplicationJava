package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class SideContactListController {
    
    @FXML
    public Circle contactImage;

    @FXML
    public HBox contactInfoHBox;

    @FXML
    public Label contactName;

    @FXML
    public Label contactNumber;

    @FXML
    public TextField searchBar;

    private static SideContactListController sideContactListController;

    public static void setController(SideContactListController sideContactListController){
        SideContactListController.sideContactListController = sideContactListController;
    }

    public static SideContactListController getInstance() {
        return sideContactListController;
    }


    @FXML
    void test(MouseEvent event) {

    }
}
