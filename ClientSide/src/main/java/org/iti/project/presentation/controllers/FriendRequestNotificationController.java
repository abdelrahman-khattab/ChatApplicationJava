package org.iti.project.presentation.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.io.IOException;

public class FriendRequestNotificationController {

    @FXML
    private ListView<User> requestLV;
    public ObservableList<User> contactObservableList;
    byte[] user1;
    public void initialize() throws IOException {

        contactObservableList = FXCollections.observableArrayList();
        //get Image

        File file=new FileChooser().showOpenDialog(null);
        user1= ImageConverter.fromImageToBytes(file.getPath());

        contactObservableList.addAll(
                new User("Eima Ross","01014607733",user1),
                new User("Terabithia ","0100040613",user1)
        );


        requestLV.setItems(contactObservableList);
        requestLV.setCellFactory(groupListView -> new ContactRequestListCell());
        requestLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {


            }

        });

    }
}
