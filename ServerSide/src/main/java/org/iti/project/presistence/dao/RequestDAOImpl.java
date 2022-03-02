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
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO request_friend (requester_id, responder_id) VALUES (?, ?)");
            preparedStatement.setString(1, user1.getUserPhone());
            preparedStatement.setString(2, user2.getUserPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Problem inside insert the friend  request ");
            e.getMessage();
        }
        return false;
     }
     else {
         deleteUser(user1,user2);
         deleteUser(user2,user1);
         ContactDAO contactDAO = new ContactDAOImpl();
         contactDAO.insertContact(user1,user2);
         contactDAO.insertContact(user2,user1);
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
            preparedStatement.close();
            System.out.println(resultSet == null);
            if (resultSet!=null)
            {
                resultSet.close();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Problem inside select user request for checking");
            e.getMessage();
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User mainUser , User secondaryUser) {
        System.out.println("enter Delete stm" + ""+ mainUser.getUserPhone() +"" +secondaryUser.getUserPhone());
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE from request_friend where requester_id = ? AND responder_id = ?");
            preparedStatement.setString(1,mainUser.getUserPhone());
            preparedStatement.setString(2,secondaryUser.getUserPhone());
            int numberOfRow = preparedStatement.executeUpdate();
            System.out.println("finish Delete stm and num of row is : "+ numberOfRow);
            preparedStatement.close();
            return  true;
        } catch (SQLException e) {
            System.out.println("Problem inside delete request");
            e.getMessage();     }

        return false;
    }

    @Override
    public ArrayList<User> selectListRequestUser(User responderUser) {
        ResultSet resultSet;
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
                    returnRequestList.add(new User(resultSet.getString(2),resultSet.getString(1),ImageConverter.fromBlobToBytes(userBlopImage)));
               }
                resultSet.close();
                preparedStatement.close();
                System.out.println("request list in dao"+returnRequestList);
                return  returnRequestList;
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Problem inside select all request for the user ");
            e.getMessage();        }


        return null;
    }
}
