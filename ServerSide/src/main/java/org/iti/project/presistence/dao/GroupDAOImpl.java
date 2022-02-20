package org.iti.project.presistence.dao;
import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements  GroupDAO{
    private List<String> clientsPhones = null;
    private List<User> users = null;
    private Connection con = null;

    // instantiate connection and lists
    public GroupDAOImpl(){

        clientsPhones = new ArrayList<>();
        users = new ArrayList<>();
        con =  DBConnector.getConnection().connect();

    }

    // get all phone numbers using group id
    @Override
    public List<String> findUsersPhoneByGroupId(int groupId) {
//
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
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

//
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
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
                user.setImage(rs.getBytes(9));

                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }
}
