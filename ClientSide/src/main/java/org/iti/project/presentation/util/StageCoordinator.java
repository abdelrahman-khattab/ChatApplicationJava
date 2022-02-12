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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();

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
        switchToLoginScene();
        //switchToChatScreen();
    }

    public void switchToLoginScene(){
        Scene loginScene = sceneMap.get("loginScene");
        //Parent root = null;
        if (loginScene == null){}
        try {
            Pane root = FXMLLoader.load(getClass().getResource("/view/chatForms.fxml"));
            System.out.println("login scene created");
            loginScene = new Scene(root);
            sceneMap.put("loginScene", loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginScene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(loginScene);
        primaryStage.setFullScreen(false);
    }

    public void switchToChatScreen() {
        Scene chatScene = sceneMap.get("chatScene");
        //Parent root = null;
        if (chatScene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/ChatScreen.fxml"));
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
