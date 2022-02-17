package org.iti.project.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
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
import org.controlsfx.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;


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
    @FXML
    void onRegisterButtonClicked(ActionEvent event) {



    /////////////////////////////////////
        /////////ahmed ashraf hyktb hena ////
        User user = new User();
        user.setGender("asdas");
        user.setUserCountry("asdas");
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            user.setImage(fileContent);
            System.out.println("image set" + fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setUserEmail("asdas");
        user.setUserName("ali");
        user.setUserPassword("asdas");
        user.setUserDOB("asdas");
        user.setUserPhone("01223657582");
        user.setUserBio("jjj");
        boolean added = false;

        try {
            added = RMIConnector.getRmiConnector().getChatService().registerMe(user);
        } catch (RemoteException e) {
            Alert remoteExceptionAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            remoteExceptionAlert.showAndWait();
        }
        if(added) {
            Notifications.create()
                    .title("Registration")
                    .text("Congrats! You are one of us now, go login talk to your friends!").position(Pos.TOP_CENTER)
                    .showConfirm();

        }
        //
         }
}
