package org.iti.project.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    UserModel userModel;
    @FXML
    private VBox vboxReg;
    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField eMail;

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
    private FontAwesomeIconView userIcon;
    @FXML
    private TextField userName;



    @FXML
    void onGetImageButtonClick(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog((Stage)vboxReg.getScene().getWindow());
        Image image = null;
        image = new Image(file.getPath());
        //imageView = new ImageView(image);
        profileImage.setImage(image);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        userName.textProperty().bindBidirectional(userModel.userUserNameProperty());
        password.textProperty().bindBidirectional(userModel.userPasswordProperty());
        eMail.textProperty().bindBidirectional(userModel.emailProperty());
        address.textProperty().bindBidirectional(userModel.addressProperty());
        phoneNo.textProperty().bindBidirectional(userModel.phoneNoProperty());
        age.textProperty().bindBidirectional(userModel.ageProperty());
        profileImage.imageProperty().bindBidirectional(userModel.userImageProperty());

    }

}
