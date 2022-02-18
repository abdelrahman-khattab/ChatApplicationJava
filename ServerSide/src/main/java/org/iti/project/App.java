package org.iti.project;

import org.iti.project.models.User;
import org.iti.project.network.RMIConnector;
import org.iti.project.presentation.util.StageCoordinator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.iti.project.presistence.dao.UserDAOImpl;
import org.iti.project.presistence.util.DBConnector;

import java.io.IOException;
import java.sql.Connection;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
        stageCoordinator.initStage(stage);
        RMIConnector rmiConnector = new RMIConnector();
        rmiConnector.connectRMI();
        stage.show();


    }



    public static void main(String[] args) {
        launch();
    }

}