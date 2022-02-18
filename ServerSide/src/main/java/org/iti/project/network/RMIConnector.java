package org.iti.project.network;

import org.iti.project.services.impls.SignUpImpl;
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

            Naming.rebind(SignUpObj.lookupName, SignUpObj);

            System.out.println(" SignUp Bounded! ");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
