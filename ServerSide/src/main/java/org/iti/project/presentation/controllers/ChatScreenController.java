package org.iti.project.presentation.controllers;

import javafx.scene.layout.BorderPane;
import org.iti.project.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatScreenController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
    private final Tooltip exitTip = new Tooltip("Exit");



        @FXML
        private Button chatListButton;

        @FXML
        private Button contactListButton;

        @FXML
        private ScrollPane firstPane;

        @FXML
        private Button groupChatButton;

        @FXML
        private Button logOutButton;

        @FXML
        private Button profileButton;

        @FXML
        private ScrollPane secondPane;

        @FXML
        private StackPane sideNavigationStackPane;

        @FXML
        private Button unknownFunctionaityButton;

        @FXML
        void onChatListButtonClicked(ActionEvent event) {

            switchToChatListPane();
        }

    private void switchToChatListPane() {
        try {
            ScrollPane chatListScrollPane = FXMLLoader.load(getClass().getResource("/view/sideChatList.fxml"));
            sideNavigationStackPane.getChildren().removeAll();
            sideNavigationStackPane.getChildren().add(chatListScrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

        @FXML
        void onContactListButtonClicked(ActionEvent event) {
            switchToContactListPane();
        }

        private void switchToContactListPane() {
            try {
                BorderPane sideProfilePane = FXMLLoader.load(getClass().getResource("/view/announcement.fxml"));
                sideNavigationStackPane.getChildren().removeAll();
                sideNavigationStackPane.getChildren().add(sideProfilePane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void onGroupChatButtonClicked(ActionEvent event) {
            switchToGroupChat();
        }
        private void switchToGroupChat(){
            try {
                BorderPane sideProfilePane = FXMLLoader.load(getClass().getResource("/view/memberList.fxml"));
                sideNavigationStackPane.getChildren().removeAll();
                sideNavigationStackPane.getChildren().add(sideProfilePane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void onLogOutButtonClicked(ActionEvent event) {
            stageCoordinator.switchToLoginScene();
        }

        @FXML
        void onProfileButtonClicked(ActionEvent event) {
            switchToProfilePane();
        }
    private void switchToProfilePane() {
        try {
            ScrollPane sideProfilePane = FXMLLoader.load(getClass().getResource("/view/sideProfilePane.fxml"));
            sideNavigationStackPane.getChildren().removeAll();
            sideNavigationStackPane.getChildren().add(sideProfilePane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        @FXML
        void onUnownButtonClicked(ActionEvent event) {
            System.exit(0);

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tooltip exitTip = new Tooltip("Exit");
        //exitTip.show(stageCoordinator.getPrimaryStage());
        Tooltip.install(unknownFunctionaityButton, exitTip);


    }



}