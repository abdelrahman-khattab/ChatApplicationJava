package org.iti.project.presentation.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.Notifications;
import org.iti.project.models.User;
import org.iti.project.presistence.util.Validator;
import org.iti.project.services.impls.AdminImpl;
import org.iti.project.services.interfaces.AdminInt;

public class RegisterController implements Initializable {
    private boolean nameValidation = false;
    private boolean emailValidation = false;
    private boolean passwordValidation = false;
    private boolean confirmPasswordValidation = false;
    private boolean mobileValidation = false;
    private boolean birthDateValidation = false;
    private boolean imageValidation = false;
    private AdminInt adminObj = new AdminImpl();

    @FXML
    private TextArea clientBioTextArea;

    @FXML
    private DatePicker clientBirthDateField;

    @FXML
    private TextField clientCountryTextField;

    @FXML
    private TextField clientEmailTextField;

    @FXML
    private ComboBox<String> clientGenderField;

    @FXML
    private TextField clientMobileTextField;

    @FXML
    private TextField clientNameTextField;

    @FXML
    private PasswordField clientPasswordField;

    @FXML
    private Button clientRegistrationButton;

    private User user = new User();

    public RegisterController() throws RemoteException {
    }

    @FXML
    void registerNewClient(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        clientGenderField.getItems().addAll("Male", "Female");
        enterBirthDate();
        //genderComboBox.getSelectionModel().select(0);


        
    }

    public void enterPhoneNumber(KeyEvent keyEvent) {
        if (clientMobileTextField == null)
            clientMobileTextField.setText("");
        if (!Validator.phoneValidation(clientMobileTextField.getText())) {
            clientMobileTextField.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
            mobileValidation = false;

        } else {
            clientMobileTextField.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            mobileValidation = true;
        }
    }

    public void enterPassword(KeyEvent keyEvent) {
        if (!Validator.passwordValidation(clientPasswordField.getText())) {
            clientPasswordField.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");


        } else {
            clientPasswordField.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            passwordValidation = true;

        }
    }

   /* public void enterConfirmPassword(KeyEvent keyEvent) {
        if (!confirmPassword.getText().equals(password.getText())) {

            confirmPassword.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        } else {
            confirmPassword.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            confirmPasswordValidation = true;
        }
    }
    */

    public void enterEmail(KeyEvent keyEvent) {
        if (!Validator.emailValidation(clientEmailTextField.getText())) {
            clientEmailTextField.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        } else {
            clientEmailTextField.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            emailValidation = true;
        }
    }

    public void enterBio(KeyEvent keyEvent) {
    }

    public void onGetImageButtonClick(ActionEvent actionEvent) {
    }

    public void enterBirthDate() {
        clientBirthDateField.getEditor().setDisable(true);
        clientBirthDateField.getEditor().setOpacity(1);
        if (clientBirthDateField.getValue() == null) {
            //dateValidation.setVisible(true);

        } else {
            //dateValidation.setVisible(false);
            birthDateValidation = true;

        }


    }

    public void enterGender(KeyEvent keyEvent) {
    }

    public void onRegisterButtonClicked(ActionEvent actionEvent) {
        if (nameValidation && emailValidation && mobileValidation && birthDateValidation && passwordValidation) {

            User user = new User();
            user.setUserEmail(clientEmailTextField.getText());
            user.setUserName(clientNameTextField.getText());
            user.setUserPassword(clientPasswordField.getText());
            user.setUserDOB(String.valueOf(clientBirthDateField.getValue()));
            user.setUserPhone(clientMobileTextField.getText());
            user.setUserBio("@admin side");

            boolean added = false;

            try {
                added = adminObj.registerUser(user);
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
                    .text("All Data fields Must Be filled").position(Pos.CENTER)
                    .showError();
        }
    }

    public void enterUserName(KeyEvent keyEvent) {
        if (!Validator.nameValidation(clientNameTextField.getText())) {
            clientNameTextField.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
            nameValidation = false;


        } else {
            clientNameTextField.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            nameValidation = true;

        }
    }
}
