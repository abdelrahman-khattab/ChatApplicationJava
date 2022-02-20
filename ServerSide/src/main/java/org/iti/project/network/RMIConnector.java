package org.iti.project.network;

import org.iti.project.services.impls.SignInImpl;
import org.iti.project.services.impls.SignOutImpl;
import org.iti.project.services.impls.SignUpImpl;
import org.iti.project.services.interfaces.SignInInt;
import org.iti.project.services.interfaces.SignOutInt;
import org.iti.project.services.interfaces.SignUpInt;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public void connectRMI(){
        try {

            SignUpInt SignUpObj = new SignUpImpl();
            SignInInt signInObj = new SignInImpl();
            SignOutInt signOutObj = new SignOutImpl();
            Naming.rebind(SignUpObj.lookupName, SignUpObj);
            Naming.rebind(signInObj.lookupName, signInObj);
            Naming.rebind(signOutObj.lookupName, signOutObj);

            System.out.println(" SignUp Bounded! ");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
