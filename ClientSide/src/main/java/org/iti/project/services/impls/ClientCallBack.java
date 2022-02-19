package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presentation.controllers.ChatScreenController;
import org.iti.project.services.interfaces.ClientCallBackInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCallBack extends UnicastRemoteObject implements ClientCallBackInt {
    private final ChatScreenController chatScreenController =  ChatScreenController.getInstance(); //this is would be null if used before creating the chat screen.

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
    public void receiveGroupMessage(String message, int groupId, User user) {

    }
}
