package org.iti.project.presistence.dao;

import org.iti.project.models.User;

public interface UserDAO extends DAO
{
    boolean insertUser(User user);
    User selectUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    public User selectRowUser(User user);
}
