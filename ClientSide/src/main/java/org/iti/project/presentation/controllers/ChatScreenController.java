package org.iti.project.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.models.Group;
import org.iti.project.presentation.models.MessageModel;
import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.StageCoordinator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
    private Button emojiButton;

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
    private static Group currentContactedGroup;
    private static User currentContactedUser;

    private final UserModel userModel = new UserModel();

    public static void setController(ChatScreenController chatScreenController) {
        ChatScreenController.chatScreenController = chatScreenController;
    }
    public static ChatScreenController getInstance(){
        return chatScreenController;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////


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



    public void onFileAttachingButtonClicked(ActionEvent actionEvent) {
        fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(stageCoordinator.getPrimaryStage());


    }

    public void onSendButtonClicked(ActionEvent actionEvent) {
        if (!messageTextField.getText().isEmpty()){
            String messageBody = messageTextField.getText().trim();
            RMIConnector.getRmiConnector().getChattingService().sendGroupMessage(messageBody , 111);
            renderMessage(messageBody);
        }
    }

    private void renderMessage(String messageBody) {
        MessageModel messageModel = new MessageModel();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/contactMessage.fxml"));
        try {
            HBox messageHBox = fxmlLoader.load();
            ContactMessageController messageController = fxmlLoader.getController();
            messageModel.setMessageBody(messageBody);
            messageController.setMessage(messageModel);
            chatVBox.getChildren().add(messageHBox);
            messageTextField.clear();
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

        try {
            FXMLLoader sideContactListLoader = new FXMLLoader(getClass().getResource("/view/sideContactList.fxml"));
            sideContactListPane = sideContactListLoader.load();
            SideContactListController.setController(sideContactListLoader.getController());
            sideContactListController = SideContactListController.getInstance();
            System.out.println("sideContactListPane created");
            paneMap.put("sideContactListPane",sideContactListPane);
            sideNavigationStackPane.getChildren().add(sideContactListPane);

            FXMLLoader profilePaneLoader = new FXMLLoader(getClass().getResource("/view/sideProfilePane.fxml"));
            sideProfilePane = profilePaneLoader.load();
            ProfileController.setController(profilePaneLoader.getController());
            profileController = ProfileController.getInstance();
            System.out.println("sideProfilePane created");
            paneMap.put("sideProfilePane",sideProfilePane);
            sideNavigationStackPane.getChildren().add(sideProfilePane);

            FXMLLoader sideGroupListLoader = new FXMLLoader(getClass().getResource("/view/SideGroupList.fxml"));
            sideGroupPane = sideGroupListLoader.load();
            SideGroupListController.setController(sideGroupListLoader.getController());
            sideGroupListController = SideGroupListController.getInstance();
            System.out.println("sideGroupPane created");
            paneMap.put("sideGroupPane",sideGroupPane);
            sideNavigationStackPane.getChildren().add(sideGroupPane);

            FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/view/sideChatList.fxml"));
            chatListPane = chatLoader.load();
            SideChatListController.setController(chatLoader.getController());
            sideChatListController = SideChatListController.getInstance();
            System.out.println("chatPane created");
            paneMap.put("chatListPane",chatListPane);
            sideNavigationStackPane.getChildren().add(chatListPane);

            System.out.println(sideNavigationStackPane.getChildren().get(sideNavigationStackPane.getChildren().size()-1)==chatListPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
