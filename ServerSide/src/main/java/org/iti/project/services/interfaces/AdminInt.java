package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface AdminInt extends Remote {
    public  HashMap<String,Integer> countOfGender() throws RemoteException;
    public  HashMap<String,Integer> countOfCountries() throws RemoteException;
    public boolean registerUser(User user) throws RemoteException;
}
