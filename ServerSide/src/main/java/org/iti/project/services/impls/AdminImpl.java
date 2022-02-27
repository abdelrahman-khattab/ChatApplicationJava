package org.iti.project.services.impls;

import org.iti.project.models.User;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.services.interfaces.AdminInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class AdminImpl extends UnicastRemoteObject implements AdminInt {

    public AdminImpl() throws RemoteException {
    }

    @Override
    public  HashMap<String,Integer> countOfGender() {
        HashMap<String,Integer> gender = new HashMap<>();
        UserDAOImpl userDAO = new UserDAOImpl();
        gender.putAll( userDAO.selectGender());
        return gender;
    }

    @Override
    public HashMap<String, Integer> countOfCountries() throws RemoteException {
        HashMap<String,Integer> countries = new HashMap<>();
        UserDAOImpl userDAO = new UserDAOImpl();
        countries.putAll( userDAO.selectUSerCountries());
        return countries;
    }
    @Override
    public boolean registerUser(User user) throws RemoteException{
        System.out.println(user.getUserName() + " reached the service");
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean result = userDAO.insertUser(user);
        return result;
    }

}
