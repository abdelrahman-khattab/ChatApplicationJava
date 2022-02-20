package org.iti.project.presistence.dao;

import org.iti.project.models.User;

import java.util.List;

public interface GroupDAO {
    public List<String> findUsersPhoneByGroupId(int groupId);
    public List<User> getUsersByGroupId(int groupId);

}
