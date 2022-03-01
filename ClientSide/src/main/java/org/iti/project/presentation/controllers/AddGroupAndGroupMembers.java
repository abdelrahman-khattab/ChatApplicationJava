package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.util.ArrayList;
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
    Image groupImg ;

    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fc.getExtensionFilters().add(extFilter);
        file = fc.showOpenDialog((Stage) vBoxView.getScene().getWindow());

//        Image image = profileImage.getImage();


        if (file != null) {
            groupImage.setFill(new ImagePattern(new Image(file.getPath())));
            groupImg = new Image(file.getPath());
        }else{
            groupImage.setFill(new ImagePattern(new Image("/images/R.png")));
        }
    }

    //Add group name here...............................
    @FXML
    void addGroup(ActionEvent event) {
        Group group = new Group();
        byte[] fileContent = ImageConverter.fromImageToBytes(groupImg.getUrl());
        group.setGroupName(newGroupName.getText());
        group.setGroupImageBytes(fileContent);
        User currentUser = new User();
        currentUser.setUserPhone(userModel.getPhoneNo());
        try {
            RMIConnector.getRmiConnector().getGroupServices().createNewGroup(group , currentUser);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            SideGroupListController.groupsObservableList.clear();
            SideGroupListController.groupsObservableList.addAll(RMIConnector.getRmiConnector().getGroupServices().getListOfGroupsForCurrentUser(currentUser));
            groupsObservableList.clear();
            groupsObservableList.addAll(RMIConnector.getRmiConnector().getGroupServices().getListOfGroupsForCurrentUser(currentUser));

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();
    ArrayList<User>selectedUser = new ArrayList<>();
    Group selectedGroup = new Group();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactsObservableList = FXCollections.observableArrayList();
        groupsObservableList = FXCollections.observableArrayList();

        User user = new User();
        user.setUserPhone(userModel.getPhoneNo());
        try {
            contactsObservableList.addAll(RMIConnector.getRmiConnector().getContactService().getContact(user));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Group> groupList = new ArrayList<>();
            groupList = RMIConnector.getRmiConnector().getGroupServices().getListOfGroupsForCurrentUser(user);
            for (Group group:groupList) {
                System.out.println(group.getGroupName());

            }
            groupsObservableList.addAll(RMIConnector.getRmiConnector().getGroupServices().getListOfGroupsForCurrentUser(user));

        } catch (RemoteException e) {
            e.printStackTrace();
        }


        memberLV.setItems(contactsObservableList);
        memberLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        memberLV.setCellFactory(groupListView -> new AddContactsWithGroupListCell());
        memberLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {

                //all select item
                ObservableList<User> selected = memberLV.getSelectionModel().getSelectedItems();
                // Display the selections.
                for(int i=0; i < selected.size(); i++) {
                   // selectedUser.clear();
                    User newUser = new User();
                    newUser.setUserName(selected.get(i).getUserName());
                    newUser.setUserPhone(selected.get(i).getUserPhone());
                    selectedUser.add(newUser);
                    System.out.println(selected.get(i).getUserName());

//                System.out.println("Select user  values : " + newValue.getUserName());
                }
            }

        });

        groupsLv.setItems(groupsObservableList);
        groupsLv.setCellFactory(groupListView -> new AddGroupWithContactListCell());
        groupsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        groupsLv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                System.out.println("Select group  values : " + newValue.getGroupName());
                System.out.println("Select group  number : " + newValue.getGroupId());
                selectedGroup.setGroupId(newValue.getGroupId());
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
            addMembers.setDisable(false);
    }

    @FXML
    void addMembersToGroup(ActionEvent event) {
        try {

            System.out.println("select user in array is : "+selectedUser);
            RMIConnector.getRmiConnector().getGroupServices().addUsersToGroup(selectedGroup , selectedUser);
        } catch (RemoteException e) {
            System.out.println("here the problem in member to group");
            e.getMessage();
        }
    }
}

