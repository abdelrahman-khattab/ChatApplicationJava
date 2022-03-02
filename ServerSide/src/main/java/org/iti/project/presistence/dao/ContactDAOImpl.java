package org.iti.project.presistence.dao;

import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.*;
import java.util.ArrayList;

public class ContactDAOImpl implements ContactDAO{



    @Override
    public ArrayList<User> selectContacts(String userPhone) {
        ArrayList<User> contacts = new ArrayList<>();
        ResultSet resultSet;
        System.out.println("user phone number in server Contact Dao  : "+userPhone );
        PreparedStatement preparedStatement = null;
        try (Connection con = DBConnector.getConnection().connect()) {
            preparedStatement = con.prepareStatement("select distinct phone_number , USER_NAME , IMAGE from user , contacts where PHONE_NUMBER IN (select friend_id from contacts where user_id = ? ) ");
            preparedStatement.setString(1, userPhone);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blob userBlopImage = resultSet.getBlob(3);
                contacts.add(new User(resultSet.getString(2), resultSet.getString(1), ImageConverter.fromBlobToBytes(userBlopImage)));
            }
            System.out.println("ContactDAO : " + contacts);
            resultSet.close();
            return contacts;
        } catch (SQLException e) {
            System.out.println("Problem in selecting contact to user");
            e.getMessage();
            return null;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public boolean insertContact(User mainUser , User secondaryUser) {
        ResultSet resultSet;

        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO contacts (user_id, friend_id) VALUES (?, ?)");
            preparedStatement.setString(1,mainUser.getUserPhone());
            preparedStatement.setString(2, secondaryUser.getUserPhone());
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Problem in insert new contact to user");
            e.getMessage();
            return false;
        }

    }
}
