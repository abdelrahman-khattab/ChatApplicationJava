package org.iti.project.services.interfaces;

import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientCallBackInt extends Remote {
    public void receiveGroupMessage(GroupMessage groupMessage) throws RemoteException;
    public void receiveSingleMessage(SingleMessage singleMessage) throws RemoteException;
    public void receiveFile(String senderName, byte[] sentFile , String fileName) throws RemoteException;
    public void updateFriendAvailability(String friendName, String friendPhone , boolean onlineFlag) throws RemoteException;
    public void getRequestListOfFriends(ArrayList<User> requestList) throws RemoteException;
    public void closeApplicationForUnbinding() throws RemoteException;
    public void adminAnnouncement(String message) throws RemoteException;

}
