package org.iti.project.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.iti.project.models.User;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.presentation.util.StageCoordinator;

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
                    errorsLbl.setText("the request send");

                }
            }
        }
    }


}
