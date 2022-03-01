package org.iti.project.presentation.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.presentation.util.Validator;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;
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
    private Circle userImage;

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

    private File file = null;

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
        //clientNameTextField.textProperty().bindBidirectional(userModel.userUserNameProperty());
        clientNameTextField.setText(userModel.getUserUserName());
        //countryCombobox.cellFactoryProperty().bindBidirectional();
//        clientEmail.textProperty().bindBidirectional(userModel.emailProperty());
        clientEmail.setText(userModel.getEmail());
        clientPassword.setText(userModel.getUserPassword());

        //infoClientGenderLabel.textProperty().bindBidirectional(userModel.userGenderProperty());
        //infoClientNameLabel.textProperty().bindBidirectional(userModel.userUserNameProperty());
//        imageProfileView.imageProperty().bindBidirectional(userModel.userImageProperty());
        imageProfileView.setImage(userModel.getUserImage());
        // set border radius of image view
       /*
        Rectangle clip = new Rectangle(100, 100);
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        imageProfileView.setClip(clip);
*/
        //
        countryCombobox.getItems().addAll("Egypt","Tunsia","Morocco");

        genderCombobox.getItems().addAll("Male","Female");

//
        //countryCombobox.itemsProperty().set("ahmed");
       // countryCombobox.valueProperty().bindBidirectional();
        countryCombobox.valueProperty().set(userModel.getUserCountry());
        genderCombobox.valueProperty().set(userModel.getUserGender());
        //genderCombobox.valueProperty().bindBidirectional(userModel.userGenderProperty());

    }
    @FXML
    void editClientInformation(ActionEvent event) {

    }

    @FXML
    void editInformation(ActionEvent event) {
        String name = clientNameTextField.getText();
        String email = clientEmail.getText();
        Image clientImage = imageProfileView.getImage();
        String password = clientPassword.getText();
        String country = countryCombobox.getValue();
        String gender = genderCombobox.getValue();
        byte[] fileContent = null;
//
        if(Validator.nameValidation(name) && Validator.emailValidation(email)  && Validator.passwordValidation(password)) {
            if(file != null) {
                 fileContent = ImageConverter.fromImageToBytes(imageProfileView.getImage().getUrl());
            }

           User user = new User(userModel.getPhoneNo(),name,email,password,gender,country,fileContent);
//            Check if data updated or no
            boolean updated = false;

            try {
                updated = RMIConnector.getRmiConnector().getUpdateService().updateMe(user);

            } catch (RemoteException e) {
                Alert remoteExceptionAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                remoteExceptionAlert.showAndWait();
           }


            if(updated) {
                Notifications.create()
                        .title("Update Information")
                        .text("Congrats! Your Information Updated Successfully").position(Pos.TOP_CENTER)
                        .showConfirm();
//                If data updated will goto profile pane
                ChatScreenController.switchToProfilePane();

            }else{
                Notifications.create()
                        .title("Update Failed !!")
                        .text("There is an Problem in Updating your Info").position(Pos.TOP_CENTER)
                        .showWarning();
            }
//

        }else{
            Notifications.create()
                    .title("All Data Required")
                    .text("All Data fields Must Be filled To Update It").position(Pos.CENTER)
                    .showError();
        }
        //

//        Change the data into the user Model
        userModel.setEmail(email);
        userModel.setUserPassword(password);
        userModel.setUserUserName(name);
        userModel.setUserCountry(country);
        userModel.setUserGender(gender);
        userModel.setUserImage(clientImage);

    }

    @FXML
    void uploadImage(ActionEvent event) {

//        To Upload New Image

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fc.getExtensionFilters().add(extFilter);
        file = fc.showOpenDialog(null);

        Image image = imageProfileView.getImage();
        //

        //imageView = new ImageView(image);

        if (file != null) {
            image = new Image(file.getPath());
            imageProfileView.setImage(image);
            userImage.setFill(new ImagePattern(image));
        }



    }
    @FXML
    void switchtoProfile(ActionEvent event) {
//        try {
//            ScrollPane profile = FXMLLoader.load(getClass().getResource("/view/sideProfilePane.fxml"));
//            vboxContainer.getChildren().removeAll(vboxContainer.getChildren());
//            vboxContainer.getChildren().clear();
//            vboxContainer.getChildren().add(profile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ChatScreenController.switchToProfilePane();

    }
}
