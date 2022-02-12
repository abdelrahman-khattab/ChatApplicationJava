package org.iti.project.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

//import gov.iti.jets.presentation.models.UserModel;
//import gov.iti.jets.presentation.util.ModelFactory;
import org.iti.project.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ProfileController implements Initializable {
    // private final StageCoordinator stageCoordinator =
    // StageCoordinator.getStageCoordinator();
    // private final ModelFactory modelFactory = ModelFactory.getInstance();

    @FXML
    private Text clientBioText;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label clientStatusLabel;

    @FXML
    private Button editInformationButton;

    @FXML
    private Label infoClientCountryLabel;

    @FXML
    private Label infoClientEmailLabel;

    @FXML
    private Label infoClientGenderLabel;

    @FXML
    private Label infoClientNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // UserModel userModel = modelFactory.getUserModel();
    }

    @FXML
    public void editClientInformation(ActionEvent event) {

        // Login of Edit button

    }

}
