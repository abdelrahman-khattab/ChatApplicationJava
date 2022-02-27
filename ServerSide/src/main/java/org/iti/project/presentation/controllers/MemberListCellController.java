package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.IOException;

public class MemberListCellController extends ListCell<User> {

    @FXML
    private Button editMember;

    @FXML
    private HBox memberHBox;

    @FXML
    private Circle memberImage;

    @FXML
    private Label memberLocation;

    @FXML
    private Label memberName;

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
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/memberListCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userImage= ImageConverter.fromBytesToImage(user.getImage());
            memberName.setText(user.getUserName());
            memberImage.setFill(new ImagePattern(userImage));
            setGraphic(memberHBox);
        }
    }


}
