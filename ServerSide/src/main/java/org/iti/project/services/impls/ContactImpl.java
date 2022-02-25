package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.*;
import org.iti.project.services.interfaces.ClientCallBackInt;
import org.iti.project.services.interfaces.ContactInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactImpl extends UnicastRemoteObject implements ContactInt {
    private final HashMap<String, ClientCallBackInt> onlineClients;

    public ContactImpl() throws RemoteException {
        onlineClients = SignInImpl.getOnlineClients();
    }

    @Override
    public ArrayList<User> getContact(User user) throws RemoteException {
        ContactDAOImpl contactDAO=new ContactDAOImpl();
        ArrayList<User> contacts=contactDAO.selectContacts(user.getUserPhone());

        return  contacts;
    }

    @Override
    public boolean addContact(User requesterUser , User responserUser ) throws RemoteException {

            UserDAOImpl returnUser = new UserDAOImpl();
            User getUser = new User();
            getUser = returnUser.selectRowUser(responserUser);

            if(getUser!=null)
            {
                RequestDAO requestDAO = new RequestDAOImpl();
                requestDAO.insertRequest(requesterUser , responserUser);
                ClientCallBackInt clientCallBack = onlineClients.get(responserUser.getUserPhone());
                if (clientCallBack != null)
                {
                    ArrayList <User> requestList = new ArrayList<>();
                    requestList = requestDAO.selectListRequestUser(responserUser);
                    clientCallBack.getRequestListOfFriends(requestList);
                }
                return true;
            }
            else
            {
                return  false;
            }

    }

    @Override
    public void rejectContact(User mainUser, User secoundryUser) throws RemoteException {
        RequestDAO requestDAO = new RequestDAOImpl();
        requestDAO.deleteUser(mainUser , secoundryUser);
    }

    @Override
    public ArrayList<User> acceptContact(User mainUser, User secoundryUser) throws RemoteException {
       RequestDAO requestDAO = new RequestDAOImpl();
       ContactDAO contactDAO = new ContactDAOImpl();
       requestDAO.deleteUser(mainUser , secoundryUser);
       contactDAO.insertContact(mainUser,secoundryUser);
       contactDAO.insertContact(secoundryUser,mainUser);
        return contactDAO.selectContacts(mainUser.getUserPhone());
    }

    @Override
    public ArrayList<User> requestListFriends(User mainUser) throws RemoteException {
        RequestDAO requestDAO = new RequestDAOImpl();
        return requestDAO.selectListRequestUser(mainUser);

    }

}
