package org.iti.project.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import org.iti.project.services.impls.SignInImpl;

import java.rmi.RemoteException;

public class AnnouncementController {

    @FXML
    private ImageView addUser;

    @FXML
    private TextArea annoumcementMessage;

    @FXML
    private Button sendToAll;

    @FXML
    void sendAnnoumcement(ActionEvent event) {
        SignInImpl.getOnlineClients().forEach((k,v)->{
            try {
                v.adminAnnouncement(annoumcementMessage.getText());
            } catch (RemoteException e) {
                System.out.println("Announcement Message doesn't work ");
            }
        });
    }
    
}
