package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBackInt extends Remote {
    public void receiveGroupMessage(String message) throws RemoteException;
}
