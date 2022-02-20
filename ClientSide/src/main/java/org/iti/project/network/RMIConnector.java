package org.iti.project.network;


import org.iti.project.services.interfaces.ChatServiceInt;
import org.iti.project.services.interfaces.LogInInt;
import org.iti.project.services.interfaces.SignInInt;
import org.iti.project.services.interfaces.SignOutInt;
import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private SignUpInt signUpService;
    private LogInInt loginService;  //// not needed I think
    private ChatServiceInt chatService;
    private SignInInt signInService;
    private SignOutInt signOutService;
    private Registry reg;
    private static  RMIConnector rmiConnector = new RMIConnector();

//    private Registry reg;
//
//    private static final RMIConnector rmiConnector = new RMIConnector();
//
//    private RMIConnector(){
//        try {
//            //Registry reg = LocateRegistry.getRegistry();
//            reg = LocateRegistry.getRegistry();
//            //signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);
//        } catch (RemoteException  e) { //| NotBoundException


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

    public ChatServiceInt getChattingService() {
        try {
            chatService = (ChatServiceInt) reg.lookup(chatService.lookupName);
            System.out.println(chatService.lookupName + " service found!");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return chatService;

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
