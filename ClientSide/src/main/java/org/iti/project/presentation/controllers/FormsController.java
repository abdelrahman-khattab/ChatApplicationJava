package org.iti.project.presentation.controllers;

import org.iti.project.presentation.models.UserModel;
import org.iti.project.presentation.util.ModelFactory;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormsController implements Initializable {
    private final ModelFactory modelFactory = ModelFactory.getModelFactory();
    UserModel userModel;
    @FXML
    private VBox animateVbox;

    VBox fxml;
    @FXML
    private AnchorPane anchPane;

    Stage stage;

    @FXML
    void login()
    {
        TranslateTransition tr = new TranslateTransition(Duration.seconds(1),animateVbox);
        tr.setToX(animateVbox.getLayoutX() * 0);
        tr.play();
        tr.setOnFinished(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxml= fxmlLoader.load(getClass().getResource("/view/login.fxml"));
                System.out.println(fxml==null);
                animateVbox.getChildren().removeAll();
                animateVbox.getChildren().setAll(fxml);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }


    @FXML
    void register()
    {
        TranslateTransition tr = new TranslateTransition(Duration.seconds(1),animateVbox);
        tr.setToX(-animateVbox.getLayoutX()+20);
        tr.play();
        tr.setOnFinished(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxml= fxmlLoader.load(getClass().getResource("/view/Register.fxml"));
                System.out.println(fxml==null);
                animateVbox.getChildren().removeAll();
                animateVbox.getChildren().setAll(fxml);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    @FXML
    void closeStage()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Application");
        alert.setHeaderText("You're about close application");
        alert.setContentText("Are you sure you want to close");

        if (alert.showAndWait().get()==ButtonType.OK)
        {
            stage =(Stage) anchPane.getScene().getWindow();
            stage.close();
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TranslateTransition tr = new TranslateTransition(Duration.seconds(1),animateVbox);
        //tr.setToX(100);
        tr.play();
        tr.setOnFinished(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxml= fxmlLoader.load(getClass().getResource("/view/login.fxml"));
                System.out.println(fxml==null);
                animateVbox.getChildren().removeAll();
                animateVbox.getChildren().setAll(fxml);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}
