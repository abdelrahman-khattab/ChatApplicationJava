package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMessageController implements Initializable {
    ModelFactory modelFactory = ModelFactory.getModelFactory();
    UserModel userModel = modelFactory.getUserModel();



    @FXML
    private ImageView userImage;

    @FXML
    private Label userMsgLabel;

    public void setUserMsgLabelMsg(String msg){
        userMsgLabel.setText(msg);
    }
    public Label getUserMsgLabel(){
        return userMsgLabel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userImage.imageProperty().bindBidirectional(userModel.userImageProperty());
    }
}
