package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SignInInt extends Remote {
    public String lookupName = "SignInService";
    public User loginMe(User user , ClientCallBackInt clientCallBackInt) throws RemoteException;
}