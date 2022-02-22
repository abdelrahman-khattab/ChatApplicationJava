package org.iti.project.services.interfaces;

import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBackInt extends Remote {
    public void receiveGroupMessage(GroupMessage groupMessage) throws RemoteException;
    public void receiveSingleMessage(SingleMessage singleMessage) throws RemoteException;
}
