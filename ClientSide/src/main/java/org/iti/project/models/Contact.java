package org.iti.project.models;

import java.io.Serializable;

public class Contact implements Serializable {

    private  String user_Id ;
    private  String friend_Id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getFriend_Id() {
        return friend_Id;
    }

    public void setFriend_Id(String friend_Id) {
        this.friend_Id = friend_Id;
    }



}
