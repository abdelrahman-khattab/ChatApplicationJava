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
import org.iti.project.models.Group;
import org.iti.project.network.RMIConnector;
import org.iti.project.services.impls.ClientCallBack;

import java.net.URL;
import java.util.ResourceBundle;

public class SideServerAnnouncementController implements Initializable {
    @FXML
    private ListView<String> announcementListView;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;
//
//    private static SideServerAnnouncementController sideServerAnnouncementController;
//    private SideServerAnnouncementController()
//    {
//        sideServerAnnouncementController= new SideServerAnnouncementController();
//    }
//
//    public static SideServerAnnouncementController getInstance()
//    {
//        return sideServerAnnouncementController;
//    }
    public static ObservableList<String> announcementObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        announcementObservableList = FXCollections.observableArrayList();
        announcementObservableList.addAll(ClientCallBack.announcementMessageList);
        announcementListView.setItems(announcementObservableList);
        announcementListView.setCellFactory(groupListView -> new ServerAnnouncementsListCell());
        announcementListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });


    }
}
