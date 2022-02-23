package org.iti.project.presistence.dao;


import org.iti.project.models.SingleMessage;
import org.iti.project.presistence.entities.Message;
import org.iti.project.presistence.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {
    DBConnector conn;
    @Override
    public void insertMessage(Message message) {

    }

    @Override
    public Message selectMessage(Message message) {
        return null;
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
