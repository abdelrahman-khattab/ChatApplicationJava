package org.iti.project.models;

import java.io.Serializable;

public class User  implements Serializable {
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userBio;
    private String userCountry;
    private String userDOB;
    private String userDate;
    private String userPhone;
    private String gender;
    private byte[] image;
    private String status;
    private int unsavedMessageCount;
    //List<MessageModel> unseenmessgaeslist;



    public User(String userName, byte[] image, String status, int unsavedMessageCount) {
        this.userName = userName;
        this.image = image;
        this.status = status;
        this.unsavedMessageCount = unsavedMessageCount;
    }

    public User(String userName, String userPhone, byte[] image) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.image = image;
    }

    public User() {
    }




    public int getUnsavedMessageCount() {
        return unsavedMessageCount;
    }

    public void setUnsavedMessageCount(int unsavedMessageCount) {
        this.unsavedMessageCount = unsavedMessageCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// Adding array of bytes for image

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}
