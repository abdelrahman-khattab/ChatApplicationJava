package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.IOException;

public class ContactsChatInfoListCellController extends ListCell<User> {

    @FXML
    private Circle contactImageContactBar;

    @FXML
    private HBox contactInfoHBox;

    @FXML
    private Label contactMessageContactBar;

    @FXML
    private Label contactNameContactBar;

    @FXML
    private Circle contactStatusContactBar;

    @FXML
    private Label mesaageNumbersContactBar;

    @FXML
    private Label messageTimeContactBar;

    public FXMLLoader fxmlLoader;
    Image userImage;
    String userStatus;

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/contactsChatInfo.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userImage= ImageConverter.fromBytesToImage(user.getImage());
            contactNameContactBar.setText(user.getUserName());
            contactImageContactBar.setFill(new ImagePattern(userImage));
            mesaageNumbersContactBar.setText(String.valueOf(user.getUnsavedMessageCount()));
            userStatus=user.getStatus();
//            if(userStatus.equalsIgnoreCase("online"))
//                contactStatusContactBar.setFill(Color.rgb(39,93,16));
//            if(userStatus.equalsIgnoreCase("offline"))
//                contactStatusContactBar.setFill(Color.rgb(128,125,125));
//


            setGraphic(contactInfoHBox);
        }
    }

}

