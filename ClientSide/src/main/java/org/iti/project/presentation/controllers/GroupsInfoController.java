package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import org.iti.project.presentation.models.Group;

import java.io.IOException;

public class GroupsInfoController extends ListCell<Group> {

    @FXML
    private HBox contactInfoHBox;

    @FXML
    private Circle groupImage;

    @FXML
    private Label GroupDescription;

    @FXML
    private Label groupMesaageNumbers;

    @FXML
    private Label groupMessageTime;

    @FXML
    private Label groupName;

    public FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Group group, boolean empty) {
        super.updateItem(group, empty);

        if (empty || group == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/groupsInfoListCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            groupName.setText(group.getGroupName());
            GroupDescription.setText(group.getDescription());
            setGraphic(contactInfoHBox);
        }
    }

}
