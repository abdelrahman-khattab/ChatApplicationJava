package org.iti.project.presistence.dao;

import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.*;
import java.util.ArrayList;

public class RequestDAOImpl implements RequestDAO{
    @Override
    public boolean insertRequest(User user1 , User user2) {

     if (selectUser(user1 , user2)){
         System.out.println("inside the if insert");
        try(Connection con = DBConnector.getConnection().connect()) {
            System.out.println("inside the try insert");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO request_friend (requester_id, responder_id) VALUES (?, ?)");
            preparedStatement.setString(1, user1.getUserPhone());
            preparedStatement.setString(2, user2.getUserPhone());
            preparedStatement.executeUpdate();
            System.out.println("after the update insert execute");
            return true;
        } catch (SQLException e) {
            System.out.println("inside the catch");
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
        System.out.println("inside Select user in RequestDAO");
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT requester_id , responder_id FROM request_friend where (requester_id = ? AND responder_id = ?) OR (requester_id = ? AND responder_id = ?)");
            preparedStatement.setString(1,user1.getUserPhone());
            preparedStatement.setString(2,user2.getUserPhone());
            preparedStatement.setString(3,user2.getUserPhone());
            preparedStatement.setString(4,user1.getUserPhone());
            resultSet=preparedStatement.executeQuery();
            System.out.println(resultSet == null);
            if (resultSet!=null)
            {
                return true;
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
    public boolean deleteUser(User mainUser , User secondaryUser) {
        ResultSet resultSet;
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE from request_friend where requester_id = ? AND responder_id = ?");
            preparedStatement.setString(1,mainUser.getUserPhone());
            preparedStatement.setString(2,secondaryUser.getUserPhone());
            resultSet=preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<User> selectListRequestUser(User responderUser) {
        ResultSet resultSet;
        int iterator = 0;
        ArrayList<User> returnRequestList = new ArrayList<>();
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT PHONE_NUMBER , USER_NAME , IMAGE from user where PHONE_NUMBER IN (SELECT requester_id FROM chat_app_project.request_friend where responder_id = ?)");
            preparedStatement.setString(1,responderUser.getUserPhone());
            resultSet=preparedStatement.executeQuery();
            System.out.println(resultSet == null);
            if (resultSet!=null)
            {
                while (resultSet.next())
                {
                    Blob userBlopImage = resultSet.getBlob(3);
                    returnRequestList.get(iterator).setUserPhone(resultSet.getString(1));
                    returnRequestList.get(iterator).setUserName(resultSet.getString(2));
                    returnRequestList.get(iterator).setImage(ImageConverter.fromBlobToBytes(userBlopImage));
                }
                return  returnRequestList;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
