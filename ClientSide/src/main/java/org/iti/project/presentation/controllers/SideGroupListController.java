package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iti.project.models.Group;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.util.ImageConverter;

import org.iti.project.presentation.util.StageCoordinator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SideGroupListController implements Initializable {

    @FXML
    private ListView<Group> groupListView;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;


    @FXML
    private Button addGroupAddContactsBtn;

    private ChatScreenController chatScreenController;

    public ObservableList<Group> groupsObservableList;

    byte[] user1Img;

    private static SideGroupListController sideGroupListController;

    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();

    public static void setController(SideGroupListController sideGroupListController) {
        SideGroupListController.sideGroupListController = sideGroupListController;
    }
    public static SideGroupListController getInstance(){
        return sideGroupListController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        chatScreenController = ChatScreenController.getInstance();
        groupsObservableList = FXCollections.observableArrayList();


        //add user groups here
        User user = new User();
        user.setUserPhone(userModel.getPhoneNo());
        try {
            groupsObservableList.addAll(RMIConnector.getRmiConnector().getGroupServices().getListOfGroupsForCurrentUser(user));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        groupListView.setItems(groupsObservableList);
        groupListView.setCellFactory(groupListView -> new GroupsInfoListCellController());
        groupListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {

            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                System.out.println("Selected item1: " + newValue.getGroupName());
                stageCoordinator.getChatScreenController().setIsGroup(true);
                System.out.println(stageCoordinator.getChatScreenController().isIsGroup());
//                stageCoordinator.getChatScreenController().setCurrentContactedGroup(groupListView.getSelectionModel().getSelectedItem());
                Group newGroup = groupListView.getSelectionModel().getSelectedItem();
                stageCoordinator.getChatScreenController().setCurrentContactedGroup(newGroup);
                Image newGroupImage = ImageConverter.fromBytesToImage(newGroup.getGroupImageBytes());
                stageCoordinator.getChatScreenController().updateChatScene(newGroupImage , newGroup.getGroupName());

            }

        });


    }

    @FXML
    void addGroupAddContacts(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addGroupAndGroupMembers.fxml"));
        Parent parent= fxmlLoader.load();

        Scene scene = new Scene(parent, 500, 550);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
