package org.iti.project.presentation.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.iti.project.services.impls.SignInImpl;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ChatScreenController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
    private final Tooltip exitTip = new Tooltip("Exit");

    private  DashBoardController dashBoardController;
    private final Map<String, ScrollPane> paneMap = new HashMap<>();
    private  ScrollPane dashBoardPane;




        @FXML
        private Button chatListButton;

        @FXML
        private Button contactListButton;

        @FXML
        private ScrollPane firstPane;

    @FXML
    private ToggleButton serverStatusBtn;

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
    public DashBoardController getDashBoardController() {
        return dashBoardController;
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
            ScrollPane sideProfilePane = FXMLLoader.load(getClass().getResource("/view/announcement.fxml"));
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
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("/view/memberList.fxml"));
            ScrollPane sideProfilePane = loader.load();
            sideNavigationStackPane.getChildren().removeAll();
            sideNavigationStackPane.getChildren().add(sideProfilePane);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @FXML
        void onLogOutButtonClicked(ActionEvent event) {
            //stageCoordinator.switchToLoginScene();
        }

    @FXML
    void onProfileButtonClicked(ActionEvent event) {
        switchToProfilePane();
    }
    private void switchToProfilePane() {
        dashBoardPane =  paneMap.get("dashBoardPane");
        dashBoardPane.toFront();
        System.out.println(DashBoardController.getInstance()==null);
//        try {
//            FXMLLoader dashBoardLoader = new FXMLLoader(getClass().getResource("/view/sideProfilePane.fxml"));
//            ScrollPane dashBoardPane = dashBoardLoader.load();
//            dashBoardController.setController(dashBoardLoader.getController());
//            dashBoardController = dashBoardController.getInstance();
//            sideNavigationStackPane.getChildren().removeAll();
//            sideNavigationStackPane.getChildren().add(dashBoardPane);
//            System.out.println(DashBoardController.getInstance()==null);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
        try {
            FXMLLoader dashBoardLoader = new FXMLLoader(getClass().getResource("/view/sideProfilePane.fxml"));
            ScrollPane dashBoardPane = dashBoardLoader.load();
            dashBoardController.setController(dashBoardLoader.getController());
            dashBoardController = dashBoardController.getInstance();
            paneMap.put("dashBoardPane", dashBoardPane);
            sideNavigationStackPane.getChildren().add(dashBoardPane);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void addUnderline(ActionEvent actionEvent) {
    }
    @FXML
    void setServerSatuts(ActionEvent event) {
       //fireOnClientSide();

        if(serverStatusBtn.isSelected()){
            SignInImpl.getOnlineClients().forEach((k,v)-> {
                try {
                    v.closeApplicationForUnbinding();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
              RMIConnector.getRmiConnector().closeConncetion();

        }
        else{
             RMIConnector.getRmiConnector().connectRMI();
        }
    }

}