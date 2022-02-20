package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.services.interfaces.ClientCallBackInt;
import org.iti.project.services.interfaces.SignInInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class SignInImpl extends UnicastRemoteObject implements SignInInt {

    static HashMap<String, ClientCallBackInt> onlineClients = new HashMap<>();
    public SignInImpl() throws RemoteException {

    }


    @Override
    public User loginMe(User user , ClientCallBackInt clientCallBack) throws RemoteException {

        UserDAOImpl userDAO = new UserDAOImpl();

         user = userDAO.selectUser(user);
        if(user==null)
        {
            System.out.println("don't Find User");
            return null;
        }
        else{
            onlineClients.put(user.getUserPhone(), clientCallBack);
            System.out.println("user is add"+onlineClients.toString());
            return user;
        }

    }

    public static HashMap<String, ClientCallBackInt> getOnlineClients() {
        return onlineClients;
    }
}
