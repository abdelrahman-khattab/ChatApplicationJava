package org.iti.project.presistence.dao;

import org.iti.project.models.Contact;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{



    @Override
    public ArrayList<User> selectUser(String userPhone) {
            ArrayList<User> contacts=new ArrayList<>();
            ResultSet resultSet;

        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("select distinct phone_number , USER_NAME , IMAGE from user , contacts where PHONE_NUMBER IN (select friend_id from contacts where user_id = ?) ");
            preparedStatement.setString(1,userPhone);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Blob userBlopImage = resultSet.getBlob(3);
                contacts.add(new User(resultSet.getString(2),resultSet.getString(1),ImageConverter.fromBlobToBytes(userBlopImage)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return    contacts;


    }
}
