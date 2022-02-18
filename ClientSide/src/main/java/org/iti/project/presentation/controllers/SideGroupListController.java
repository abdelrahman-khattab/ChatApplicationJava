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
import org.iti.project.presentation.models.Group;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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


            }

        });


    }
}
