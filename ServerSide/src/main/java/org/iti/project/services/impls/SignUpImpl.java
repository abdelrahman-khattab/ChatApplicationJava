package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.services.interfaces.SignUpInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SignUpImpl extends UnicastRemoteObject implements SignUpInt {
    public SignUpImpl() throws RemoteException {

    }

    @Override
    public boolean registerMe(User user) throws RemoteException{
        System.out.println(user.getUserName() + " reached the service");
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean result = userDAO.insertUser(user);
        return result;
    }
}
