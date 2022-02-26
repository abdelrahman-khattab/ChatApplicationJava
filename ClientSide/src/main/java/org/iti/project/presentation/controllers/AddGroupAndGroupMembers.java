package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.iti.project.models.Group;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class AddGroupAndGroupMembers implements Initializable {

    @FXML
    private Button addGroupBtn;

    @FXML
    private Button addMembers;

    @FXML
    private ListView<Group> groupsLv;

    @FXML
    private ListView<User> memberLV;

    @FXML
    private TextField newGroupName;
    @FXML
    private Button uploadImageBtn;
    @FXML
    private Circle groupImage;
    @FXML
    private VBox vBoxView;

    public ObservableList<User> contactsObservableList;
    public ObservableList<Group> groupsObservableList;
    byte[] userImg;
    private File file;


    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fc.getExtensionFilters().add(extFilter);
        file = fc.showOpenDialog((Stage) vBoxView.getScene().getWindow());

//        Image image = profileImage.getImage();


        //

        if (file != null) {
            groupImage.setFill(new ImagePattern(new Image(file.getPath())));
        }else{
            groupImage.setFill(new ImagePattern(new Image("/images/R.png")));
        }
    }

    //Add group name here...............................
    @FXML
    void addGroup(ActionEvent event) {
        Group group = new Group(newGroupName.getText());
        try {
            RMIConnector.getRmiConnector().getGroupServices().createNewGroup(group);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactsObservableList = FXCollections.observableArrayList();
        groupsObservableList = FXCollections.observableArrayList();

        File file=new FileChooser().showOpenDialog(null);
        userImg= ImageConverter.fromImageToBytes(file.getPath());
        User user = new User();
        user.setUserPhone(userModel.getPhoneNo());
        try {
            contactsObservableList.addAll(RMIConnector.getRmiConnector().getContactService().getContact(user));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            groupsObservableList.addAll(RMIConnector.getRmiConnector().getGroupServices().getListOfGroupsForCurrentUser(user));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        memberLV.setItems(contactsObservableList);
        memberLV.setCellFactory(groupListView -> new AddContactsWithGroupListCell());
        memberLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {


            }

        });

        groupsLv.setItems(groupsObservableList);
        groupsLv.setCellFactory(groupListView -> new AddGroupWithContactListCell());
        groupsLv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {

            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {


            }

        });






        if(groupsLv.getSelectionModel().selectedItemProperty()==null || memberLV.getSelectionModel().selectedItemProperty()==null){
            System.out.println("two list...");
            addMembers.setDisable(false);
        }
        else{
            System.out.println("twolist ...");
            addMembers.setDisable(true);
        }





    }
    @FXML
    void validateGroupName(KeyEvent event) {
        if(newGroupName.getText()==null ||newGroupName.getText().trim().isEmpty() ){
            addGroupBtn.setDisable(true);
        }else{
            addGroupBtn.setDisable(false);
        }

    }
    @FXML
    void listviewLisner(MouseEvent event) {
        if(groupsLv.getSelectionModel().selectedItemProperty()==null){
            memberLV.setDisable(true);
        }
        else{
            memberLV.setDisable(false);
        }
    }
    @FXML
    void contactListValidate(MouseEvent event) {
        if(memberLV.getSelectionModel().selectedItemProperty()==null){
            addMembers.setDisable(true);
        }
        else{
            addMembers.setDisable(false);
        }
    }

}

