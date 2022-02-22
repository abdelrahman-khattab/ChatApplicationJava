package org.iti.project.presentation.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iti.project.models.User;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.io.IOException;

public class SideContactListController {

    @FXML
    private Button addContactBtn;

    @FXML
    private ListView<User> contactsLV;

    @FXML
    private Button search;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane secondPane;

    private static SideContactListController sideContactListController;

    public static void setController(SideContactListController sideContactListController){
        SideContactListController.sideContactListController = sideContactListController;
    }

    public static SideContactListController getInstance() {
        return sideContactListController;
    }

    @FXML
    void addContact() throws IOException {
        System.out.println("add");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addContact.fxml"));
        Parent parent= fxmlLoader.load();

        System.out.println("add1");


        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();


    }

    @FXML
    void test(MouseEvent event) {

    }

    public ObservableList<User> contactObservableList;
    byte[] user1;
    byte[] user2;

    public void initialize() throws IOException {

        contactObservableList = FXCollections.observableArrayList();
        //get Image
        File file=new FileChooser().showOpenDialog(null);
        user1= ImageConverter.fromImageToBytes(file.getPath());

        contactObservableList.addAll(
                new User("Eima Ross","01014607733",user1),
                new User("Terabithia ","0100040613",user1)
        );

        contactsLV.setItems(contactObservableList);
        contactsLV.setCellFactory(groupListView -> new ContactsInfoListCellController());
        contactsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                System.out.println("Selected item1: " + newValue.getUserName());


            }

        });


    }
}
