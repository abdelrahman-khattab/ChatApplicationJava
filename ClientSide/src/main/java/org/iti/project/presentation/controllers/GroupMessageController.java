package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.presentation.models.MessageModel;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.util.ImageConverter;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GroupMessageController implements Initializable {

    private MessageModel messageModel = new MessageModel();


        @FXML
        private Text messageBodyText;

        @FXML
        private Label messageTimeLabel;

        @FXML
        private Text senderNameText;

        @FXML
        private TextFlow userMessageTextFlow;

        private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();





    public void setGroupMessage(GroupMessage groupMessage){
//        Image senderImage = ImageConverter.fromBytesToImage(groupMessage.getSender().getImage());
//        System.out.println(senderImage+ "image coming from server after converting");
//        messageModel.setImageObjectProperty(senderImage);
//        imageCircle.setFill(new ImagePattern(senderImage));
        if(!groupMessage.getSender().getUserPhone().equals(stageCoordinator.currentUser.getUserPhone()))
        {
            senderNameText.setText(groupMessage.getSender().getUserName()+" : \n");
        }
        messageBodyText.setText(groupMessage.getGroupMessageContent());
        int fontSize = groupMessage.getFontSize();
        String fontFamily = groupMessage.getFontFamily();
        String fontWeight = groupMessage.getFontWeight();
        String fontPosture = groupMessage.getFontPosture();
        Font msgFont = Font.font(fontFamily,
                fontWeight.equalsIgnoreCase("bold")? FontWeight.BOLD : FontWeight.NORMAL,
                fontPosture.equalsIgnoreCase("italic")? FontPosture.ITALIC:FontPosture.REGULAR,
                fontSize);
        messageBodyText.setFont(msgFont);
        messageBodyText.setFill(Color.valueOf(groupMessage.getGroupMessageColor()));
        messageBodyText.setUnderline(groupMessage.isFontUnderLine());
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        messageTimeLabel.setText(formattedDate);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if you want to bind change the circle to an imageview and use abdalla's way to shape the image view to circle-like shape
    }
}
