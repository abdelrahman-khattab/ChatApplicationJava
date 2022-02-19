package org.iti.project.services.interfaces;

import org.iti.project.models.User;

import java.rmi.Remote;

public interface ClientCallBackInt extends Remote {
    public void receiveGroupMessage(String message , int groupId , User user);
}
