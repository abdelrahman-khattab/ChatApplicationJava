package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.ContactDAOImpl;
import org.iti.project.services.interfaces.ContactInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ContactImpl extends UnicastRemoteObject implements ContactInt {
    public ContactImpl() throws RemoteException {
    }

    @Override
    public ArrayList<User> getContact(User user) throws RemoteException {
        ContactDAOImpl contactDAO=new ContactDAOImpl();
        ArrayList<User> contacts=contactDAO.selectUser(user.getUserPhone());

        return  contacts;
    }


}
