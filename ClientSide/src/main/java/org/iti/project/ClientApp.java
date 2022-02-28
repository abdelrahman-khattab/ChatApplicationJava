package org.iti.project;

//Import section
import javafx.application.Application;
import javafx.stage.Stage;
import org.iti.project.presentation.util.StageCoordinator;


/**
 * JavaFX App
 */
public class ClientApp extends Application {


    @Override
    public void start(Stage stage) {

        StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();
        stageCoordinator.initStage(stage);
        stage.show();
    }



    public static void main(String[] args) {
        launch();

    }

}