package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.services.interfaces.UpdateClientInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UpdateClientImpl  extends UnicastRemoteObject implements UpdateClientInt {

    public UpdateClientImpl() throws RemoteException {
    }

    @Override
    public boolean updateMe(User user) throws RemoteException {
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean result = userDAO.updateUser(user);

        return result;

    }
}
