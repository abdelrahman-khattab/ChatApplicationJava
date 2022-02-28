package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateClientInt extends Remote{
    public String lookupName = "UpdateService";
    public boolean updateMe(User user) throws RemoteException;

}
