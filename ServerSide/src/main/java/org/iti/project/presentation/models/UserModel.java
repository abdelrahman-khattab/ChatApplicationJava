package org.iti.project.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class UserModel {
    private static final String DEFAULT_USER_NAME = "USER NAME";
    private StringProperty userUserName = new SimpleStringProperty("User Name");
    private StringProperty userImgURL = new SimpleStringProperty();
    private StringProperty userPassword = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private ObjectProperty<Image> userImage = new SimpleObjectProperty<>(new Image("/R.png"));

    public Image getUserImage() {
        return userImage.get();
    }

    public ObjectProperty<Image> userImageProperty() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage.set(userImage);
    }

    public String getUserUserName() {
        return userUserName.get();
    }

    public StringProperty userUserNameProperty() {
        return userUserName;
    }

    public void setUserUserName(String userUserName) {
        this.userUserName.set(userUserName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUserImgURL() {
        return userImgURL.get();
    }
    public StringProperty userImgURLProperty() {
        return userImgURL;
    }

    public void setUserImgURL(String userImgURL) {
        this.userImgURL.set(userImgURL);
    }

    public String getUserPassword() {
        return userPassword.get();
    }

    public StringProperty userPasswordProperty() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword.set(userPassword);
    }
}

