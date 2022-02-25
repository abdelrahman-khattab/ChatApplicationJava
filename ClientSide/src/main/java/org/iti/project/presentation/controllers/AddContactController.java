package org.iti.project.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.presentation.util.StageCoordinator;

import java.rmi.RemoteException;

public class AddContactController {

    @FXML
    private Button addContactBtn;

    @FXML
    private Label errorsLbl;

    @FXML
    private TextField phoneNumberTf;

    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();

    @FXML
    void handleAddContactBtn(ActionEvent event) {
        String userPhoneCheck = phoneNumberTf.getText();
        System.out.println("outside");
        if (userModel.getPhoneNo().equals(phoneNumberTf.getText()))
        {
            errorsLbl.setText("You can't add yourself !!!!!");

        }
        else {
            for (User usr:SideContactListController.contactObservableList) {

                if(usr.getUserPhone().equals(userPhoneCheck))
                {
                    errorsLbl.setText("this is already friend !!");
                }
                else {
                    User secUser = new User();
                    User mainUser = new User();
                    mainUser.setUserPhone(userModel.getPhoneNo());
                    secUser.setUserPhone(phoneNumberTf.getText());
                    try {
                        System.out.println("ana gowa add");
                        RMIConnector.getRmiConnector().getContactService().addContact(mainUser, secUser);
                    } catch (RemoteException e) {
                        System.out.println("fy moshkela");
                        e.printStackTrace();
                    }
                    errorsLbl.setText("the request send");

                }
            }
        }
    }


}
