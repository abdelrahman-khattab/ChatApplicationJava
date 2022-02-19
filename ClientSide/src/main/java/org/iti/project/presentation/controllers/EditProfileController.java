package org.iti.project.presentation.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    @FXML
    private TextField clientEmail;

    @FXML
    private TextField clientNameTextField;

    @FXML
    private PasswordField clientPassword;

    @FXML
    private Label clientbioLabel;

    @FXML
    private ComboBox<String> countryCombobox;

    @FXML
    private Button editInformation;

    @FXML
    private Button editInformationButton;

    @FXML
    private ComboBox<String> genderCombobox;

    @FXML
    private ImageView imageProfileView;

    @FXML
    private ScrollPane secondPan;

    @FXML
    private Button uploadImageButton;

    @FXML
    private VBox vboxContainer;

    private static EditProfileController editProfileController;

    public static void setController(EditProfileController editProfileController) {
        EditProfileController.editProfileController = editProfileController;
    }
    public static EditProfileController getInstance(){
        return editProfileController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombobox.getItems().addAll("Egypt","Tunsia","Morocco");
        genderCombobox.getItems().addAll("Male","Female");

    }
    @FXML
    void editClientInformation(ActionEvent event) {

    }

    @FXML
    void editInformation(ActionEvent event) {

    }

    @FXML
    void uploadImage(ActionEvent event) {

    }

}
