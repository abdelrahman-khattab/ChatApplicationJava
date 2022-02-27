package org.iti.project.presistence.dao;

import org.iti.project.models.Group;
import org.iti.project.models.User;

import java.util.ArrayList;
import java.util.List;

public interface GroupDAO {
    public List<String> findUsersPhoneByGroupId(int groupId);
    public List<User> getUsersByGroupId(int groupId);
    public void createNewGroup(Group group , User user);
    public void addCurrentUserToGroup(User user, int groupId);
    public void addNewUserToGroup(Group group , ArrayList<User> userList);
    public ArrayList<Group> getListOfGroups(User user);
    int getSpecialGroup(Group group);
}
