package org.iti.project.network;

import org.iti.project.services.impls.ChatServiceImpl;
import org.iti.project.services.impls.LogInImpl;
import org.iti.project.services.impls.SignUpImpl;
import org.iti.project.services.interfaces.ChatServiceInt;
import org.iti.project.services.interfaces.LogInInt;
import org.iti.project.services.interfaces.SignUpInt;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public void connectRMI(){
        try {

            SignUpInt signUpObj = new SignUpImpl();
            LogInInt logInObj = new LogInImpl();
            ChatServiceInt chatServiceObj = new ChatServiceImpl();

            Naming.rebind(signUpObj.lookupName, signUpObj);
            System.out.println(" SignUp Bounded! ");
            Naming.rebind(logInObj.lookupName, logInObj);
            System.out.println(" loginUp Bounded! ");
            Naming.rebind(chatServiceObj.lookupName, chatServiceObj);
            System.out.println(" chatService Bounded! ");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
