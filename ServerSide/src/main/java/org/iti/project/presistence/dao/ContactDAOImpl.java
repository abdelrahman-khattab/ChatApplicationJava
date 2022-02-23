package org.iti.project.presistence.dao;

import org.iti.project.models.Contact;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{

    Connection con = DBConnector.getConnection().connect();
    private PreparedStatement preparedStatement;

    @Override
    public void insertUser(Contact contact) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into contacts (USER_ID ,FRIEND_ID,STATUS ) \n" + "values (?,?,?)");
            preparedStatement.setString(1, contact.getUser_Id());
            preparedStatement.setString(2, contact.getFriend_Id());
            preparedStatement.setString(3, contact.getStatus());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> selectUser(Contact contact) {
        return null;
    }

    @Override
    public boolean updateUser(Contact contact) {
        return false;
    }

    @Override
    public boolean deleteUser(Contact contact) {
        return false;
    }

    @Override
    public ArrayList<User> selectUser(String userPhone) {
            ArrayList<User> contacts=new ArrayList<>();
            ResultSet resultSet;

        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("select distinct phone_number , USER_NAME , IMAGE from user , contacts where PHONE_NUMBER IN (select friend_id from contacts where user_id = ?) ");
            preparedStatement.setString(1,userPhone);
            resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                Blob userBlopImage = resultSet.getBlob(3);
                contacts.add(new User(resultSet.getString(2), resultSet.getString(1),ImageConverter.fromBlobToBytes(userBlopImage)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return    contacts;


    }
}
