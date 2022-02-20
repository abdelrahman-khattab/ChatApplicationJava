package org.iti.project.network;

import org.iti.project.services.interfaces.SignInInt;
import org.iti.project.services.interfaces.SignOutInt;
import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private SignUpInt signUpService;
    private SignInInt signInService;
    private SignOutInt signOutService;
    private Registry reg;
    private static  RMIConnector rmiConnector = new RMIConnector();

    private RMIConnector(){
        try {
            reg = LocateRegistry.getRegistry();
            signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);
            signInService = (SignInInt) reg.lookup(signInService.lookupName);
            signOutService = (SignOutInt) reg.lookup(signOutService.lookupName);
            System.out.println(signUpService.lookupName + " service found!");
        } catch (RemoteException | NotBoundException e) {
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
        return signUpService;
    }

    public SignInInt getSignInService() {
        return signInService;
    }
    public SignOutInt getSignOutService() {
        return signOutService;
    }

    public Registry getReg() {
        return reg;
    }
}
