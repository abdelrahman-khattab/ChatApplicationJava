package org.iti.project.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.iti.project.models.Group;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddGroupAndGroupMembers implements Initializable {

    @FXML
    private Button addGroupBtn;

    @FXML
    private Button addMembers;

    @FXML
    private ListView<Group> groupsLv;

    @FXML
    private ListView<User> memberLV;

    @FXML
    private TextField newGroupName;

    List<Group> groupObservableList;
    byte[] user1Img;
    @FXML
    void addGroup(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupsLv.setCellFactory(listViewListCellCallback ->new ListCell<>(){
            @Override
            protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
                if (group != null) {
                    setText(group.getGroupName());
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        groupObservableList = FXCollections.observableArrayList();
        //get Image

//
//        groupObservableList.addAll(
//                new Group("Iti Group"),
//                new Group("Dark Life")
//        );


        if (groupObservableList != null) {
            groupsLv.setItems(FXCollections.observableList(groupObservableList));
        }


    }

}

