package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ContactInt extends Remote {
    public String lookupName = "ContactService";
    public ArrayList<User> getContact(User user) throws RemoteException;

}
