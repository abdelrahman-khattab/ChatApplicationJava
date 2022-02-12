package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.iti.project.presentation.models.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ContactMessageController implements Initializable {

    private MessageModel messageModel = new MessageModel();

    @FXML
    private Circle imageCircle;

    @FXML
    private Text messageBodyText;

    @FXML
    private Label messageTimeLabel;

    @FXML
    private TextFlow userMessageTextFlow;



    public void setMessage(MessageModel messageModel){
        imageCircle.setFill(new ImagePattern(messageModel.getImageObjectProperty()));
        messageBodyText.setText(messageModel.getMessageBody());
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        messageTimeLabel.setText(formattedDate);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
