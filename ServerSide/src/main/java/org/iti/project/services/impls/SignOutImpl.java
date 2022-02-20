package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.services.interfaces.SignOutInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SignOutImpl extends UnicastRemoteObject implements SignOutInt {
    public SignOutImpl() throws RemoteException {
    }

    @Override
    public void logoutMe(String userPhone) throws RemoteException {
        System.out.println("the user remove with number phone : "+userPhone);
        SignInImpl.onlineClients.remove(userPhone);
    }
}
