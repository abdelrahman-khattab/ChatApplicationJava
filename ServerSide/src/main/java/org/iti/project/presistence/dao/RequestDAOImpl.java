package org.iti.project.presistence.dao;

import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.*;

public class RequestDAOImpl implements RequestDAO{
    @Override
    public boolean insertRequest(User user1 , User user2) {

     if (selectUser(user1 , user2)){
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO request_friend (requester_id, responder_id) VALUES (?, ?)");
            preparedStatement.setString(1, user1.getUserPhone());
            preparedStatement.setString(1, user2.getUserPhone());
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
     }
     else {
         return false;
     }
    }

    @Override
    public boolean selectUser(User user1 , User user2) {
        ResultSet resultSet;
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT requester_id , responder_id FROM request_friend where (requester_id = ? AND responder_id = ?) OR (requester_id = ? AND responder_id = ?)");
            preparedStatement.setString(1,user1.getUserPhone());
            preparedStatement.setString(2,user2.getUserPhone());
            preparedStatement.setString(3,user2.getUserPhone());
            preparedStatement.setString(4,user1.getUserPhone());
            resultSet=preparedStatement.executeQuery();
            if (resultSet==null)
            {
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
