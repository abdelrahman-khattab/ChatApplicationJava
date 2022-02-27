package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.iti.project.models.Group;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.IOException;

public class AddGroupWithContactListCell extends ListCell<Group> {
    @FXML
    private HBox contactInfoHBox;

    @FXML
    private Circle groupImage;

    @FXML
    private Label groupName;

    public FXMLLoader fxmlLoader;
    Image userImage;
    String userStatus;

    @Override
    protected void updateItem(Group group, boolean empty) {
        super.updateItem(group, empty);
        if (empty || group == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/addGroupWithContact.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userImage= ImageConverter.fromBytesToImage(group.getGroupImageBytes());
            groupName.setText(group.getGroupName());
            groupImage.setFill(new ImagePattern(userImage));

            setGraphic(contactInfoHBox);
        }
    }

}
