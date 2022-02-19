package org.iti.project.services.impls;

import org.iti.project.services.interfaces.ClientCallBackInt;
import org.iti.project.services.interfaces.LogInInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class LogInImpl extends UnicastRemoteObject implements LogInInt {
    private static HashMap<String, ClientCallBackInt> onlineClients = new HashMap<>();

    public static HashMap<String, ClientCallBackInt> getOnlineClients(){
        return onlineClients;
    }

    public LogInImpl() throws RemoteException {
    }

    @Override
    public void logIn(String userPhoneNumber, ClientCallBackInt clientCallBack) throws RemoteException {

        onlineClients.put(userPhoneNumber, clientCallBack);
    }


}
