package org.iti.project.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import javafx.scene.input.KeyEvent;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.iti.project.presentation.util.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private boolean nameValidation = false;
    private boolean emailValidation = false;
    private boolean passwordValidation = false;
    private boolean confirmPasswordValidation = false;
    private boolean mobileValidation = false;
    private boolean birthDateValidation = false;


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
    private ComboBox<String> countryComboBox;
    private String countries[] = {"Egypt", "Tunisia", "Morocco"};

    @FXML

    private ComboBox<String> genderComboBox;
    private String genders[] = {"Male", "Female"};


    @FXML
    private Label dateValidation;
    private File file = new File("C://Users/eltaweel/Desktop/Abdallah/ChatApplicationJava/ClientSide/target/classes/images/R.png");
    ;

    @FXML
    void onGetImageButtonClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fc.getExtensionFilters().add(extFilter);
        file = fc.showOpenDialog((Stage) vboxReg.getScene().getWindow());

        Image image = null;
        //

        //imageView = new ImageView(image);
        if (file != null) {

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
//        address.textProperty().bindBidirectional(userModel.addressProperty());
        phoneNo.textProperty().bindBidirectional(userModel.phoneNoProperty());
        //       age.textProperty().bindBidirectional(userModel.ageProperty());
        profileImage.imageProperty().bindBidirectional(userModel.userImageProperty());
        countryComboBox.getItems().addAll(countries);
        genderComboBox.getItems().addAll(genders);

        countryComboBox.getSelectionModel().select(0);
        genderComboBox.getSelectionModel().select(0);


        enterBirthDate();


    }

    @FXML
    void enterBirthDate() {
        birthDate.getEditor().setDisable(true);
        birthDate.getEditor().setOpacity(1);
        if (birthDate.getValue() == null) {
            dateValidation.setVisible(true);

        } else {
            dateValidation.setVisible(false);
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
    void enterPassword(KeyEvent event) {

        if (!Validator.passwordValidation(password.getText())) {
            password.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");


        } else {
            password.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            passwordValidation = true;

        }
    }

    @FXML
    void enterConfirmPassword(KeyEvent event) {

        if (!confirmPassword.getText().equals(password.getText())) {

            confirmPassword.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        } else {
            confirmPassword.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            confirmPasswordValidation = true;
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
    void onRegisterButtonClicked(ActionEvent event) {
        if(nameValidation && emailValidation && mobileValidation && birthDateValidation && passwordValidation && confirmPasswordValidation){

            User user = new User();

            user.setGender(genderComboBox.getValue());
            user.setUserCountry(countryComboBox.getValue());

            try {

                byte[] fileContent = FileUtils.readFileToByteArray(file);
                user.setImage(fileContent);

            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setUserEmail(eMail.getText());
            user.setUserName(userName.getText());
            user.setUserPassword(password.getText());
            user.setUserDOB(String.valueOf(birthDate.getValue()));
            user.setUserPhone(phoneNo.getText());
            user.setUserBio("@chat app");

            boolean added = false;

            try {
                added = RMIConnector.getRmiConnector().getChatService().registerMe(user);
            } catch (RemoteException e) {
                Alert remoteExceptionAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                remoteExceptionAlert.showAndWait();
            }
            if(added) {
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
            Notifications.create()
                    .title("All Data Required")
                    .text("All Data Here Must Be Exsists").position(Pos.CENTER)
                    .showError();
        }
    }

}




