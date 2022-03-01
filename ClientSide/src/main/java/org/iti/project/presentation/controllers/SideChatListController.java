package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.iti.project.models.Group;
import org.iti.project.models.User;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.services.interfaces.SignOutInt;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.io.IOException;

public class SideChatListController {




    @FXML
    private ListView<User> contactinfoLV;

    @FXML
    private ListView<User> userStatusListView;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;



    public ObservableList<User> contactChatObservableList;
    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
    private final SideContactListController sideContactListController = SideContactListController.getInstance();

    byte[] user1;

    public ListView<User> getContactinfoLV() {
        return contactinfoLV;
    }

    public static SideChatListController getInstance() {
        return sideChatListController;
    }

    public static void setController(SideChatListController sideChatListController) {
        SideChatListController.sideChatListController = sideChatListController;
    }

    private static SideChatListController sideChatListController;
    public static ObservableList<User> userObservableList;

    public void initialize() throws IOException {
        userObservableList = FXCollections.observableArrayList();



        contactinfoLV.setItems(sideContactListController.contactObservableList);
        contactinfoLV.setCellFactory(groupListView -> new ContactsChatInfoListCellController());
        contactinfoLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                System.out.println("Selected item1: " + newValue.getUserName());
                stageCoordinator.getChatScreenController().setIsGroup(false);
                System.out.println(stageCoordinator.getChatScreenController().isIsGroup());
                User selectedUser = contactinfoLV.getSelectionModel().getSelectedItem();
                System.out.println(selectedUser.getUserPhone());
                stageCoordinator.getChatScreenController().setCurrentContactedUser(selectedUser);
                stageCoordinator.getChatScreenController().updateSingleChatScene(selectedUser);
                System.out.println(stageCoordinator.getChatScreenController().getCurrentContactedUser().getUserPhone()+
                        " from sideChatListview I am the phone number");

            }

        });


        userObservableList.addAll(sideContactListController.contactObservableList);
        userStatusListView.setOrientation(Orientation.HORIZONTAL);
        userStatusListView.setItems(userObservableList);
        userStatusListView.setCellFactory(userListView -> new UserStatusListCell());
        userStatusListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                System.out.println(newValue.getUserName());
            }

        });

    }
   

}
