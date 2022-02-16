package org.iti.project.services.interfaces;


import org.iti.project.models.User;

import java.rmi.*;

public interface SignUpInt extends Remote{
    public String lookupName = "SignUpService";
    public User registerMe(User user) throws RemoteException;
}
