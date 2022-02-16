package gov.iti.jets.project;

import gov.iti.jets.project.presentation.util.StageCoordinator;
import gov.iti.jets.project.presistence.dao.UserDAOImpl;
import gov.iti.jets.project.presistence.entities.User;
import gov.iti.jets.project.presistence.util.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
              // init db
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // close conn pool
        // close rmi
    }

    public static void main(String[] args) {

        Connection conn = DBConnector.getConnection().connect();
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = new User();
        user.setGender("asdas");
        user.setUserCountry("asdas");
        user.setImage("asdas");
        user.setUserEmail("asdas");
        user.setUserName("asdas");
        user.setUserPassword("asdas");
        user.setUserDOB("asdas");
        user.setUserPhone("jjj");
        user.setUserBio("jjj");

        userDAO.insertUser(user);
        launch();
    }

}