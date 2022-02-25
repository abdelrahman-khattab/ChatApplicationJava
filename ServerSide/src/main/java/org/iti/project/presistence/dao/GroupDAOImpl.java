package org.iti.project.presistence.dao;
import org.iti.project.models.Group;
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

    @Override
    public void createNewGroup(Group group) {

        System.out.println("inside the if group insert");
        try(Connection con = DBConnector.getConnection().connect()) {
            System.out.println("inside the try group insert");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO groupchat (GROUP_NAME) VALUES (?)");
            preparedStatement.setString(1, group.getGroupName());
            preparedStatement.executeUpdate();
            System.out.println("after the update insert group execute");

        } catch (SQLException e) {
            System.out.println("inside group the catch");
            e.printStackTrace();
        }

    }

    @Override
    public void addNewUserToGroup(Group group, ArrayList<User> userList) {
      //INSERT INTO user_group (user_id, group_id) VALUES (?, ?)
        for (int i =0 ; i < userList.size() ; i++)
        {
            System.out.println("inside the user to group insert");
            try(Connection con = DBConnector.getConnection().connect()) {
                System.out.println("inside the try user to group insert");
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user_group (user_id, group_id) VALUES (?, ?)");
                preparedStatement.setString(1, userList.get(i).getUserPhone());
                preparedStatement.setInt(2, group.getGroupId());
                preparedStatement.executeUpdate();
                System.out.println("after the update insert group execute");

            } catch (SQLException e) {
                System.out.println("inside group the catch");
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Group> getListOfGroups(User user) {
      ResultSet resultSet;
        ArrayList<Group> groupList = new ArrayList<>();
        try(Connection con = DBConnector.getConnection().connect()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM groupchat where group_id IN (Select group_id from user_group where user_id = ? )");
            preparedStatement.setString(1,user.getUserPhone());
            resultSet=preparedStatement.executeQuery();
            System.out.println(resultSet == null);
            if (resultSet!=null)
            {
                while (resultSet.next())
                {
                    //Blob userBlopImage = resultSet.getBlob(3);
                    //adding directly in list by using new user with constructor of 3 parameteres
                    Group newGroup =new Group(resultSet.getInt(1),resultSet.getString(2) );
                    groupList.add(newGroup);

                }
                return  groupList;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

}
