package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.util.Validator;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MemberListController implements Initializable {
    private boolean nameValidation = false;
    private boolean emailValidation = false;
    private boolean mobileValidation = false;
    private boolean birthDateValidation = false;
    private boolean imageValidation = false;

    @FXML
    private Button addMember;

    @FXML
    private ImageView addUser;

    @FXML
    private DatePicker birthDate;

    @FXML
    private TextArea clientBioTextArea;


    @FXML
    private Button clientRegistrationButton;

    @FXML
    private Circle memberImage;

    @FXML
    private ComboBox<String> countryComboBox;
    private String countries[] = {"Egypt", "Tunisia", "Morocco"};

    @FXML

    private ComboBox<String> genderComboBox;
    private String genders[] = {"Male", "Female"};

    @FXML
    private TextField eMail;


    @FXML
    private ListView<User> membersLV;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField searchBar1;

    @FXML
    private Button uploadImageBtn;

    @FXML
    private TextField userName;
    @FXML
    private VBox editVBox;

    private File file;
    public ObservableList<User> membersObservableList;
    byte[] userImg;
    @FXML
    void enterBirthDate(ActionEvent event) {
        birthDate.getEditor().setDisable(true);
        birthDate.getEditor().setOpacity(1);
        if (birthDate.getValue() == null) {
//            dateValidation.setVisible(true);

        } else {
//            dateValidation.setVisible(false);
            birthDateValidation = true;

        }

    }

    @FXML
    void enterEmail(KeyEvent event) {
        if (!Validator.emailValidation(eMail.getText())) {
            eMail.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        } else {
            eMail.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            emailValidation = true;
        }

    }

    @FXML
    void enterPhoneNumber(KeyEvent event) {
        if (phoneNo == null)
            phoneNo.setText("");
        if (!Validator.phoneValidation(phoneNo.getText())) {
            phoneNo.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
            mobileValidation = false;

        } else {
            phoneNo.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            mobileValidation = true;
        }

    }

    @FXML
    void enterUserName(KeyEvent event) {
        if (!Validator.nameValidation(userName.getText())) {
            userName.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
            nameValidation = false;


        } else {
            userName.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            nameValidation = true;

        }
    }


    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fc.getExtensionFilters().add(extFilter);
        file = fc.showOpenDialog((Stage) editVBox.getScene().getWindow());
        if (file != null) {
            memberImage.setFill(new ImagePattern(new Image(file.getPath())));
//            System.out.println(memberImage.getFill());
        }else{
            memberImage.setFill(new ImagePattern(new Image("/images/R.png")));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        membersObservableList = FXCollections.observableArrayList();
        File file=new FileChooser().showOpenDialog(null);
        userImg= ImageConverter.fromImageToBytes(file.getPath());

        membersObservableList.addAll(
                new User("Eima Ross",userImg),
                new User("Terabithia ",userImg)
        );

        membersLV.setItems(membersObservableList);
        membersLV.setCellFactory(groupListView -> new MemberListCellController());
        membersLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
            }

        });


//        countryComboBox.getItems().addAll(countries);
//        genderComboBox.getItems().addAll(genders);
//
//        countryComboBox.getSelectionModel().select(0);
//        genderComboBox.getSelectionModel().select(0);

    }




}
