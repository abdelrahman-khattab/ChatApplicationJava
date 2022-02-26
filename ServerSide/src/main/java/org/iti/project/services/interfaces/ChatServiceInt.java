package org.iti.project.services.interfaces;

import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServiceInt extends Remote {
    public String lookupName = "ChatService";
    public void sendGroupMessage(GroupMessage groupMessage) throws RemoteException;
    public void sendSingleMessage(SingleMessage singleMessage) throws RemoteException;

    public List<SingleMessage> fetchSingleMessageHistory(String senderPhone, String receiverPhone) throws RemoteException;
    public List<GroupMessage> fetchGroupMessageHistory(int groupReceipentId) throws RemoteException;

    public void sendFile(String senderName , String receiverPhone, byte[] sentFileAsBytes ,String fileName) throws RemoteException;
//    public boolean isOnline(String phoneNumber);

}
