package org.iti.project.services.interfaces;

import java.rmi.*;

public interface SignUpInt extends Remote{
    public String lookupName = "SignUpService";
    public boolean registerMe(String name) throws RemoteException;
}
