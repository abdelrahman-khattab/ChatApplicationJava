package org.iti.project.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class MessageModel {
    private String messageBody ;
    private StringProperty time = new SimpleStringProperty();
    private ObjectProperty<Image> imageObjectProperty = new SimpleObjectProperty<Image>();

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public Image getImageObjectProperty() {
        return imageObjectProperty.get();
    }

    public ObjectProperty<Image> imageObjectPropertyProperty() {
        return imageObjectProperty;
    }

    public void setImageObjectProperty(Image msgSenderImage) {
        this.imageObjectProperty.set(msgSenderImage);
    }
}
