package gov.iti.jets.project.presistence.dao;

import gov.iti.jets.project.presistence.entities.User;

public interface UserDAO extends DAO
{
    void insertUser(User user);
    User selectUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
