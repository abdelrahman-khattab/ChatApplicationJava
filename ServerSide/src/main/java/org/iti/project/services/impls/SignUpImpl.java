package org.iti.project.services.impls;

import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SignUpImpl extends UnicastRemoteObject implements SignUpInt {
    public SignUpImpl() throws RemoteException {
    }

    @Override
    public boolean registerMe(String name) throws RemoteException{
        return false;
    }
}
