package org.iti.project.services.interfaces;

import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServiceInt extends Remote {
    public String lookupName = "ChatService";
    public void sendGroupMessage(GroupMessage groupMessage) throws RemoteException;
    public void sendSingleMessage(SingleMessage singleMessage) throws RemoteException;

}
