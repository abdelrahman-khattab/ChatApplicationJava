package org.iti.project.network;

import org.iti.project.services.interfaces.ChatServiceInt;
import org.iti.project.services.interfaces.LogInInt;
import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private SignUpInt signUpService;
    private LogInInt loginService;
    private ChatServiceInt chatService;
    private Registry reg;

    private static final RMIConnector rmiConnector = new RMIConnector();

    private RMIConnector(){
        try {
            //Registry reg = LocateRegistry.getRegistry();
            reg = LocateRegistry.getRegistry();
            //signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);
        } catch (RemoteException  e) { //| NotBoundException
            e.printStackTrace();
        }

    }
    public static RMIConnector getRmiConnector(){
        if(rmiConnector != null)
            return rmiConnector;
        else return new RMIConnector();
    }
    public SignUpInt getSignUpService(){
        //getRmiConnector(); //
        try {
            signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);
            System.out.println(signUpService.lookupName + " service found!");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return signUpService;
    }
    public LogInInt getLoginService(){
        try {
            loginService = (LogInInt) reg.lookup(LogInInt.lookupName);
            System.out.println(loginService.lookupName + " service found!");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return loginService;
    }

    public ChatServiceInt getChattingService(){
        try {
            chatService = (ChatServiceInt) reg.lookup(chatService.lookupName);
            System.out.println(chatService.lookupName + " service found!");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return chatService;
    }
}
