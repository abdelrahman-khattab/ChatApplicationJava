package org.iti.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.controllers.ChatScreenController;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.presentation.util.StageCoordinator;
import org.iti.project.services.impls.ClientCallBack;

import java.io.IOException;
import java.sql.Connection;

/**
 * JavaFX App
 */
public class ClientApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
        stageCoordinator.initStage(stage);
        stage.show();



    }



    public static void main(String[] args) {
        launch();


    }

}