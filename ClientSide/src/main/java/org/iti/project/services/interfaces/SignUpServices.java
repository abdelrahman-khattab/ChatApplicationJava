package org.iti.project.services.interfaces;

import org.iti.project.network.RMIConnector;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class SignUpServices {
    private SignUpInt signUpService;
    Registry reg;
   public SignUpServices()
    {
       reg = RMIConnector.getRmiConnector().getReg();

    }
    public SignUpInt getChatService(){
        try {
            return (SignUpInt) reg.lookup(signUpService.lookupName);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();//should be handling
        }

        return null;
    }
   // signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);

}
