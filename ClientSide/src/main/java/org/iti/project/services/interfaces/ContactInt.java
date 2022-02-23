package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ContactInt extends Remote {
    public String lookupName = "ContactService";
    public ArrayList<User> getContact(User user) throws RemoteException;
    public boolean addContact(User requesterUser , User responserUser ) throws RemoteException;
    public void rejectContact(User mainUser , User secoundryUser) throws RemoteException;
    public ArrayList<User> acceptContact(User mainUser , User secoundryUser) throws RemoteException;

}
