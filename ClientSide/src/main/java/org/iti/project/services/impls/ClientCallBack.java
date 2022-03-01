package org.iti.project.services.impls;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.Notifications;
import javafx.collections.ObservableList;
import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.models.User;
import org.iti.project.presentation.controllers.ChatScreenController;
import org.iti.project.presentation.controllers.FriendRequestNotificationController;
import org.iti.project.presentation.controllers.SideContactListController;
import org.iti.project.presentation.controllers.SideServerAnnouncementController;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.services.interfaces.ClientCallBackInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;

public class ClientCallBack extends UnicastRemoteObject implements ClientCallBackInt {
    private ChatScreenController chatScreenController;
    private final StageCoordinator stageCoordinator =  StageCoordinator.getStageCoordinator(); //this is would be null if used before creating the chat screen.
    public static ArrayList<String> announcementMessageList = new ArrayList<>();
    private ClientCallBack() throws RemoteException {
    }

    private static ClientCallBack instance;

    public static ClientCallBack getInstance() {
        if (instance == null) {
            try {
                instance = new ClientCallBack();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    @Override
    public void receiveGroupMessage(GroupMessage groupMessage) {
        Platform.runLater(()-> {
            stageCoordinator.getChatScreenController().handleGroupMessage(groupMessage);
        });
    }

    @Override
    public void receiveSingleMessage(SingleMessage singleMessage) {
        Platform.runLater(()-> {
            stageCoordinator.getChatScreenController().handleSingleMessage(singleMessage);
        });
    }

    @Override
    public void receiveFile(String senderName, byte[] sentFile, String fileName) {
        Platform.runLater(()-> {
            stageCoordinator.getChatScreenController().handleReceivedFile(senderName , sentFile , fileName);
        });

    }

    @Override
    public void updateFriendAvailability(String friendName, String friendPhone, boolean onlineFlag) {
        Platform.runLater(()->{
            stageCoordinator.getChatScreenController().handleFriendAvailability(friendName,friendPhone,onlineFlag);
        });
    }

    @Override
    public void getRequestListOfFriends(ArrayList<User> requestList) throws RemoteException
    {
        //assign This array with the contact list array
        FriendRequestNotificationController.contactObservableList.addAll(requestList);

    }

    @Override
    public void closeApplicationForUnbinding() throws RemoteException {
        Platform.runLater(()->{
            Alert a = new Alert(Alert.AlertType.NONE);

            a.setAlertType(Alert.AlertType.ERROR);

            // show the dialog

            a.setHeaderText("Server is down");
            a.showAndWait();
            //Optional<ButtonType> alertResult = a.showAndWait();
            // StageCoordinator.getStageCoordinator().switchToLoginFormScene();
            System.exit(0);

        });

    }

    @Override
    public void adminAnnouncement(String message) throws RemoteException {
        announcementMessageList.add(message);

    }
}
