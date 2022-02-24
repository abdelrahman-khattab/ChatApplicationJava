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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        List<String> userPhones = new ArrayList<>();
             userPhones = groupDAO.findUsersPhoneByGroupId(groupId);
//        try {
//            PreparedStatement psttmnt = con.prepareStatement("select user_id from user_group where group_id = ?");
//            psttmnt.setInt(1,groupId);
//            ResultSet res = psttmnt.executeQuery();
//            while (res.next()){
//                String userPhoneNumber = res.getString(1);
//                userPhones.add(userPhoneNumber);
//                System.out.println("we found friends for you to send them your message with phone: "+ userPhoneNumber + groupId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        if(userPhones != null && userPhones.size()>0){
            for (String userPhone : userPhones){
                ClientCallBackInt clientCallBack =  onlineClients.get(userPhone);
                if (clientCallBack != null ){
                    clientCallBack.receiveGroupMessage(groupMessage);
                }
            }
        }
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
        clientCallBack = onlineClients.get(senderPhoneNumber);
        if (clientCallBack != null ){
            clientCallBack.receiveSingleMessage(singleMessage);
        }

    }

    @Override
    public List<SingleMessage> fetchSingleMessageHistory(String senderPhone, String receiverPhone){
        List<SingleMessage> singleMessageHistory = new ArrayList<>();

        return singleMessageHistory;
    }


}
