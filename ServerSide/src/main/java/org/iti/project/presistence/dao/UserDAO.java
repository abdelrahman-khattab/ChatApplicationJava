package org.iti.project.presistence.dao;

import org.iti.project.models.User;

public interface UserDAO extends DAO
{
    boolean insertUser(User user);
    User selectUser(User user);

    User selectRowUser(User user);

    boolean updateUser(User user);
    boolean deleteUser(User user);
    User checkUser(User user);
}
