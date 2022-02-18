package org.iti.project.network;

import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private SignUpInt signUpService;
    private Registry reg;

    private static final RMIConnector rmiConnector = new RMIConnector();

    private RMIConnector(){
        try {
            //Registry reg = LocateRegistry.getRegistry();
            reg = LocateRegistry.getRegistry();
            //signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);
            System.out.println(signUpService.lookupName + " service found!");
        } catch (RemoteException  e) { //| NotBoundException
            e.printStackTrace();
        }

    }
    public static RMIConnector getRmiConnector(){
        if(rmiConnector != null)
            return rmiConnector;
        else return new RMIConnector();
    }
    public SignUpInt getChatService(){
        //getRmiConnector(); //
        try {
            signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return signUpService;
    }
}
