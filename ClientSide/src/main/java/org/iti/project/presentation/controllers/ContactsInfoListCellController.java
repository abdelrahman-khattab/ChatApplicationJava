package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.IOException;

public class ContactsInfoListCellController extends ListCell<User> {

    @FXML
    private Circle contactImage;

    @FXML
    private HBox contactInfoHBox;

    @FXML
    private Label contactName;

    @FXML
    private Label contactNumber;

    public FXMLLoader fxmlLoader;
    Image userImage;

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/contactsListInfo.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userImage= ImageConverter.fromBytesToImage(user.getImage());
            contactName.setText(user.getUserName());
            contactImage.setFill(new ImagePattern(userImage));
            contactNumber.setText(user.getUserPhone());

            setGraphic(contactInfoHBox);
        }
    }

}

