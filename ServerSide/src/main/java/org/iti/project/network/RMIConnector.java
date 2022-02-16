package org.iti.project.network;

import org.iti.project.services.impls.SignUpImpl;
import org.iti.project.services.interfaces.SignUpInt;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public void connectRMI(){
        try {
            SignUpInt SignUpObj = new SignUpImpl();
            Registry reg = LocateRegistry.getRegistry();
            reg.rebind(SignUpObj.lookupName, SignUpObj);
            System.out.println(" SignUp Bounded! ");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
