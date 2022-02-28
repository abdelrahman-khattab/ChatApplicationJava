package org.iti.project.presistence.dao;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements  GroupDAO{

    // instantiate connection and lists
    public GroupDAOImpl(){

    }

    // get all phone numbers using group id
    @Override
    public List<String> findUsersPhoneByGroupId(int groupId) {
//
        List<String> clientsPhones = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try (Connection con =  DBConnector.getConnection().connect()){
            pstmt = con.prepareStatement("SELECT user_id from user_group WHERE group_id = ?");
            pstmt.setInt(1,groupId);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                clientsPhones.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsPhones;
    }

    @Override
    public List<User> getUsersByGroupId(int groupId) {

        List<User> users = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try (Connection con =  DBConnector.getConnection().connect()){
            pstmt = con.prepareStatement(
                    "select PHONE_NUMBER, USER_NAME, EMAIL, PASSWORD, GENDER, COUNTRY, BIRTH_DATE, IMAGE, BIO from user,user_group \n" +
                            "where PHONE_NUMBER = user_id and group_id = ? ");
            pstmt.setInt(1,groupId);      // Assign value to input parameter      2

            rs = pstmt.executeQuery();        // Get the result table from the query  3
            while (rs.next())
            {
                User user = new User();
                user.setUserPhone(rs.getString(1));
                user.setUserName(rs.getString(2));
                user.setUserEmail(rs.getString(3));
                user.setUserPassword(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setUserCountry(rs.getString(6));
                user.setUserDOB(rs.getString(7));
                user.setUserBio(rs.getString(8));
                Blob userBlopImage = rs.getBlob(9);
                user.setImage(ImageConverter.fromBlobToBytes(userBlopImage));

                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
