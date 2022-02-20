package org.iti.project.services.interfaces;

import org.iti.project.network.RMIConnector;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class SignInServices {
    private SignUpInt signUpService;
    Registry reg;
    private SignInInt signInService;
    public SignInServices()
    {
        reg = RMIConnector.getRmiConnector().getReg();

    }
    public SignInInt getLoginService(){
        try {
            return (SignInInt) reg.lookup(signInService.lookupName);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();//should be handling
        }

        return null;
    }
    // signUpService = (SignUpInt) reg.lookup(signUpService.lookupName);

}
