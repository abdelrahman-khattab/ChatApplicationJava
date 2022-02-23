package org.iti.project.presistence.dao;
import org.iti.project.models.GroupMessage;

import org.iti.project.models.SingleMessage;
import org.iti.project.presistence.util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<SingleMessage> restoreSingleMessages() {
        List<SingleMessage> singleMessageHistory = new ArrayList<>();
        PreparedStatement psttmnt = null;
        try(Connection con = DBConnector.getConnection().connect()) {
            psttmnt = con.prepareStatement("select ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singleMessageHistory;
    }

}
