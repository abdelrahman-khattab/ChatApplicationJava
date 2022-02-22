package org.iti.project.services.impls;

import org.iti.project.models.Contact;
import org.iti.project.presistence.dao.ContactDAOImpl;
import org.iti.project.services.interfaces.ContactInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ContactImpl extends UnicastRemoteObject implements ContactInt {
    public ContactImpl() throws RemoteException {
    }

    @Override
    public void addContact(Contact contact) throws RemoteException {
        ContactDAOImpl contactDAO=new ContactDAOImpl();
        List<String> contacts= (List<String>) contactDAO.selectUser(contact);
        for (String contactNum:contacts) {
            if(contactNum!=null && contactNum!=contact.getFriend_Id()){
                contactDAO.insertUser(contact);
            }
            else{
                System.out.println("contact already exist..");
            }

        }

    }

    @Override
    public Contact selectContact(Contact contact) throws RemoteException {
        return null;
    }

    @Override
    public boolean deleteContact(Contact contact) throws RemoteException {
        return false;
    }
}
