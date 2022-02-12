package org.iti.project.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.iti.project.presentation.util.StageCoordinator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    UserModel userModel;
    @FXML
    private PasswordField password;

    @FXML
    private FontAwesomeIconView passwordIcon;

    @FXML
    private ImageView profileImage;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private TextField userName;

    @FXML
    private VBox vBox;

    @FXML
    void forgetPassword()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        vBox.getChildren().removeAll();
        try {
            vBox.getChildren().setAll((VBox)fxmlLoader.load(getClass().getResource("/view/forgetPassword.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void login()
    {
        StageCoordinator.getStageCoordinator().switchToChatScreen();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        userName.textProperty().bindBidirectional(userModel.userUserNameProperty());
        password.textProperty().bindBidirectional(userModel.userPasswordProperty());
        profileImage.imageProperty().bindBidirectional(userModel.userImageProperty());

    }

    public void onLoginBtnClicked(ActionEvent actionEvent) {
    }
}
