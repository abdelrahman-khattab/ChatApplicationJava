package org.iti.project.presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.iti.project.models.Group;
import org.iti.project.util.ImageConverter;

import java.io.IOException;

public class ServerAnnouncementsListCell extends ListCell<String> {
    @FXML
    private HBox contactInfoHBox;

    @FXML
    private Label serverMessageTime;

    @FXML
    private Label message;

    public FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(String serverMessage, boolean empty) {
        super.updateItem(serverMessage, empty);

        if (empty || serverMessage == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/announcementsListCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("hello");
            message.setText(serverMessage);
            System.out.println("hello again");
            setGraphic(contactInfoHBox);
        }
    }



}

