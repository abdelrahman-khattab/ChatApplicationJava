package org.iti.project.services.impls;

import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import javafx.collections.ObservableList;
import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.models.User;
import org.iti.project.presentation.controllers.ChatScreenController;
import org.iti.project.presentation.controllers.FriendRequestNotificationController;
import org.iti.project.presentation.controllers.SideContactListController;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.services.interfaces.ClientCallBackInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientCallBack extends UnicastRemoteObject implements ClientCallBackInt {
    private ChatScreenController chatScreenController;
    private final StageCoordinator stageCoordinator =  StageCoordinator.getStageCoordinator(); //this is would be null if used before creating the chat screen.

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
}
