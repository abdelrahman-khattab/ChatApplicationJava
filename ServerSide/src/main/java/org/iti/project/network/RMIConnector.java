package org.iti.project.network;

import org.iti.project.services.impls.*;
import org.iti.project.services.interfaces.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private Registry reg;
    public void connectRMI(){
        try {


            reg = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            try {
                reg = LocateRegistry.getRegistry(1099);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        } finally {
            try{

                SignUpInt signUpObj = new SignUpImpl();
                SignInInt signInObj = new SignInImpl();
                LogInInt logInObj = new LogInImpl();
                ChatServiceInt chatServiceObj = new ChatServiceImpl();
                SignOutInt signOutObj = new SignOutImpl();
                ContactInt contactObj=new ContactImpl();

                reg.rebind(signUpObj.lookupName, signUpObj);
                System.out.println(" SignUp Bounded! ");
                reg.rebind(logInObj.lookupName, logInObj);
                System.out.println(" loginUp Bounded! ");
                reg.rebind(chatServiceObj.lookupName, chatServiceObj);
                System.out.println(" chatService Bounded! ");
                reg.rebind(signInObj.lookupName, signInObj);
                System.out.println(" signinservice Bounded! ");
                reg.rebind(signOutObj.lookupName, signOutObj);
                System.out.println(" chatsignout Service Bounded! ");
                reg.rebind(contactObj.lookupName,contactObj);
                System.out.println("ContactService Bounded");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    }
}
