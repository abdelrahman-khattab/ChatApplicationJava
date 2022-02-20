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
    private StringProperty address = new SimpleStringProperty();
    private StringProperty age = new SimpleStringProperty();
    private StringProperty phoneNo = new SimpleStringProperty();

    public Image getUserImage() {
        return userImage.get();
    }

    public ObjectProperty<Image> userImageProperty() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage.set(userImage);
    }

    private ObjectProperty<Image> userImage = new SimpleObjectProperty(new Image("/images/R.png"));


    public String getPhoneNo() {
        return phoneNo.get();
    }

    public StringProperty phoneNoProperty() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
    }




    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
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

