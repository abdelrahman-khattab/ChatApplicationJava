package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iti.project.presentation.models.Group;
import org.iti.project.presentation.util.StageCoordinator;

import java.net.URL;
import java.util.ResourceBundle;

public class SideGroupListController implements Initializable {

    @FXML
    private ListView<Group> groupListView;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;

    private ChatScreenController chatScreenController;

    public ObservableList<Group> groupsObservableList;

    private static SideGroupListController sideGroupListController;

    private static final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();

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
        groupsObservableList.addAll(
                new Group("Iti Group","welcome to the hell"),
                new Group("Dark Life","hello every one ")
        );
        groupListView.setItems(groupsObservableList);
        groupListView.setCellFactory(groupListView -> new GroupsInfoController());
        groupListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {

            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                System.out.println("Selected item1: " + newValue.getGroupName());
                stageCoordinator.getChatScreenController().setIsGroup(true);
                System.out.println(stageCoordinator.getChatScreenController().isIsGroup());



            }

        });


    }
}
