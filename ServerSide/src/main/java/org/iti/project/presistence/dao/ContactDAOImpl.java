package org.iti.project.presistence.dao;

import org.iti.project.models.Contact;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{
    Connection con = DBConnector.getConnection().connect();
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
            List<String> contacts=new ArrayList<String>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("select (FRIEND_ID) from contacts where USER_ID=? ");
            preparedStatement.setString(1,contact.getUser_Id());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                contacts.add(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  contacts;


    }

    @Override
    public boolean updateUser(Contact contact) {
        return false;
    }

    @Override
    public boolean deleteUser(Contact contact) {
        return false;
    }
}
