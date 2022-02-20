package org.iti.project.services.interfaces;


import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LogInInt extends Remote{
    public String lookupName = "LoginServiceService";
    public void logIn(String userPhoneNumber , ClientCallBackInt clientCallBack) throws RemoteException;
}
