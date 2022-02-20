package org.iti.project.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
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
import org.iti.project.presentation.util.Validator;
import org.iti.project.services.interfaces.ClientCallBackInt;
import org.iti.project.services.interfaces.SignInServices;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    UserModel userModel;
    @FXML
    private PasswordField password;


    @FXML
    private Text validationText;

    @FXML
    private Button btnLogin;

    @FXML
    private FontAwesomeIconView passwordIcon;

    @FXML
    private ImageView profileImage;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private TextField userPhone;

    @FXML
    private VBox vBox;
    boolean passFlag =false , phNoFlag=false;
    private User mainUser= new User();
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
    void checkLoginFields()
    {
       if(passFlag==false){
            btnLogin.setDisable(true);

        }
        else if(phNoFlag==false){
            btnLogin.setDisable(true);

        }
        else{
           btnLogin.setDisable(false);
       }
    }

    @FXML
    void enterPhoneNumber(KeyEvent event) {
        if (!Validator.phoneValidation(userPhone.getText())) {
            userPhone.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
             phNoFlag=false;
        } else {
            userPhone.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            phNoFlag=true;
        }
        checkLoginFields();
    }
    @FXML
    void enterPassword(KeyEvent event) {
        if(password.getText().equals(""))
        {
            passFlag=false;
        }
        else
        {
            passFlag=true;
        }
        checkLoginFields();
    }
    @FXML
    void login()
    {
        User user = null;
        System.out.println(userPhone.getText());
        mainUser.setUserPhone(userPhone.getText());
        mainUser.setUserPassword(password.getText());
        ClientCallBackInt clientCallBackInt=null;
        try {
            user = RMIConnector.getRmiConnector().getSignInService().loginMe(mainUser ,clientCallBackInt );
       //     System.out.println(user.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(user!=null)
        {
            StageCoordinator.getStageCoordinator().switchToChatScreen();

        }
        else {
            validationText.setVisible(true);

            //System.out.println("user not found in table login with different Phone Number And Password");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        userPhone.textProperty().bindBidirectional(userModel.phoneNoProperty());
        password.textProperty().bindBidirectional(userModel.userPasswordProperty());
        profileImage.imageProperty().bindBidirectional(userModel.userImageProperty());
        btnLogin.setDisable(true);
        validationText.setVisible(false);
    }

    public void onLoginBtnClicked(ActionEvent actionEvent) {
    }
}
