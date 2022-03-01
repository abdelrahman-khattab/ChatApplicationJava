package org.iti.project.network;

import org.iti.project.models.User;
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
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;

public class RMIConnector {
    private RMIConnector rmiConnector;
    private Registry reg;
    private SignUpInt signUpObj;
    private SignInInt signInObj;
    private LogInInt logInObj;
    private ChatServiceInt chatServiceObj;
    private SignOutInt signOutObj ;
    private ContactInt contactObj;
    private UpdateClientInt updateObj;
    private GroupInt groupObj ;
    private static RMIConnector INSTANCE = new RMIConnector();

    private RMIConnector()
    {}
   static   public RMIConnector getRmiConnector()
    {
       return INSTANCE;
    }
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

                signUpObj = new SignUpImpl();
                signInObj = new SignInImpl();
                logInObj = new LogInImpl();
                chatServiceObj = new ChatServiceImpl();
                signOutObj = new SignOutImpl();
                contactObj=new ContactImpl();
                updateObj = new UpdateClientImpl();
                groupObj = new GroupImpl();

                reg.rebind(signUpObj.lookupName, signUpObj);
                System.out.println(" SignUp Bounded! ");
                reg.rebind(logInObj.lookupName, logInObj);
                System.out.println(" loginUp Bounded! ");
                reg.rebind(chatServiceObj.lookupName, chatServiceObj);
                System.out.println(" chatService Bounded! ");
                reg.rebind(signInObj.lookupName, signInObj);
                System.out.println(" signInService Bounded! ");
                reg.rebind(signOutObj.lookupName, signOutObj);
                System.out.println(" chatSignOut Service Bounded! ");
                reg.rebind(contactObj.lookupName,contactObj);
                System.out.println("ContactService Bounded");
                reg.rebind(updateObj.lookupName,updateObj);
                System.out.println("UpdateService Bounded");
                reg.rebind(groupObj.lookupName,groupObj);
                System.out.println("GroupService Bounded");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }


    }

    public void closeConncetion()
    {
        try {
            reg.unbind(signUpObj.lookupName);
            reg.unbind(logInObj.lookupName);
            reg.unbind(chatServiceObj.lookupName);
            reg.unbind(signInObj.lookupName);
            reg.unbind(signOutObj.lookupName);
            reg.unbind(contactObj.lookupName);
            reg.unbind(updateObj.lookupName);
            reg.unbind(groupObj.lookupName);
            UnicastRemoteObject.unexportObject(chatServiceObj,true);
            UnicastRemoteObject.unexportObject(signUpObj,true);
            UnicastRemoteObject.unexportObject(signInObj,true);
            UnicastRemoteObject.unexportObject(signOutObj,true);
            UnicastRemoteObject.unexportObject(contactObj,true);
            UnicastRemoteObject.unexportObject(updateObj,true);
            UnicastRemoteObject.unexportObject(groupObj,true);
            UnicastRemoteObject.unexportObject(logInObj,true);
            System.out.println("unbind done");
        } catch (Exception e) {
         //   LOG.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
            }
    }
}
