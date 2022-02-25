package org.iti.project.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import org.iti.project.util.ImageConverter;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ContactRequestListCell extends ListCell<User>  implements Initializable {

    @FXML
    private Button accept;

    @FXML
    private Circle contactImage;

    @FXML
    private HBox contactInfoHBox;

    @FXML
    private Label contactName;

    @FXML
    private Label contactNumber;

    @FXML
    private Button reject;

    public FXMLLoader fxmlLoader;
    Image userImage;
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    private final UserModel userModel = modelFactory.getUserModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User secUser = new User();
                User mainUser = new User();
                mainUser.setUserPhone(userModel.getPhoneNo());
                secUser.setUserPhone(contactNumber.getText());


                try {
                    RMIConnector.getRmiConnector().getContactService().acceptContact(mainUser,secUser);
                    System.out.println("accepted");

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        reject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("reject");
            }
        });

    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/contactRequestListCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userImage= ImageConverter.fromBytesToImage(user.getImage());
            contactName.setText(user.getUserName());
            contactImage.setFill(new ImagePattern(userImage));
            contactNumber.setText(String.valueOf(user.getUserPhone()));



            setGraphic(contactInfoHBox);
        }
    }


}

