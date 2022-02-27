package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private Circle chatImage1;

    @FXML
    private Circle chatImage11;

    @FXML
    private Circle chatImage111;

    @FXML
    private Circle chatImage2;

    @FXML
    private Circle contactImageStatusBar;

    @FXML
    private Label contactNameStatusBar;

    @FXML
    private Label contactOnlineName1;

    @FXML
    private Label contactOnlineName11;

    @FXML
    private Label contactOnlineName111;

    @FXML
    private Label contactOnlineName2;

    @FXML
    private VBox contactStatusVBox;

    @FXML
    private ListView<User> contactinfoLV;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;

    @FXML
    private Circle userStatusStatusBar;

    public ObservableList<User> contactChatObservableList;
    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();

    byte[] user1;

    public static SideChatListController getInstance() {
        return sideChatListController;
    }

    public static void setController(SideChatListController sideChatListController) {
        SideChatListController.sideChatListController = sideChatListController;
    }

    private static SideChatListController sideChatListController;

    public void initialize() throws IOException {

        contactChatObservableList = FXCollections.observableArrayList();
        //get Image
        File file=new FileChooser().showOpenDialog(null);
        user1=ImageConverter.fromImageToBytes(file.getPath());

        contactChatObservableList.addAll(
                new User("Mohamed Alaa",user1 ,"online",2 , "01111111111"),
                new User("saly ",user1,"offine",4,"01111111113")
        );
        contactinfoLV.setItems(contactChatObservableList);
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
                Image selectedUserImage = ImageConverter.fromBytesToImage(selectedUser.getImage());
                stageCoordinator.getChatScreenController().updateChatScene(selectedUserImage , selectedUser.getUserName());
                System.out.println(stageCoordinator.getChatScreenController().getCurrentContactedUser().getUserPhone()+
                        " from sideChatListview I am the phone number");







            }

        });

    }
   

}
