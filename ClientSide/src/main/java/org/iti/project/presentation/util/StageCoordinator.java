package org.iti.project.presentation.util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.controllers.ChatScreenController;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.services.impls.ClientCallBack;
import org.iti.project.util.ImageConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();
    public User currentUser;
    private ChatScreenController chatScreenController;

    public ChatScreenController getChatScreenController() {
        return chatScreenController;
    }

    private Stage primaryStage;
    private final Map<String, Scene> sceneMap = new HashMap<>();


    public static StageCoordinator getStageCoordinator() {
        return stageCoordinator;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Map<String, Scene> getSceneMap() {
        return sceneMap;
    }

    public void initStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(600);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        if( Files.exists(Paths.get("credentials.txt"))){
            try {
                FileReader fr = new FileReader("credentials.txt");
                BufferedReader bufferedReader = new BufferedReader(fr);
                String userMobile = bufferedReader.readLine();
                String password = bufferedReader.readLine();
                System.out.println("Username : " + userMobile);
                System.out.println("Password : " + password);
                User returnedUser = new User();
                returnedUser = authenticate(userMobile , password);
                if(returnedUser!=null)
                {
                    currentUser = returnedUser;
                    updateUserModel(returnedUser);
                    switchToChatScreen();

                }
                else {
                    switchToLoginFormScene();
                    //System.out.println("user not found in table login with different Phone Number And Password");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            switchToLoginFormScene();
        }
    }

    public void switchToLoginFormScene(){
        Scene loginFormScene = sceneMap.get("loginFormScene");
        //Parent root = null;
        if (loginFormScene == null){}
        try {
            Pane root = FXMLLoader.load(getClass().getResource("/view/chatForms.fxml"));
            System.out.println("login form scene created");
            loginFormScene = new Scene(root);
            sceneMap.put("loginFormScene", loginFormScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginFormScene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(loginFormScene);
        primaryStage.setFullScreen(false);
    }

    public void switchToChatScreen() {
        Scene chatScene = sceneMap.get("chatScene");
        //Parent root = null;
        if (chatScene == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChatScreen.fxml"));
                Pane root = fxmlLoader.load();
//                ChatScreenController chatScreenController = fxmlLoader.getController();
                ChatScreenController.setController(fxmlLoader.getController());
                chatScreenController = ChatScreenController.getInstance();
                //Parent root = FXMLLoader.load(getClass().getResource("/view/ChatScreen.fxml"));
                System.out.println("chat scene created");
                chatScene = new Scene(root);
                sceneMap.put("chatScene", chatScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(chatScene);
        primaryStage.setFullScreen(true);
    }

    private User authenticate(String phoneNumber, String password){
        User returnedUser = null;
        User loginUser = new User();
        loginUser.setUserPhone(phoneNumber);
//        password encryption...
//        String encryptedPass = PasswordEncryptor.encrypt(password.getText());
//        mainUser.setUserPassword(encryptedPass);
        loginUser.setUserPassword(password);
        try {
            returnedUser = RMIConnector.getRmiConnector().getSignInService().loginMe(loginUser , ClientCallBack.getInstance() );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return returnedUser;
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
