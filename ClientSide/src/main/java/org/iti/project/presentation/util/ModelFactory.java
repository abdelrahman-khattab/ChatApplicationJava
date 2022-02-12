package org.iti.project.presentation.util;

import org.iti.project.presentation.models.UserModel;

public class ModelFactory {
    private static ModelFactory modelFactory = new ModelFactory();

    private UserModel userModel = new UserModel();

    private ModelFactory(){

    }

    public static ModelFactory getModelFactory() {
        return modelFactory;
    }

    public UserModel getUserModel(){
        return userModel;
    }
}
