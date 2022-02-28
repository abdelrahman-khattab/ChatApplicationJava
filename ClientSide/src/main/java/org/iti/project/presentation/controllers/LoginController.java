package org.iti.project.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
import org.iti.project.services.impls.ClientCallBack;
import org.iti.project.presentation.util.Validator;
import org.iti.project.util.ImageConverter;
import org.iti.project.util.PasswordEncryptor;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();
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
       if(!passFlag){
            btnLogin.setDisable(true);

        }
        else if(!phNoFlag){
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
        if(password.getText().isEmpty())
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
//<<<<<<< HEAD
//        try {
//            RMIConnector.getRmiConnector().getLoginService().logIn(userName.getText(), ClientCallBack.getInstance());
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        StageCoordinator.getStageCoordinator().switchToChatScreen();
//=======
        User returnedUser = null;
        System.out.println(userPhone.getText());
        mainUser.setUserPhone(userPhone.getText());
//        password encryption...
//        String encryptedPass = PasswordEncryptor.encrypt(password.getText());
//        mainUser.setUserPassword(encryptedPass);
        mainUser.setUserPassword(password.getText());
        try {
            returnedUser = RMIConnector.getRmiConnector().getSignInService().loginMe(mainUser ,ClientCallBack.getInstance() );
                  } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(returnedUser!=null)
        {
            StageCoordinator.getStageCoordinator().currentUser = returnedUser;
            updateUserModel(returnedUser);
            StageCoordinator.getStageCoordinator().switchToChatScreen();

        }
        else {
            validationText.setVisible(true);

            //System.out.println("user not found in table login with different Phone Number And Password");
        }
//>>>>>>> 3d670fcfaf68ca90f7d9cd3e7567c4793bfe4e0d
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhone.textProperty().bindBidirectional(userModel.phoneNoProperty());
//        password.textProperty().bindBidirectional(userModel.userPasswordProperty());
        profileImage.imageProperty().bindBidirectional(userModel.userImageProperty());
        btnLogin.setDisable(true);
        validationText.setVisible(false);
    }

    public void onLoginBtnClicked(ActionEvent actionEvent) {
    }

    private void updateUserModel(User user){
        userModel.setUserUserName(user.getUserName());
        userModel.setEmail(user.getUserEmail());
        userModel.setPhoneNo(user.getUserPhone());
        userModel.setUserGender(user.getGender());
        userModel.setUserCountry(user.getUserCountry());
        userModel.setUserPassword(user.getUserPassword());
        Image convertedimg = ImageConverter.fromBytesToImage(user.getImage());
        userModel.setUserImage(convertedimg);
    }
}
