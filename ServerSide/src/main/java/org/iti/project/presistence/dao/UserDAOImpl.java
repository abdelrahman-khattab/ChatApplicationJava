package org.iti.project.presistence.dao;

import org.iti.project.models.User;
import org.iti.project.presistence.util.DBConnector;
import org.iti.project.util.ImageConverter;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    Connection con = DBConnector.getConnection().connect();

    @Override
    public boolean insertUser(User user) {
        Blob userImageAsBlop = ImageConverter.fromBytesToBlob(user.getImage());
        try {
            System.out.println("enter insert stm");
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
//        Connection conn = DBConnector.getConnection().connect();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = con.prepareStatement(
                    "SELECT * from user WHERE PHONE_NUMBER=? AND PASSWORD=?");
            pstmt.setString(1,user.getUserPhone());
            pstmt.setString(2,user.getUserPassword());
            System.out.println("enter select");
            rs = pstmt.executeQuery();

            if (rs.next())
            {            System.out.println("enter RS");

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

                System.out.println("Employee number = " + user.getUserName() +
                        "Phone number = " + user.getUserPhone());
                return user;
                // Print the column values
             }
        } catch (SQLException e) {
            System.out.println("enter problem");
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode() + e.getMessage());
                }
            }
        }
        // Create a PreparedStatement object    1
        System.out.println(user.toString());
        return null;
    }

    @Override
    public User selectRowUser(User user) {
//        Connection conn = DBConnector.getConnection().connect();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = con.prepareStatement(
                    "SELECT * from user WHERE PHONE_NUMBER=?");
            pstmt.setString(1,user.getUserPhone());

            System.out.println("enter select");
            rs = pstmt.executeQuery();

            if (rs.next())
            {            System.out.println("enter RS");

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

                System.out.println("Employee number = " + user.getUserName() +
                        "Phone number = " + user.getUserPhone());
                return user;
                // Print the column values
            }
        } catch (SQLException e) {
            System.out.println("enter problem");
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode() + e.getMessage());
                }
            }
        }
        // Create a PreparedStatement object    1
        System.out.println(user.toString());
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



    @Override
    public User checkUser(User user) {
//        Connection conn = DBConnector.getConnection().connect();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = con.prepareStatement(
                    "SELECT * from user WHERE PHONE_NUMBER=?");
            pstmt.setString(1,user.getUserPhone());
            System.out.println("enter select");
            rs = pstmt.executeQuery();

            if (rs.next())
            {            System.out.println("enter RS");

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

                System.out.println("Employee number = " + user.getUserName() +
                        "Phone number = " + user.getUserPhone());
                return user;
                // Print the column values
            }
        } catch (SQLException e) {
            System.out.println("enter problem");
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode() + e.getMessage());
                }
            }
        }
        // Create a PreparedStatement object    1
        System.out.println(user.toString());
        return null;
    }



}


