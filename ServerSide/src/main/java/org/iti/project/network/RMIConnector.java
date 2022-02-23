package org.iti.project.network;

import org.iti.project.presistence.dao.ContactDAO;
import org.iti.project.presistence.dao.ContactDAOImpl;
import org.iti.project.presistence.dao.RequestDAO;
import org.iti.project.presistence.dao.RequestDAOImpl;
import org.iti.project.services.impls.*;
import org.iti.project.services.interfaces.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public void connectRMI(){
        try {

//<<<<<<< HEAD
//            SignUpInt signUpObj = new SignUpImpl();
//            LogInInt logInObj = new LogInImpl();
//            ChatServiceInt chatServiceObj = new ChatServiceImpl();
//=======
            SignUpInt signUpObj = new SignUpImpl();
            SignInInt signInObj = new SignInImpl();
            LogInInt logInObj = new LogInImpl();
            ChatServiceInt chatServiceObj = new ChatServiceImpl();
            SignOutInt signOutObj = new SignOutImpl();
            ContactInt contactObj=new ContactImpl();


//>>>>>>> 3d670fcfaf68ca90f7d9cd3e7567c4793bfe4e0d

            Naming.rebind(signUpObj.lookupName, signUpObj);
            System.out.println(" SignUp Bounded! ");
            Naming.rebind(logInObj.lookupName, logInObj);
            System.out.println(" loginUp Bounded! ");
            Naming.rebind(chatServiceObj.lookupName, chatServiceObj);
            System.out.println(" chatService Bounded! ");
            Naming.rebind(signInObj.lookupName, signInObj);
            System.out.println(" signinservice Bounded! ");
            Naming.rebind(signOutObj.lookupName, signOutObj);
            System.out.println(" chatsignout Service Bounded! ");
            Naming.rebind(contactObj.lookupName,contactObj);
            System.out.println("ContactService Bounded");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
