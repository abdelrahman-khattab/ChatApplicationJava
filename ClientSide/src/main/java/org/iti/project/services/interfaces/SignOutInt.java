package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SignOutInt extends Remote {
    public String lookupName = "SignOutService";
    public void logoutMe(String userPhone) throws RemoteException;
}
