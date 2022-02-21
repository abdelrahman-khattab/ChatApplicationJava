package org.iti.project.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class UserModel {
    private static final String DEFAULT_USER_NAME = "USER NAME";
    private StringProperty userUserName = new SimpleStringProperty();
    private StringProperty userPassword = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phoneNo = new SimpleStringProperty();
    private StringProperty userCountry = new SimpleStringProperty();
    private StringProperty userGender = new SimpleStringProperty();
    private ObjectProperty<Image> userImage = new SimpleObjectProperty(new Image("/images/R.png"));

    public String getUserCountry() {
        return userCountry.get();
    }

    public StringProperty userCountryProperty() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry.set(userCountry);
    }

    public String getUserGender() {
        return userGender.get();
    }

    public StringProperty userGenderProperty() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender.set(userGender);
    }

    public Image getUserImage() {
        return userImage.get();
    }

    public ObjectProperty<Image> userImageProperty() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage.set(userImage);
    }


    public String getPhoneNo() {
        return phoneNo.get();
    }

    public StringProperty phoneNoProperty() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
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

