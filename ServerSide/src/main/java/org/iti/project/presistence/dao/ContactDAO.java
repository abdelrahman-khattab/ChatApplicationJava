package org.iti.project.presistence.dao;

import org.iti.project.models.User;

import java.util.ArrayList;

public interface ContactDAO {
    ArrayList<User> selectContacts(String userPhone);
    ArrayList<User> insertContact(User mainUser , User secondaryUser);



}
