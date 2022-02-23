package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.ContactDAOImpl;
import org.iti.project.presistence.dao.UserDAOImpl;
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

    @Override
    public boolean addContact(User user) throws RemoteException {
        if (LogInImpl.getOnlineClients().containsKey(user.getUserPhone()))
        {
            //callbackcleint call Method send invitation

            //after the response if true(that add the two users in friend tables) false (mat3melsh 7aga wa fokak)
            //////
            //
            return true;
        }
        else if(!LogInImpl.getOnlineClients().containsKey(user.getUserPhone()))
        {
            //check in the database if the user is exist or not
            UserDAOImpl returnUser = new UserDAOImpl();
            User getUser = new User();
            getUser = returnUser.selectUser(user);
            if(getUser==null)
            {
                return false;
            }
            else if (getUser!=null)
            {
                //save to request /// create request DAO and Implements it
                return  true;
            }
            //if true save the request in request table
            //if false return false to the user side
        }
        //check it again
        return true;
    }


}
