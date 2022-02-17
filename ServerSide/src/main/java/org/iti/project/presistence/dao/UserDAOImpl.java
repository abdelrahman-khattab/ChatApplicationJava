package org.iti.project.presistence.dao;

import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    Connection con = DBConnector.getConnection().connect();

    @Override
    public boolean insertUser(User user) {
        Blob userImageAsBlop = ImageConverter.fromBytesToBlob(user.getImage());
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into user (PHONE_NUMBER ,USER_NAME ,EMAIL ,PASSWORD ,GENDER , COUNTRY , BIRTH_DATE , BIO ,IMAGE) \n" +
                    "values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getUserPhone());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getUserCountry());
            preparedStatement.setString(7, user.getUserDOB());
            preparedStatement.setString(8, user.getUserBio());
            preparedStatement.setBlob(9,userImageAsBlop);
            preparedStatement.execute();
            System.out.println(user.getUserName() + " with phone :" +user.getUserPhone());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            ///ChatClientCallback.loadError(e.getMessage());
            return false;
        }
    }

    @Override
    public User selectUser(User user) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        return true;
    }
}
