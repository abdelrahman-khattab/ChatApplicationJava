package gov.iti.jets.project.presentation.controllers;

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

}
