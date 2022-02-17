package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

public class SignUpImpl extends UnicastRemoteObject implements SignUpInt {
    public SignUpImpl() throws RemoteException {
    }

    @Override
    public User registerMe(User user) throws RemoteException{
        System.out.println(user.getUserName() + " reached the service");
        Connection conn = DBConnector.getConnection().connect();
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.insertUser(user);
        return user;
    }
}
