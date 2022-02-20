package org.iti.project.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBackInt extends Remote {
    public void receiveGroupMessage(String message) throws RemoteException;
}