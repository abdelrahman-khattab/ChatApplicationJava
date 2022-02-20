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
import javafx.stage.FileChooser;
import org.iti.project.presentation.models.Group;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SideGroupListController implements Initializable {

    @FXML
    private ListView<Group> groupListView;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;

    public ObservableList<Group> groupsObservableList;

    byte[] user1;

    private static SideGroupListController sideGroupListController;

    public static void setController(SideGroupListController sideGroupListController) {
        SideGroupListController.sideGroupListController = sideGroupListController;
    }
    public static SideGroupListController getInstance(){
        return sideGroupListController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupsObservableList = FXCollections.observableArrayList();

        File file=new FileChooser().showOpenDialog(null);
        user1= ImageConverter.fromImageToBytes(file.getPath());

        //add user groups here
        groupsObservableList.addAll(
                new Group("Iti Group","welcome to the hell",user1),
                new Group("Dark Life","hello every one ",user1)
        );
        groupListView.setItems(groupsObservableList);
        groupListView.setCellFactory(groupListView -> new GroupsInfoListCellController());
        groupListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {

            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                System.out.println("Selected item1: " + newValue.getGroupName());


            }

        });


    }
}
