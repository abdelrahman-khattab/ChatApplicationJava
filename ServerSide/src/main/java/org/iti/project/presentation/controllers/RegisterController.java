package org.iti.project.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

public class RegisterController implements Initializable {
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

    @FXML
    void registerNewClient(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        clientGenderField.getItems().addAll("Male", "Female");

        
    }

    public void enterPhoneNumber(KeyEvent keyEvent) {
    }

    public void enterPassword(KeyEvent keyEvent) {
    }

    public void enterConfirmPassword(KeyEvent keyEvent) {
    }

    public void enterEmail(KeyEvent keyEvent) {
    }

    public void enterBio(KeyEvent keyEvent) {
    }

    public void onGetImageButtonClick(ActionEvent actionEvent) {
    }

    public void enterBirthDate(KeyEvent keyEvent) {
    }

    public void enterGender(KeyEvent keyEvent) {
    }

    public void onRegisterButtonClicked(ActionEvent actionEvent) {
    }

    public void enterUserName(KeyEvent keyEvent) {
    }
}
