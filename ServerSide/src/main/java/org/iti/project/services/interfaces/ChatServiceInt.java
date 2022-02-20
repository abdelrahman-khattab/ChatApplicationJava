package org.iti.project.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServiceInt extends Remote {
    public String lookupName = "ChatService";
    public void sendGroupMessage(String message , int groupId) throws RemoteException;
}
