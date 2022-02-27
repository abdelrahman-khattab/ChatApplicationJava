package org.iti.project.presentation.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FriendRequestNotificationController {

    @FXML
    private ListView<User> requestLV;
    public static ObservableList<User> contactObservableList;
    byte[] user1;
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();

    public void initialize() throws IOException {

        contactObservableList = FXCollections.observableArrayList();
        //get Image
        //System.out.println("before requestList");
        File file=new FileChooser().showOpenDialog(null);
        user1= ImageConverter.fromImageToBytes(file.getPath());
        ArrayList<User> requestList = new ArrayList<>();
        User currentUser = new User();
        currentUser.setUserPhone(userModel.getPhoneNo());
      requestList = RMIConnector.getRmiConnector().getContactService().requestListFriends(currentUser);
       contactObservableList.addAll(requestList);

        requestLV.setItems(contactObservableList);
        requestLV.setCellFactory(groupListView -> new ContactRequestListCell());
        requestLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {


            }

        });

    }
}
