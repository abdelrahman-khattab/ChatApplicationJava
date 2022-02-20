package org.iti.project.models;

import javafx.scene.text.Font;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class GroupMessage implements Serializable {
    private int messageId;
    private String groupMessageContent;
    private String senderPhoneNumber;
    private int groupId;
    private LocalDateTime messageCreationTime;
    private Font groupMessageFont;

    public GroupMessage(String groupMessageContent, String senderPhoneNumber, int groupId) {
        this.groupMessageContent = groupMessageContent;
        this.senderPhoneNumber = senderPhoneNumber;
        this.groupId = groupId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getGroupMessageContent() {
        return groupMessageContent;
    }

    public void setGroupMessageContent(String groupMessageContent) {
        this.groupMessageContent = groupMessageContent;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
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

    @Override
    public String toString() {
        return "GroupMessage{" +
                "messageId=" + messageId +
                ", groupMessageContent='" + groupMessageContent + '\'' +
                ", senderPhoneNumber='" + senderPhoneNumber + '\'' +
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
