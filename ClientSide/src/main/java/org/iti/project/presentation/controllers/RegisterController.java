package org.iti.project.presentation.controllers;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.iti.project.presentation.util.Validator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private boolean nameValidation = false;
    private boolean emailValidation = false;
    private boolean passwordValidation = false;
    private boolean mobileValidation = false;
    private boolean birthDateValidation = false;
    private boolean genderValidation = false;

    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    UserModel userModel;
    @FXML
    private TextField bio;


    @FXML
    private DatePicker birthDate;

    @FXML
    private TextField country;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TextField eMail;

    @FXML
    private RadioMenuItem genFemale;

    @FXML
    private RadioMenuItem genMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private MenuButton genderButton;

    @FXML
    private PasswordField password;

    @FXML
    private FontAwesomeIconView passwordIcon;

    @FXML
    private TextField phoneNo;

    @FXML
    private FontAwesomeIconView phoneNoIcon;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button registerButton;

    @FXML
    private Button uploadImageButton;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private TextField userName;

    @FXML
    private VBox vboxReg;

    @FXML
    private Label dateValidation;

    @FXML
    void onGetImageButtonClick(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg","*.png");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog((Stage)vboxReg.getScene().getWindow());
        Image image = null;
        //

        //imageView = new ImageView(image);
        if(file != null) {
            image = new Image(file.getPath());
            profileImage.setImage(image);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        userName.textProperty().bindBidirectional(userModel.userUserNameProperty());
        password.textProperty().bindBidirectional(userModel.userPasswordProperty());
        eMail.textProperty().bindBidirectional(userModel.emailProperty());
        phoneNo.textProperty().bindBidirectional(userModel.phoneNoProperty());
        profileImage.imageProperty().bindBidirectional(userModel.userImageProperty());


        // initialize Bio

        // set the default value for the datepicker
        enterBirthDate();


    }

    @FXML
    void enterBio(KeyEvent event) {

    }

    @FXML
    void enterBirthDate() {
        birthDate.getEditor().setDisable(true);
        birthDate.getEditor().setOpacity(1);
        if(birthDate.getValue() == null){
            dateValidation.setVisible(true);

        }else{
            dateValidation.setVisible(false);

        }
    }



    @FXML
    void enterEmail(KeyEvent event) {
        if (!Validator.emailValidation(eMail.getText())) {
            eMail.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        } else {
            eMail.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");

        }
    }

    @FXML
    void enterGender(KeyEvent event) {

    }

    @FXML
    void enterPassword(KeyEvent event) {

        if (!Validator.passwordValidation(password.getText())) {
            password.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");


        } else {
            password.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");

        }
    }

    @FXML
    void enterConfirmPassword(KeyEvent event) {

        if(!confirmPassword.getText().equals(password.getText())) {
            confirmPassword.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        }
     else
            confirmPassword.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");


    }

    @FXML
    void enterPhoneNumber(KeyEvent event) {
        if (!Validator.phoneValidation(phoneNo.getText())) {
            phoneNo.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");


        } else {
            phoneNo.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");

        }
    }

    @FXML
    void enterUserName(KeyEvent event) {

        if (!Validator.nameValidation(userName.getText())) {
            userName.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");


        } else {
            userName.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");

        }

    }

    @FXML
    void onRegisterButtonClicked(ActionEvent event) {

    }


}