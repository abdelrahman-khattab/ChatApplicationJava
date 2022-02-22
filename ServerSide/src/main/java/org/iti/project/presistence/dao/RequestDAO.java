package org.iti.project.presistence.dao;

import org.iti.project.models.User;

public interface RequestDAO {
    boolean insertRequest(User user1 , User user2);
    boolean selectUser(User user1 , User user2);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
