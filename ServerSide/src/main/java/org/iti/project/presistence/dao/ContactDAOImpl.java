package org.iti.project.presistence.dao;

import org.iti.project.models.Contact;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDAOImpl implements ContactDAO{
    Connection con = DBConnector.getConnection().connect();
    @Override
    public void insertUser(Contact contact) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into user (USER_ID ,FRIEND_ID ) \n" +
                    "values (?,?)");
            preparedStatement.setString(1, contact.getUser_Id());
            preparedStatement.setString(2, contact.getFriend_Id());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact selectUser(Contact contact) {
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
}
