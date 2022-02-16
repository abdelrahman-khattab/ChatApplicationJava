package org.iti.project.presistence.dao;

import org.iti.project.models.User;

public interface UserDAO extends DAO
{
    void insertUser(User user);
    User selectUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
