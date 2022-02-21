package org.iti.project.models;

import javafx.scene.text.Font;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class GroupMessage implements Serializable {
    private int messageId;
    private String groupMessageContent;
    private User sender;
    private int groupId;
    private String groupMessageColor;
    private LocalDateTime messageCreationTime;
    private byte[] groupImage;
    private String fontFamily;
    private String fontWeight;
    private String fontPosture;
    private int fontSize;
//    private Font messageFont;

    public GroupMessage(){}

    public GroupMessage(String groupMessageContent, User sender, int groupId) {
        this.groupMessageContent = groupMessageContent;
        this.sender = sender;
        this.groupId = groupId;
    }
//
//    public Font getMessageFont() {
//        return messageFont;
//    }
//
//    public void setMessageFont(Font messageFont) {
//        this.messageFont = messageFont;
//    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getGroupMessageContent() {
        return groupMessageContent;
    }

    public void setGroupMessageContent(String groupMessageContent) {
        this.groupMessageContent = groupMessageContent;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getMessageCreationTime() {
        return messageCreationTime;
    }

    public void setMessageCreationTime(LocalDateTime messageCreationTime) {
        this.messageCreationTime = messageCreationTime;
    }

    public byte[] getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(byte[] groupImage) {
        this.groupImage = groupImage;
    }

    public String getGroupMessageColor() {
        return groupMessageColor;
    }

    public void setGroupMessageColor(String groupMessageColor) {
        this.groupMessageColor = groupMessageColor;
    }


    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getFontPosture() {
        return fontPosture;
    }

    public void setFontPosture(String fontPosture) {
        this.fontPosture = fontPosture;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }


    @Override
    public String toString() {
        return "GroupMessage{" +
                "messageId=" + messageId +
                ", groupMessageContent='" + groupMessageContent + '\'' +
                ", senderPhoneNumber='" + sender.getUserPhone() + '\'' +
                ", groupId=" + groupId +
                ", messageCreationTime=" + messageCreationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMessage that = (GroupMessage) o;
        return messageId == that.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

}
