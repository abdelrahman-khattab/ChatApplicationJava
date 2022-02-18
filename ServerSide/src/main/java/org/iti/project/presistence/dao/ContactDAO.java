package org.iti.project.presistence.dao;

import org.iti.project.models.Contact;

public interface ContactDAO {
    void insertUser(Contact contact);
    Contact selectUser(Contact contact);
    boolean updateUser(Contact contact);
    boolean deleteUser(Contact contact);

}
