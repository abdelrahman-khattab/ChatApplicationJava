package org.iti.project.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.iti.project.models.User;
import org.iti.project.presentation.util.StageCoordinator;

public class AddContactController {

    @FXML
    private Button addContactBtn;

    @FXML
    private Label errorsLbl;

    @FXML
    private TextField phoneNumberTf;

    @FXML
    void handleAddContactBtn(ActionEvent event) {
        String userPhoneCheck = phoneNumberTf.getText();
        System.out.println("outside");
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
