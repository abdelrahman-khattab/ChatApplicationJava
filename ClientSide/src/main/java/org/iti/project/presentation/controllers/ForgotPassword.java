package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ForgotPassword {
    @FXML
    private TextField eMail;

    @FXML
    private TextField phoneNo;

    @FXML
    private VBox vBox;

    @FXML
    void loginVbox()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        vBox.getChildren().removeAll();
        try {
            vBox.getChildren().setAll((VBox)fxmlLoader.load(getClass().getResource("/view/login.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
