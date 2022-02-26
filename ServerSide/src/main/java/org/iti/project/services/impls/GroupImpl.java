package org.iti.project.services.impls;

import org.iti.project.models.Group;
import org.iti.project.models.User;
import org.iti.project.presistence.dao.GroupDAO;
import org.iti.project.presistence.dao.GroupDAOImpl;
import org.iti.project.services.interfaces.GroupInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GroupImpl extends UnicastRemoteObject implements GroupInt {
    public GroupImpl() throws RemoteException {
    }

    @Override
    public void createNewGroup(Group group) throws RemoteException {
        GroupDAO groupDAO = new GroupDAOImpl();
        groupDAO.createNewGroup(group);
    }

    @Override
    public void addUsersToGroup(Group group, ArrayList<User> usersList) throws RemoteException {
        GroupDAO groupDAO = new GroupDAOImpl();
        groupDAO.addNewUserToGroup(group,usersList);

    }

    @Override
    public ArrayList<Group> getListOfGroupsForCurrentUser(User user) throws RemoteException {
        GroupDAO groupDAO = new GroupDAOImpl();
        ArrayList<Group> returnGroupList = new ArrayList<>();
        returnGroupList = groupDAO.getListOfGroups(user);
        if (returnGroupList !=null)
        {
            return returnGroupList;
        }
        else {
            return null;
        }
    }
}
