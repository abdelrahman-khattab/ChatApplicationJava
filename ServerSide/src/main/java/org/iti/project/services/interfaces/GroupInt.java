package org.iti.project.services.interfaces;

import org.iti.project.models.Group;
import org.iti.project.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GroupInt extends Remote {
    public String lookupName = "GroupService";
    public void createNewGroup(Group group) throws RemoteException;
    public void addUsersToGroup(Group group, ArrayList<User> usersList) throws RemoteException;
    public ArrayList<Group> getListOfGroupsForCurrentUser(User user) throws RemoteException;
}
