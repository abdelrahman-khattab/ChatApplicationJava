package org.iti.project.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SingleMessage implements Serializable {
    private int messageId;
    private String singleMessageContent;
    private User sender;
    private String receiverPhoneNumber;
    private String singleMessageColor;
    private LocalDateTime messageCreationTime;
    private byte[] receiverImage;
    private String fontFamily;
    private String fontWeight;
    private String fontPosture;
    private boolean fontUnderLine;
    private int fontSize;

    public SingleMessage(){}

    public SingleMessage(String singleMessageContent, User sender, String receiverPhoneNumber) {
        this.singleMessageContent = singleMessageContent;
        this.sender = sender;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSingleMessageContent() {
        return singleMessageContent;
    }

    public void setSingleMessageContent(String singleMessageContent) {
        this.singleMessageContent = singleMessageContent;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getSingleMessageColor() {
        return singleMessageColor;
    }

    public void setSingleMessageColor(String singleMessageColor) {
        this.singleMessageColor = singleMessageColor;
    }

    public LocalDateTime getMessageCreationTime() {
        return messageCreationTime;
    }

    public void setMessageCreationTime(LocalDateTime messageCreationTime) {
        this.messageCreationTime = messageCreationTime;
    }

    public byte[] getReceiverImage() {
        return receiverImage;
    }

    public void setReceiverImage(byte[] receiverImage) {
        this.receiverImage = receiverImage;
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

    public boolean isFontUnderLine() {
        return fontUnderLine;
    }

    public void setFontUnderLine(boolean fontUnderLine) {
        this.fontUnderLine = fontUnderLine;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleMessage that = (SingleMessage) o;
        return messageId == that.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        return "SingleMessage{" +
                "messageId=" + messageId +
                ", singleMessageContent='" + singleMessageContent + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
