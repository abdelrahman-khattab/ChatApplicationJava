package org.iti.project.presentation.controllers;

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
import org.iti.project.presentation.util.Validator;
import org.iti.project.services.impls.AdminImpl;
import org.iti.project.services.interfaces.AdminInt;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddNewUserController implements Initializable {
    private boolean nameValidation = false;
    private boolean emailValidation = false;
    private boolean mobileValidation = false;
    private boolean birthDateValidation = false;
    private boolean imageValidation = false;
    private AdminInt adminObj;



    @FXML
    private Button addMember;

    @FXML
    private ImageView addUser;

    @FXML
    private DatePicker birthDate1;

    @FXML
    private TextArea clientBioTextArea;


    @FXML
    private Button clientRegistrationButton;

    @FXML
    private ImageView memberImage;

    @FXML
    private TextField eMail;

    @FXML
    private ComboBox<String> countryComboBox;
    private String countries[] = {"Egypt", "Tunisia", "Morocco"};

    @FXML

    private ComboBox<String> genderComboBox;
    private String genders[] = {"Male", "Female"};

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
    void uploadImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fc.getExtensionFilters().add(extFilter);
        file = fc.showOpenDialog((Stage) editVBox.getScene().getWindow());
        if (file != null) {
            memberImage.setImage(new Image(file.getPath()));

        }else{
            memberImage.setImage(new Image("/images/R.png"));
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        countryComboBox.getItems().addAll(countries);
        genderComboBox.getItems().addAll(genders);

        countryComboBox.getSelectionModel().select(0);
        genderComboBox.getSelectionModel().select(0);

        enterBirthDate();

    }
    @FXML
     void addUser(ActionEvent actionEvent) {
        System.out.println("user not added");
        if(nameValidation && emailValidation && mobileValidation && birthDateValidation){

            User user = new User();

            user.setGender(genderComboBox.getValue());
            user.setUserCountry(countryComboBox.getValue());


            user.setUserEmail(eMail.getText());
            user.setUserName(userName.getText());
            user.setUserPassword("chat_App1");
            user.setUserDOB(String.valueOf(birthDate1.getValue()));
            user.setUserPhone(phoneNo.getText());
            user.setUserBio("@chat app");

            boolean added = false;
            try {
                adminObj = new AdminImpl();
                added = adminObj.registerUser(user);
            }  catch (RemoteException e) {
                Alert remoteExceptionAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                remoteExceptionAlert.showAndWait();
            }
            if(added) {
                System.out.println("user added");
                Notifications.create()
                        .title("Registration")
                        .text("Congrats! You are one of us now, go login talk to your friends!").position(Pos.TOP_CENTER)
                        .showConfirm();

            }else{
                Notifications.create()
                        .title("Registration Faild !!")
                        .text("You Can Log in , You Have already Email On The System").position(Pos.TOP_CENTER)
                        .showWarning();
            }
            //
        }else{
            System.out.println("user not added");
            Notifications.create()
                    .title("All Data Required")
                    .text("All Data fields Must Be filled").position(Pos.TOP_CENTER)
                    .showError();
        }
    }
    @FXML
     void enterBirthDate() {
        birthDate1.getEditor().setDisable(true);
        birthDate1.getEditor().setOpacity(1);
        if (birthDate1.getValue() == null) {
           System.out.println(birthDate1.getValue() == null);

        } else {

            birthDateValidation = true;

        }
    }
}



