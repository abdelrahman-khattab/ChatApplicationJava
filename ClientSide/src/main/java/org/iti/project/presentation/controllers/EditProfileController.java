package org.iti.project.presentation.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;

import java.io.IOException;
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
    @FXML
    private Button closeButton;

    private static EditProfileController editProfileController;
    private ModelFactory modelfactory = ModelFactory.getModelFactory();
    private UserModel userModel = modelfactory.getUserModel();

    public static void setController(EditProfileController editProfileController) {
        EditProfileController.editProfileController = editProfileController;
    }
    public static EditProfileController getInstance(){
        return editProfileController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Binding Information of client
        clientNameTextField.textProperty().bindBidirectional(userModel.userUserNameProperty());
        //countryCombobox.cellFactoryProperty().bindBidirectional();
        clientEmail.textProperty().bindBidirectional(userModel.emailProperty());
        //infoClientGenderLabel.textProperty().bindBidirectional(userModel.userGenderProperty());
        //infoClientNameLabel.textProperty().bindBidirectional(userModel.userUserNameProperty());
        imageProfileView.imageProperty().bindBidirectional(userModel.userImageProperty());
        // set border radius of image view
        Rectangle clip = new Rectangle(100, 100);
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        //imageProfileView.setClip(clip);

        //
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
    @FXML
    void switchtoProfile(ActionEvent event) {
        try {
            ScrollPane profile = FXMLLoader.load(getClass().getResource("/view/sideProfilePane.fxml"));
            vboxContainer.getChildren().removeAll(vboxContainer.getChildren());
            vboxContainer.getChildren().clear();
            vboxContainer.getChildren().add(profile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
