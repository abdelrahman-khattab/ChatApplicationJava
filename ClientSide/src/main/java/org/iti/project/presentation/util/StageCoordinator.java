package org.iti.project.presentation.util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.iti.project.presentation.controllers.ChatScreenController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
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
        switchToLoginFormScene();
        //switchToChatScreen();
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



}
