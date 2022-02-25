package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


    public ObservableList<User> contactsObservableList;
    public ObservableList<Group> groupsObservableList;
    byte[] userImg;

    //Add group name here...............................
    @FXML
    void addGroup(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactsObservableList = FXCollections.observableArrayList();
        groupsObservableList = FXCollections.observableArrayList();

        File file=new FileChooser().showOpenDialog(null);
        userImg= ImageConverter.fromImageToBytes(file.getPath());

        contactsObservableList.addAll(
                new User("Eima Ross",userImg),
                new User("Terabithia ",userImg)
        );

        groupsObservableList.addAll(
                new Group("Eima Ross",userImg),
                new Group("Terabithia ",userImg)
        );

        memberLV.setItems(contactsObservableList);
        memberLV.setCellFactory(groupListView -> new AddContactsWithGroupListCell());
        memberLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {


            }

        });

        groupsLv.setItems(groupsObservableList);
        groupsLv.setCellFactory(groupListView -> new AddGroupWithContactListCell());
        groupsLv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {

            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {


            }

        });



    }

}

