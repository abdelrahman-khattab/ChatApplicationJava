package org.iti.project.services.interfaces;

import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServiceInt extends Remote {

    public String lookupName = "ChatService";

    // sending messages to the single and group chat and store them in DATABASE
    public void sendGroupMessage(GroupMessage groupMessage) throws RemoteException;
    public void sendSingleMessage(SingleMessage singleMessage) throws RemoteException;

    // getting all messages from the single and group chat to displey them in the CHATBOX with entire style
    public List<SingleMessage> fetchSingleMessageHistory(String senderPhone, String receiverPhone) throws RemoteException;
    public List<GroupMessage> fetchGroupMessageHistory(int groupReceipentId) throws RemoteException;

}
