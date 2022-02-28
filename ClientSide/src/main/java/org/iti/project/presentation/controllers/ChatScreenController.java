package org.iti.project.presentation.controllers;

import com.google.code.chatterbotapi.ChatterBotSession;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.models.Group;
import org.iti.project.presentation.models.MessageModel;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ChatBot;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.util.ImageConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.*;

public class ChatScreenController implements Initializable {

    //private final Map<String, Scene> sceneMap = stageCoordinator.getSceneMap();

    private static ChatScreenController chatScreenController;
    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
    private final Map<String, ScrollPane> paneMap = new HashMap<>();
    private FileChooser fileChooser;

    @FXML
    private Button callButton;

    @FXML
    private Button chatListButton;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private VBox chatVBox;

    @FXML
    private Circle contactImageCircle;

    @FXML
    private Text contactImageLabel;

    @FXML
    private Button contactListButton;

    @FXML
    private Button chatBotButton;

    @FXML
    private Button fileAttachementButton;

    @FXML
    private ChoiceBox<String> fontFamilyButton;

    @FXML
    private ChoiceBox<Integer> fontSizeButton;

    @FXML
    private ToggleButton italicButton;

    @FXML
    private Button groupChatButton;

    @FXML
    private ToggleButton boldButton;

    @FXML
    private ToggleButton underlineButton;

    @FXML
    private Button logOutButton;

    @FXML
    private ColorPicker messageColorPickerButton;

    @FXML
    private Label messageTimeLabel;

    @FXML
    private Button profileButton;

    @FXML
    private Button sendMessageButton;

    @FXML
    private StackPane sideNavigationStackPane;

    @FXML
    private Button unknownFunctionaityButton;

    @FXML
    private TextFlow userMessageLabel2;

    @FXML
    private TextFlow userMessageTextFlow;

    @FXML
    private ScrollPane firstPane;

    @FXML
    private ScrollPane secondPane;

    @FXML
    private TextField messageTextField;

    private ScrollPane sideContactListPane;
    private ScrollPane sideProfilePane;
    private ScrollPane sideGroupPane;
    private ScrollPane chatListPane;

    private SideGroupListController sideGroupListController;
    private SideChatListController sideChatListController;
    private ProfileController profileController;
    private SideContactListController sideContactListController;

    private static volatile boolean isGroup = false;
    private boolean chatBotFlag = false;
    private Group currentContactedGroup;
    private User currentContactedUser;

    private final UserModel userModel = new UserModel();
    MessageModel messageModel = new MessageModel();
    ChatBot chatBot = new ChatBot();
    ChatterBotSession botSession = chatBot.createBotSession();




    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setController(ChatScreenController chatScreenController) {
        ChatScreenController.chatScreenController = chatScreenController;
    }

    public static ChatScreenController getInstance(){
        return chatScreenController;
    }

    public Group getCurrentContactedGroup() {
        return currentContactedGroup;
    }

    public void setCurrentContactedGroup(Group currentContactedGroup) {
        this.currentContactedGroup = currentContactedGroup;
    }

    public User getCurrentContactedUser() {
        return currentContactedUser;
    }

    public void setCurrentContactedUser(User currentContactedUser) {
        this.currentContactedUser = currentContactedUser;
    }

    public void setIsGroup(boolean boolean_value) {
        ChatScreenController.isGroup = boolean_value;
    }

    public boolean isIsGroup() {
        return isGroup;
    }

    @FXML
    void onChatListButtonClicked(ActionEvent event) {
        //chatScreenCoordinator.switchToChatList();
        switchToChatListPane();
    }

    private void switchToChatListPane() {
        chatListPane = paneMap.get("chatListPane");
//        if(chatListPane == null) {
//            try {
//                FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/view/sideChatList.fxml"));
//                chatListPane = chatLoader.load();
//                System.out.println("chatPane created");
//                paneMap.put("chatListPane",chatListPane);
////                here you can load the controller
////                Pane chatListScrollPane = FXMLLoader.load(getClass().getResource("/view/sideChatList.fxml"));
//                sideNavigationStackPane.getChildren().removeAll();
//                sideNavigationStackPane.getChildren().add(chatListPane);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////        sideNavigationStackPane.getChildren().removeAll();
////        sideNavigationStackPane.getChildren().add(chatListPane);
        chatListPane.toFront();

    }

    @FXML
    void onContactListButtonClicked(ActionEvent event) {
        switchToContactListPane();
    }

    private void switchToContactListPane() {
        sideContactListPane = paneMap.get("sideContactListPane");
//        if(sideContactListPane == null) {
//            try {
//                FXMLLoader sideContactListLoader = new FXMLLoader(getClass().getResource("/view/sideContactList.fxml"));
//                sideContactListPane = sideContactListLoader.load();
//                System.out.println("sideContactListPane created");
//                paneMap.put("sideContactListPane",sideContactListPane);
//                sideNavigationStackPane.getChildren().add(sideContactListPane);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        sideContactListPane.toFront();
    }



    @FXML
    void onProfileButtonClicked(ActionEvent event) {
        switchToProfilePane();
    }
    private void switchToProfilePane() {
        sideProfilePane = paneMap.get("sideProfilePane");
//        if(sideProfilePane == null) {
//            try {
//                FXMLLoader profilePaneLoader = new FXMLLoader(getClass().getResource("/view/sideProfilePane.fxml"));
//                sideProfilePane = profilePaneLoader.load();
//                System.out.println("sideProfilePane created");
//                paneMap.put("sideProfilePane",sideProfilePane);
//                sideNavigationStackPane.getChildren().add(sideProfilePane);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        sideProfilePane.toFront();
        System.out.println(sideNavigationStackPane.getChildren().get(sideNavigationStackPane.getChildren().size()-1)==sideProfilePane);


    }

    @FXML
    void onUnownButtonClicked(ActionEvent event) {
    //Tooltip.install(unknownFunctionaityButton,exitTip);
    //exitTip.show(null);
        Platform.exit();
        System.exit(0);

    }

    @FXML
    void onLogOutButtonClicked(ActionEvent event) {
        try {
            RMIConnector.getRmiConnector().getSignOutService().logoutMe(stageCoordinator.currentUser.getUserPhone());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        stageCoordinator.switchToLoginFormScene();
    }

    @FXML
    void onChatBotButtonClicked(ActionEvent event){
        chatBotFlag = !chatBotFlag;
        Notifications.create().title("Chat Bot").text("Chat Bot Is "+(chatBotFlag ? "ON":"OFF"))
                .position(Pos.TOP_CENTER).showInformation();
    }

    @FXML
    public void onFileAttachingButtonClicked(ActionEvent actionEvent) {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File to Send");
        fileChooser.getExtensionFilters().addAll( new ExtensionFilter("All Files",
                        "*.txt","*.png","*.jpeg","*.jpg","*.doc","*.docx","*.pdf","*.java","*.css","*.html"),
                     new ExtensionFilter("Videos","*.mp4"),
                     new ExtensionFilter("Audios","*.mp3","*.wav")
                     );
        File chosenFileToSend = fileChooser.showOpenDialog(stageCoordinator.getPrimaryStage());
        if (chosenFileToSend != null){
            try {
                String senderName = StageCoordinator.getStageCoordinator().currentUser.getUserName();
                String receiverPhone = currentContactedUser.getUserPhone();
                byte[] sentFileAsBytes = Files.readAllBytes(chosenFileToSend.toPath());
                String fileName = chosenFileToSend.getName();
                RMIConnector.getRmiConnector().getChattingService().sendFile( senderName , receiverPhone, sentFileAsBytes , fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void handleReceivedFile(String senderName, byte[] sentFile , String fileName){
        Notifications.create().title("Receive a File")
                .text(senderName+" wants to send you a file")
                .position(Pos.TOP_CENTER)
                .showInformation();
        Alert alert = creatFileAlert(senderName);
        Optional<ButtonType> alertActionResult = alert.showAndWait();
        if (alertActionResult.isPresent()){

            if (alertActionResult.get() == ButtonType.YES){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName(fileName);

                fileChooser.setTitle("Save file");
                File chosenFile =  fileChooser.showSaveDialog(StageCoordinator.getStageCoordinator().getPrimaryStage());
                FXMLLoader progressDialogLoader = new FXMLLoader(getClass().getResource("/view/progressDialog.fxml"));

//                try {
//                    Files.write(chosenFile.toPath(), sentFile );
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (chosenFile != null){
//                    Platform.runLater(() -> {
                        try {
                            DialogPane progressDialogPane = progressDialogLoader.load();
                            ProgressBarController progressBarController = progressDialogLoader.getController();
                            ProgressBarController.setController(progressBarController);
                            progressBarController = ProgressBarController.getInstance();
                            Dialog<DialogPane> dialogPaneDialog = new Dialog<>();
                            dialogPaneDialog.setDialogPane(progressDialogPane);
                            DownloadTask downloadTask = new DownloadTask(sentFile , chosenFile);
                            progressBarController.getDownloadProgressBar().progressProperty().bind(downloadTask.progressProperty());

                            dialogPaneDialog.show();
                            Thread thread = new Thread(downloadTask);
                            thread.setDaemon(true);
                            thread.start();
//                            dialogPaneDialog.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                    });
                }
            }
        }
    }

    private Alert creatFileAlert(String senderName){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to receive file from "+ senderName + " ?");
        alert.setTitle("File Download");
        alert.getButtonTypes().remove(0,2);
        alert.getButtonTypes().add(0,ButtonType.YES);
        alert.getButtonTypes().add( 1,ButtonType.NO);
        return alert;
    }

    private class DownloadTask extends Task<Void>{
        private byte[] sentFile;
        private File chosenFile;


        public DownloadTask(byte[] sentFile, File chosenFile){
            this.sentFile = sentFile;
            this.chosenFile = chosenFile;
        }

        @Override
        protected Void call() throws Exception {
            int mainArrayLength = sentFile.length;
            int remainder = mainArrayLength % 100;
            int numberOfArrays = (mainArrayLength / 100);
            if (remainder != 0) {
                numberOfArrays += 1;
            }
            long progress;
            for (int i = 0; i < numberOfArrays; i++) {
                int x = 100;
                if (i == (numberOfArrays - 1)) {
                    x = mainArrayLength % 100;
                }
                updateProgress(i ,numberOfArrays);
                FileUtils.writeByteArrayToFile(chosenFile , sentFile , i*100 ,x , true);
            }
            return null;
        }
    }

    public void onSendButtonClicked(ActionEvent actionEvent) {
        //making sure that chatBot is disabled
        chatBotFlag = false;
        //sending messages manually
        if (!messageTextField.getText().isEmpty()){
            String messageBody = messageTextField.getText().trim();
            try {
                if(isGroup) {
                    GroupMessage sentMessage = createGroupMessage();
                    displayMySentGroupMessage(sentMessage);
                    RMIConnector.getRmiConnector().getChattingService().sendGroupMessage(sentMessage);
                }
                else {
                    SingleMessage sentMessage = createSingleMessage(messageTextField.getText().trim());
                    displayMySentSingleMessage(sentMessage);
                    RMIConnector.getRmiConnector().getChattingService().sendSingleMessage(sentMessage);
                }
                messageTextField.clear();
            } catch (RemoteException e) {
                messageTextField.clear();
                e.printStackTrace();
                System.out.println(e.getMessage()+" your message not sent");
            }
        }
    }

    public void handleSingleMessage(SingleMessage singleMessage){
        if (!isGroup && singleMessage.getSender().getUserPhone().equals(currentContactedUser.getUserPhone())){
            renderSingleMessage(singleMessage);
            if(chatBotFlag){
                try {
                    System.out.println("should respond");
                    String response = botSession.think(singleMessage.getSingleMessageContent());
                    SingleMessage sentMessage = createSingleMessage(response);
                    displayMySentSingleMessage(sentMessage);
                    RMIConnector.getRmiConnector().getChattingService().sendSingleMessage(sentMessage);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        else if(!singleMessage.getSender().getUserPhone().equals(stageCoordinator.currentUser.getUserPhone())){
        else{
            System.out.println(singleMessage.getSingleMessageContent() + "ana geet bs msh httb3 3ashan ana gaya mn " +
                    singleMessage.getSender().getUserName() +
                    "wnta btklm " + currentContactedUser.getUserName());
            Notifications.create().position(Pos.TOP_CENTER)
                    .text(singleMessage.getSingleMessageContent() + " from "+
                    singleMessage.getSender().getUserName())
                    .title("Single Message Notification")
                    .darkStyle()
                    .showInformation();
        }

    }

    public void handleGroupMessage(GroupMessage groupMessage){
        if (isGroup && groupMessage.getGroupId()== currentContactedGroup.getGroupId()){
            renderGroupMessage(groupMessage);
        }
        else if(!groupMessage.getSender().getUserPhone().equals(stageCoordinator.currentUser.getUserPhone())){
            System.out.println(groupMessage.getGroupMessageContent() +
                    " ana geet bs msh httb3 3ashan ana gaya mn " + groupMessage.getSender().getUserName() +
                    "wnta btklm" + currentContactedUser.getUserName());
            Notifications.create()
                    .position(Pos.TOP_CENTER)
                    .text(groupMessage.getGroupMessageContent() + " from "+
                            groupMessage.getSender().getUserName())
                    .title("Group Message Notification")
                    .darkStyle()
                    .showInformation();
        }

    }

    private void displayMySentSingleMessage(SingleMessage singleMessage){
//        MessageModel messageModel = new MessageModel();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/userMessage.fxml"));
        try {
            HBox messageHBox = fxmlLoader.load();
            ContactMessageController messageController = fxmlLoader.getController();
            messageController.setSingleMessage(singleMessage);
            chatVBox.getChildren().add(messageHBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMySentGroupMessage(GroupMessage groupMessage){
//        MessageModel messageModel = new MessageModel();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/groupUserMessage.fxml"));
        try {
            HBox messageHBox = fxmlLoader.load();
            GroupMessageController groupMessageController = fxmlLoader.getController();
            groupMessageController.setGroupMessage(groupMessage);
            chatVBox.getChildren().add(messageHBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderGroupMessage(GroupMessage groupMessage) {
//        MessageModel messageModel = new MessageModel();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/groupContactMessage.fxml"));
        try {
            HBox messageHBox = fxmlLoader.load();
            GroupMessageController groupMessageController = fxmlLoader.getController();
//            System.out.println(groupMessage.getSender().getImage()+" image coming from server as bytes");
            groupMessageController.setGroupMessage(groupMessage);
            chatVBox.getChildren().add(messageHBox);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void renderSingleMessage(SingleMessage singleMessage) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/contactMessage.fxml"));
        try {
            HBox messageHBox = fxmlLoader.load();
            ContactMessageController messageController = fxmlLoader.getController();
            messageController.setSingleMessage(singleMessage);
            chatVBox.getChildren().add(messageHBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onGroupChatButtonClicked(ActionEvent actionEvent) {
        sideGroupPane = paneMap.get("sideGroupPane");
//        if(sideGroupPane == null) {
//            try {
//                FXMLLoader sideGroupListLoader = new FXMLLoader(getClass().getResource("/view/SideGroupList.fxml"));
//                sideGroupPane = sideGroupListLoader.load();
//                System.out.println("sideGroupPane created");
//                paneMap.put("sideGroupPane",sideGroupPane);
////                here you can load the controller
////                Pane chatListScrollPane = FXMLLoader.load(getClass().getResource("/view/sideChatList.fxml"));
////                sideNavigationStackPane.getChildren().removeAll();
//                sideNavigationStackPane.getChildren().add(sideGroupPane);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////        sideNavigationStackPane.getChildren().removeAll();
////        sideNavigationStackPane.getChildren().add(chatListPane);
        sideGroupPane.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Tooltip.install(unknownFunctionaityButton, new Tooltip("Leave"));
        chatScrollPane.vvalueProperty().bind(chatVBox.heightProperty());
        contactImageCircle.setFill(new ImagePattern(userModel.getUserImage())); // bind this property with current contacted entity

        fontSizeButton.getItems().addAll(14,16,18,20);
        fontSizeButton.setValue(14);

        fontFamilyButton.getItems().addAll(Font.getFamilies());
        fontFamilyButton.setValue("Berlin Sans FB");

        currentContactedUser = new User("#","#",null);
        currentContactedGroup= new Group("#","#",null,-1);


        try {
            FXMLLoader sideContactListLoader = new FXMLLoader(getClass().getResource("/view/sideContactList.fxml"));
            sideContactListPane = sideContactListLoader.load();
            SideContactListController.setController(sideContactListLoader.getController());
            sideContactListController = SideContactListController.getInstance();
//            System.out.println("sideContactListPane created");
            paneMap.put("sideContactListPane",sideContactListPane);
            sideNavigationStackPane.getChildren().add(sideContactListPane);

            FXMLLoader profilePaneLoader = new FXMLLoader(getClass().getResource("/view/sideProfilePane.fxml"));
            sideProfilePane = profilePaneLoader.load();
            ProfileController.setController(profilePaneLoader.getController());
            profileController = ProfileController.getInstance();
//            System.out.println("sideProfilePane created");
            paneMap.put("sideProfilePane",sideProfilePane);
            sideNavigationStackPane.getChildren().add(sideProfilePane);

            FXMLLoader sideGroupListLoader = new FXMLLoader(getClass().getResource("/view/SideGroupList.fxml"));
            sideGroupPane = sideGroupListLoader.load();
            SideGroupListController.setController(sideGroupListLoader.getController());
            sideGroupListController = SideGroupListController.getInstance();
//            System.out.println("sideGroupPane created");
            paneMap.put("sideGroupPane",sideGroupPane);
            sideNavigationStackPane.getChildren().add(sideGroupPane);

            FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/view/sideChatList.fxml"));
            chatListPane = chatLoader.load();
            SideChatListController.setController(chatLoader.getController());
            sideChatListController = SideChatListController.getInstance();
//            System.out.println("chatPane created");
            paneMap.put("chatListPane",chatListPane);
            sideNavigationStackPane.getChildren().add(chatListPane);

            System.out.println(sideNavigationStackPane.getChildren().get(sideNavigationStackPane.getChildren().size()-1)==chatListPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private GroupMessage createGroupMessage(){
        GroupMessage groupMessage = new GroupMessage(messageTextField.getText().trim(),
                stageCoordinator.currentUser, currentContactedGroup.getGroupId());
//        System.out.println(stageCoordinator.currentUser.getImage()+" image should be sent to server as bytes");
        String msgColor = toRGBCode(messageColorPickerButton.getValue());
        groupMessage.setFontFamily(fontFamilyButton.getValue());
        groupMessage.setFontSize(fontSizeButton.getValue());
        groupMessage.setFontPosture(italicButton.isSelected()? "ITALIC" : "REGULAR");
        groupMessage.setFontWeight(boldButton.isSelected()? "BOLD":"NORMAL");
        groupMessage.setFontUnderLine(underlineButton.isSelected());
        LocalDateTime msgCreationTime = LocalDateTime.now();
        groupMessage.setGroupMessageColor(msgColor);
        groupMessage.setMessageCreationTime(msgCreationTime);
//        groupMessage.setMessageFont(msgFont);

        return groupMessage;
    }

    private SingleMessage createSingleMessage(String messageText){
        SingleMessage singleMessage = new SingleMessage(messageText,
                stageCoordinator.currentUser, currentContactedUser.getUserPhone());
        System.out.println(currentContactedUser.getUserPhone()+" from createSingleMessage I am the phone number");
        String msgColor = toRGBCode(messageColorPickerButton.getValue());
        singleMessage.setFontFamily(fontFamilyButton.getValue());
        singleMessage.setFontSize(fontSizeButton.getValue());
        singleMessage.setFontPosture(italicButton.isSelected()? "ITALIC" : "REGULAR");
        singleMessage.setFontWeight(boldButton.isSelected()? "BOLD":"NORMAL");
        singleMessage.setFontUnderLine(underlineButton.isSelected());
        LocalDateTime msgCreationTime = LocalDateTime.now();
        singleMessage.setSingleMessageColor(msgColor);
        singleMessage.setMessageCreationTime(msgCreationTime);

        return singleMessage;
    }
    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    public void updateGroupChatScene(Group currentContactedGroup, String name){
        Image newGroupImage = ImageConverter.fromBytesToImage(currentContactedGroup.getGroupImageBytes());
        contactImageCircle.setFill(new ImagePattern(newGroupImage));
        contactImageLabel.setText(name);
        chatVBox.getChildren().clear();
        currentContactedUser = new User("#","#",null);
        previewGroupChatHistory();
//        sideChatListController.getContactinfoLV().getSelectionModel().clearSelection();

    }
    public void updateSingleChatScene(User currentContactedUser){
        Image selectedUserImage = ImageConverter.fromBytesToImage(currentContactedUser.getImage());
        messageModel.setImageObjectProperty(selectedUserImage);
        contactImageCircle.setFill(new ImagePattern(selectedUserImage));
        contactImageLabel.setText(currentContactedUser.getUserName());
        chatVBox.getChildren().clear();
        currentContactedGroup= new Group("#","#",null,-1);
        previewSingleChatHistory();
//        sideGroupListController.getGroupListView().getSelectionModel().clearSelection();

    }
    private void previewSingleChatHistory(){
        List<SingleMessage> singleMessageHistory = new ArrayList<>();
        try {

            singleMessageHistory = RMIConnector.getRmiConnector().getChattingService().fetchSingleMessageHistory(stageCoordinator.currentUser.getUserPhone(), currentContactedUser.getUserPhone());
//            System.out.println(" All Old messages  : "+ singleMessageHistory);
        } catch (RemoteException e) {

            e.printStackTrace();
        }
        for (SingleMessage singleMessage : singleMessageHistory){
            if (singleMessage.getSender().getUserPhone().equals(stageCoordinator.currentUser.getUserPhone())
            && currentContactedUser.getUserPhone().equals(singleMessage.getReceiverPhoneNumber())){
                singleMessage.getSender().setImage(stageCoordinator.currentUser.getImage());
                displayMySentSingleMessage(singleMessage);
            }
            else if (singleMessage.getSender().getUserPhone().equals(currentContactedUser.getUserPhone())){
//                byte[] friend_image = ImageConverter.fromImageToBytes(messageModel.getImageObjectProperty().getUrl());
//                singleMessage.getSender().setImage(friend_image);
                singleMessage.getSender().setImage(currentContactedUser.getImage());
                renderSingleMessage(singleMessage);

            }

        }
    }
    private void previewGroupChatHistory(){
        List<GroupMessage> groupMessageHistory = new ArrayList<>();
        try {

            groupMessageHistory = RMIConnector.getRmiConnector().getChattingService().fetchGroupMessageHistory(currentContactedGroup.getGroupId());

//                    System.out.println(" All Old messages  : "+ groupMessageHistory);
        } catch (RemoteException e) {

            e.printStackTrace();
        }
        for (GroupMessage groupMessage : groupMessageHistory){
            if (groupMessage.getSender().getUserPhone().equals(stageCoordinator.currentUser.getUserPhone())
                    && groupMessage.getGroupId() == currentContactedGroup.getGroupId()){
                displayMySentGroupMessage(groupMessage);
            }
            else if (groupMessage.getGroupId()==currentContactedGroup.getGroupId()){
                renderGroupMessage(groupMessage);

            }

        }
    }
}
