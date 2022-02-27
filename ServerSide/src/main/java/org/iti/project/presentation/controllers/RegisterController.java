package org.iti.project.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.iti.project.models.User;
import org.iti.project.presentation.util.Validator;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private boolean nameValidation = false;
    private boolean emailValidation = false;
    private boolean mobileValidation = false;
    private boolean birthDateValidation = false;
    private boolean imageValidation = false;

    @FXML
    private Button addMember;

    @FXML
    private ImageView addUser;

    @FXML
    private DatePicker birthDate;

    @FXML
    private TextArea clientBioTextArea;


    @FXML
    private Button clientRegistrationButton;

    @FXML
    private Circle memberImage;

    @FXML
    private TextField eMail;

    @FXML
    private ComboBox<String> countryComboBox;
    private String countries[] = {"Egypt", "Tunisia", "Morocco"};

    @FXML

    private ComboBox<String> genderComboBox;
    private String genders[] = {"Male", "Female"};

    @FXML
    private ListView<User> membersLV;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField searchBar1;

    @FXML
    private Button uploadImageBtn;

    @FXML
    private TextField userName;
    @FXML
    private VBox editVBox;

    private File file;
    public ObservableList<User> membersObservableList;
    byte[] userImg;
    @FXML
    void enterBirthDate(ActionEvent event) {
        birthDate.getEditor().setDisable(true);
        birthDate.getEditor().setOpacity(1);
        if (birthDate.getValue() == null) {
//            dateValidation.setVisible(true);

        } else {
//            dateValidation.setVisible(false);
            birthDateValidation = true;

        }

    }

    @FXML
    void enterEmail(KeyEvent event) {
        if (!Validator.emailValidation(eMail.getText())) {
            eMail.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");

        } else {
            eMail.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            emailValidation = true;
        }

    }

    @FXML
    void enterPhoneNumber(KeyEvent event) {
        if (phoneNo == null)
            phoneNo.setText("");
        if (!Validator.phoneValidation(phoneNo.getText())) {
            phoneNo.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
            mobileValidation = false;

        } else {
            phoneNo.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            mobileValidation = true;
        }

    }

    @FXML
    void enterUserName(KeyEvent event) {
        if (!Validator.nameValidation(userName.getText())) {
            userName.setStyle("-fx-border-color:RED;-fx-border-width:0 0 2px 0;");
            nameValidation = false;


        } else {
            userName.setStyle("-fx-border-color:#7269EF;-fx-border-width:0 0 2px 0;");
            nameValidation = true;

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        uploadImageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
                fc.getExtensionFilters().add(extFilter);
                file = fc.showOpenDialog((Stage) editVBox.getScene().getWindow());
                if (file != null) {
                    memberImage.setFill(new ImagePattern(new Image(file.getPath())));
                }else{
                    memberImage.setFill(new ImagePattern(new Image("/images/R.png")));
                }
            }
        });
        countryComboBox.getItems().addAll(countries);
        genderComboBox.getItems().addAll(genders);

        countryComboBox.getSelectionModel().select(0);
        genderComboBox.getSelectionModel().select(0);

    }

}
