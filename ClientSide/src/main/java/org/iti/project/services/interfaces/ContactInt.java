package org.iti.project.services.interfaces;

import org.iti.project.models.Contact;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContactInt extends Remote {
    public String lookupName = "ContactService";
    public void addContact(Contact contact) throws RemoteException;
    public Contact selectContact(Contact contact) throws RemoteException;
    //ASK for how to update contact...
//    public boolean updateContact(Contact contact);
    public boolean deleteContact(Contact contact) throws RemoteException;
}
