package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

public class SignUpImpl extends UnicastRemoteObject implements SignUpInt {

<<<<<<< HEAD

//    Connection conn = DBConnector.getConnection().connect();
=======
>>>>>>> 8e5030490f52a5d8f0dadaaec7fa03c9eec6aa80
    public SignUpImpl() throws RemoteException {

    }

    @Override
    public boolean registerMe(User user) throws RemoteException{
        System.out.println(user.getUserName() + " reached the service");
        //Connection conn = DBConnector.getConnection().connect();
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean result = userDAO.insertUser(user);
        return result;
    }
}
