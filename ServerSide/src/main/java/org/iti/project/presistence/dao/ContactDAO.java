package org.iti.project.presistence.dao;

import org.iti.project.models.Contact;

import java.util.List;

public interface ContactDAO {

    void insertUser(Contact contact);
    List<String> selectUser(Contact contact);
    boolean updateUser(Contact contact);
    boolean deleteUser(Contact contact);

}
