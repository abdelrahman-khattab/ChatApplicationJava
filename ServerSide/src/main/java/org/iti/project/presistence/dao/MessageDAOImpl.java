package org.iti.project.presistence.dao;
import org.iti.project.models.GroupMessage;

import org.iti.project.models.SingleMessage;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private  static MessageDAOImpl instance;

    public static MessageDAOImpl getInstance(){
        if(instance == null) {
            instance =  new MessageDAOImpl();
            return instance;
        }
        return instance;
    }
    @Override
    public void storeSingleMessage(SingleMessage message) {
        PreparedStatement pstmt = null;

        int is_underline = 0;

        try (Connection con =  DBConnector.getConnection().connect()){
            if(message.isFontUnderLine()){
                is_underline = 1;
            }
            pstmt = con.prepareStatement("insert into message(BODY,CREATOR_ID,RECEIPENT_ID,message_color,font_familly,font_posture,font_weight,font_size,font_underline) values(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1,message.getSingleMessageContent());
            pstmt.setString(2,message.getSender().getUserPhone());
            pstmt.setString(3,message.getReceiverPhoneNumber());
            pstmt.setString(4,message.getSingleMessageColor());
            pstmt.setString(5,message.getFontFamily());
            pstmt.setString(6,message.getFontPosture());
            pstmt.setString(7,message.getFontWeight());
            pstmt.setInt(8,message.getFontSize());
            pstmt.setInt(9,is_underline);
            boolean executed = pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void storeGroupMessage(GroupMessage message) {

        // starting database

        PreparedStatement pstmt = null;

        int is_underline = 0;

        try (Connection con =  DBConnector.getConnection().connect()){
            if(message.isFontUnderLine()){
                is_underline = 1;
            }

            pstmt = con.prepareStatement("insert into message(BODY,CREATOR_ID,GROUP_RECEIPENT_ID,message_color,font_familly,font_posture,font_weight,font_size,font_underline) values(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1,message.getGroupMessageContent());
            pstmt.setString(2,message.getSender().getUserPhone());
            pstmt.setInt(3,message.getGroupId());
            pstmt.setString(4,message.getGroupMessageColor());
            pstmt.setString(5,message.getFontFamily());
            pstmt.setString(6,message.getFontPosture());
            pstmt.setString(7,message.getFontWeight());
            pstmt.setInt(8,message.getFontSize());
            pstmt.setInt(9,is_underline);
            boolean executed = pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ending database

    }


    @Override
    public List<SingleMessage> restoreSingleMessages(String senderPhone , String receiverPhone) {
        List<SingleMessage> singleMessageHistory = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try(Connection con = DBConnector.getConnection().connect()) {
            pstmt = con.prepareStatement("select * from message where (CREATOR_ID = ? and RECEIPENT_ID = ?) || (CREATOR_ID = ? and RECEIPENT_ID = ? ) order by MESSAGE_ID DESC   limit 15;");
            pstmt.setString(1,senderPhone);
            pstmt.setString(2,receiverPhone);
            pstmt.setString(3,receiverPhone);
            pstmt.setString(4,senderPhone);

            resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                User sender = new User();
                SingleMessage singleMessage = new SingleMessage();
                singleMessage.setMessageId(resultSet.getInt(1));
                singleMessage.setSingleMessageContent(resultSet.getString(2));
                singleMessage.setMessageCreationTime(resultSet.getTimestamp(3).toLocalDateTime());
                sender.setUserPhone(resultSet.getString(4));
                singleMessage.setSender(sender);
                singleMessage.setReceiverPhoneNumber(resultSet.getString(5));
                singleMessage.setSingleMessageColor(resultSet.getString(7));
                singleMessage.setFontFamily(resultSet.getString(8));
                singleMessage.setFontPosture(resultSet.getString(9));
                singleMessage.setFontWeight(resultSet.getString(10));
                singleMessage.setFontSize(resultSet.getInt(11));
                singleMessage.setFontUnderLine(resultSet.getBoolean(12));

                // adding all Single Message Chat to List
                singleMessageHistory.add(singleMessage);

            }


            singleMessageHistory.sort((a,b)->{
             return a.getMessageId()-b.getMessageId();
        });




        } catch (SQLException e) {
            e.printStackTrace();
        }

        // to return all messages with descending order
        return singleMessageHistory;

    }
     public List<GroupMessage> restoreGroupMessages(int groupReceipentId){
        //start of group message implementation
        List<GroupMessage> groupMessageHistory = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try(Connection con = DBConnector.getConnection().connect()) {
            pstmt = con.prepareStatement("SELECT USER.USER_NAME, MESSAGE.* FROM MESSAGE, USER WHERE MESSAGE.GROUP_RECEIPENT_ID = ? AND USER.PHONE_NUMBER = MESSAGE.CREATOR_ID ORDER BY  MESSAGE_ID DESC   limit 10;");
            pstmt.setInt(1,groupReceipentId);
            resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                User sender = new User();
                GroupMessage groupMessage = new GroupMessage();
                sender.setUserName(resultSet.getString(1));
                groupMessage.setMessageId(resultSet.getInt(2));
                groupMessage.setGroupMessageContent(resultSet.getString(3));
                groupMessage.setMessageCreationTime(resultSet.getTimestamp(4).toLocalDateTime());
                sender.setUserPhone(resultSet.getString(5));
                groupMessage.setSender(sender);
                groupMessage.setGroupId(resultSet.getInt(7));
                groupMessage.setGroupMessageColor(resultSet.getString(8));
                groupMessage.setFontFamily(resultSet.getString(9));
                groupMessage.setFontPosture(resultSet.getString(10));
                groupMessage.setFontWeight(resultSet.getString(11));
                groupMessage.setFontSize(resultSet.getInt(12));
                groupMessage.setFontUnderLine(resultSet.getBoolean(13));

                // adding all Single Message Chat to List
                groupMessageHistory.add(groupMessage);

            }



            // sorting the message to display in descending order

//            System.out.println(groupMessageHistory);
            groupMessageHistory.sort((a,b)->{
                return a.getMessageId() - b.getMessageId();
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // to return all messages with descending order
        return groupMessageHistory;

        // ending restore group messages
    }


}
