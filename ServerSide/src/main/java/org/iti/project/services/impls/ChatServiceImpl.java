package org.iti.project.services.impls;

import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.presistence.dao.GroupDAO;
import org.iti.project.presistence.dao.GroupDAOImpl;
import org.iti.project.presistence.dao.MessageDAO;
import org.iti.project.presistence.dao.MessageDAOImpl;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.services.interfaces.ChatServiceInt;
import org.iti.project.services.interfaces.ClientCallBackInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatServiceInt {
    Connection con = DBConnector.getConnection().connect();
    private final HashMap<String, ClientCallBackInt> onlineClients;
    private GroupDAO groupDAO = new GroupDAOImpl();
    private MessageDAO messageDAO = new MessageDAOImpl();
    public ChatServiceImpl() throws RemoteException { // it was protected modifier , return it as it was and test
        onlineClients = SignInImpl.getOnlineClients();
    }

    @Override
    public void sendGroupMessage(GroupMessage groupMessage) throws RemoteException {
        System.out.println("your groupMessage received from server");
        int groupId = groupMessage.getGroupId();
        
        List<String> usersPhones = new ArrayList<>();
             usersPhones = groupDAO.findUsersPhoneByGroupId(groupId);
//        try {
//            PreparedStatement psttmnt = con.prepareStatement("select user_id from user_group where group_id = ?");
//            psttmnt.setInt(1,groupId);
//            ResultSet res = psttmnt.executeQuery();
//            while (res.next()){
//                String userPhoneNumber = res.getString(1);
//                usersPhones.add(userPhoneNumber);
//                System.out.println("we found friends for you to send them your message with phone: "+ userPhoneNumber + groupId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        if(usersPhones != null && usersPhones.size()>0){
            for (String userPhone : usersPhones){
                ClientCallBackInt clientCallBack =  onlineClients.get(userPhone);
                if (clientCallBack != null && !userPhone.equals(groupMessage.getSender().getUserPhone())){
                    clientCallBack.receiveGroupMessage(groupMessage);
                }
            }
        }
        messageDAO.storeGroupMessage(groupMessage);
    }

    @Override
    public void sendSingleMessage(SingleMessage singleMessage) throws RemoteException {
        System.out.println("your groupMessage received from server");
        String receiverPhoneNumber = singleMessage.getReceiverPhoneNumber();
        System.out.println(receiverPhoneNumber + "this is receiver number from server");
        String senderPhoneNumber = singleMessage.getSender().getUserPhone();
        System.out.println(senderPhoneNumber + "this is sender number from server");
        ClientCallBackInt clientCallBack = onlineClients.get(receiverPhoneNumber);
        System.out.println(clientCallBack + "clientcall back of receiver");
        if (clientCallBack != null ){
            clientCallBack.receiveSingleMessage(singleMessage);
        }
//        clientCallBack = onlineClients.get(senderPhoneNumber);
//        if (clientCallBack != null ){
//            clientCallBack.receiveSingleMessage(singleMessage);
//        }
        messageDAO.storeSingleMessage(singleMessage);

    }

    @Override
    public List<SingleMessage> fetchSingleMessageHistory(String senderPhone, String receiverPhone){

        List<SingleMessage> singleMessageHistory = new ArrayList<>();

        // Get All Messages between sender and receiver and vice versa
        singleMessageHistory = messageDAO.restoreSingleMessages(senderPhone,receiverPhone);

        return singleMessageHistory;
    }
    @Override
    public List<GroupMessage> fetchGroupMessageHistory(int groupReceipentId){

        List<GroupMessage> groupMessageHistory = new ArrayList<>();

        // Get All Messages From Groups between sender and receiver and vice versa
        groupMessageHistory = messageDAO.restoreGroupMessages(groupReceipentId);

        return groupMessageHistory;
    }

    @Override
    public void sendFile(String senderName, String receiverPhone, byte[] sentFileAsBytes, String fileName) {
        if (isOnline(receiverPhone)){
            ClientCallBackInt clientCallBack = onlineClients.get(receiverPhone);
            try {
                clientCallBack.receiveFile(senderName , sentFileAsBytes , fileName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isOnline(String phoneNumber){
        ClientCallBackInt clientCallBack = onlineClients.get(phoneNumber);
        if (clientCallBack == null) return false;

        return true;
    }

}
