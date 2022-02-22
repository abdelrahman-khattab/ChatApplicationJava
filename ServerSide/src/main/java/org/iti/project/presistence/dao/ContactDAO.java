package org.iti.project.presistence.dao;

import org.iti.project.models.User;

import java.util.ArrayList;

public interface ContactDAO {
    ArrayList<User> selectUser(String userPhone);



}
