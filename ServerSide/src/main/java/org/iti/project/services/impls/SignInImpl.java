package org.iti.project.services.impls;

import javafx.application.Platform;
import org.iti.project.models.User;
import org.iti.project.presentation.controllers.DashBoardController;
import org.iti.project.presistence.dao.ContactDAO;
import org.iti.project.presistence.dao.ContactDAOImpl;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.services.interfaces.ClientCallBackInt;
import org.iti.project.services.interfaces.SignInInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SignInImpl extends UnicastRemoteObject implements SignInInt {

    static HashMap<String, ClientCallBackInt> onlineClients = new HashMap<>();
    private ContactDAO contactDAO = new ContactDAOImpl();
    public SignInImpl() throws RemoteException {

    }


    @Override
    public User loginMe(User user , ClientCallBackInt clientCallBack) throws RemoteException {
        System.out.println("user number is connected : "+user.getUserPhone());
        UserDAOImpl userDAO = new UserDAOImpl();

         user = userDAO.selectUser(user);
        if(user==null)
        {
            System.out.println("don't Find User");
            return null;
        }
        else{
            onlineClients.put(user.getUserPhone(), clientCallBack);
            //System.out.println("counter in map : "+ onlineClients.size());
            //DashboardController.updateStatus(onlineClients.size());
            System.out.println("user is add"+onlineClients.toString());
            updateAvailability(user);
            Platform.runLater(()->{
                DashBoardController.getInstance().data(onlineClients.size(),10);
            });

            return user;
        }

    }
    private void updateAvailability(User user) throws RemoteException {
        List<String> friendsPhones = contactDAO.selectContacts(user.getUserPhone())
                                        .stream().map(User::getUserPhone)
                                        .collect(Collectors.toList());
        if(friendsPhones.size()>0){
            for(String friendPhone : friendsPhones){
                ClientCallBackInt clientCallBack =  onlineClients.get(friendPhone);
                if (clientCallBack != null){
                    clientCallBack.updateFriendAvailability(user.getUserName(),  user.getUserPhone(), true);
                }
            }
        }

    }

    public static HashMap<String, ClientCallBackInt> getOnlineClients() {
        return onlineClients;
    }
}
