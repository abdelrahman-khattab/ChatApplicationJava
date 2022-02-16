package org.iti.project.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();

    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void onLoginBtnClicked(ActionEvent event) {
        stageCoordinator.switchToChatScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserModel userModel = modelFactory.getUserModel();
        userNameTextField.textProperty().bindBidirectional(userModel.userUserNameProperty());
        passwordPasswordField.textProperty().bindBidirectional(userModel.userPasswordProperty());


    }
    /*@FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("view/secondary");
    }*/


}
