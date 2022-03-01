package org.iti.project.presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import org.iti.project.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.iti.project.util.ImageConverter;

import java.io.IOException;


public class UserStatusListCell extends ListCell<User> {
    @FXML
    private Circle contactImageStatusBar;

    @FXML
    private Label contactNameStatusBar;

    @FXML
    private VBox contactStatusVBox;

    @FXML
    private Circle userStatusStatusBar;
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
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/chatHeaderOnlinerUserListCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userImage= ImageConverter.fromBytesToImage(user.getImage());
            contactNameStatusBar.setText(user.getUserName().split(" ")[0]);
            contactImageStatusBar.setFill(new ImagePattern(userImage));
            userStatusStatusBar.setFill(Color.rgb(39,93,16));
            userStatus=user.getStatus();
//            if(userStatus.equalsIgnoreCase("online"))
//                userStatusStatusBar.setFill(Color.rgb(39,93,16));
//            if(userStatus.equalsIgnoreCase("offline"))
//                userStatusStatusBar.setFill(Color.rgb(128,125,125));
//


            setGraphic(contactStatusVBox);
        }
    }

}
