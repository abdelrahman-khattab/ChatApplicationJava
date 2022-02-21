package org.iti.project.services.impls;

import org.iti.project.models.GroupMessage;
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
    private HashMap<String, ClientCallBackInt> onlineClients;
    public ChatServiceImpl() throws RemoteException { // it was protected modifier , return it as it was and test
        onlineClients = SignInImpl.getOnlineClients();
    }

    @Override
    public void sendGroupMessage(GroupMessage groupMessage, int groupId) throws RemoteException {
        System.out.println("your message received from server");
        List<String> userPhones = new ArrayList<>();
        try {
            PreparedStatement psttmnt = con.prepareStatement("select user_id from user_group where group_id = ?");
            psttmnt.setInt(1,groupId);
            ResultSet res = psttmnt.executeQuery();
            while (res.next()){
                String userPhoneNumber = res.getString(1);
                userPhones.add(userPhoneNumber);
                System.out.println("we found friends for you to send them your message with phone: "+ userPhoneNumber + groupId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(userPhones != null && userPhones.size()>0){
            for (String userPhone : userPhones){
                ClientCallBackInt clientCallBack =  onlineClients.get(userPhone);
                System.out.println("no problem here");
                System.out.println(clientCallBack);
                if (clientCallBack != null ){
                    clientCallBack.receiveGroupMessage(groupMessage);
                    System.out.println("we processed the sending for your friends");
                }
            }
        }
    }
}
