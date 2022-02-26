package org.iti.project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.controlsfx.control.HyperlinkLabel;
import org.iti.project.presentation.util.StageCoordinator;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressBarController implements Initializable {

    @FXML
    private Label DownloadingLabel;

    @FXML
    private HyperlinkLabel FileAccessLabel;

    @FXML
    private ProgressBar downloadProgressBar;

    private static ProgressBarController progressBarController;
    private final StageCoordinator stageCoordinator = StageCoordinator.getStageCoordinator();

    public static void setController(ProgressBarController progressBarController){
        ProgressBarController.progressBarController = progressBarController;
    }

    public static ProgressBarController getInstance() {
        return progressBarController;
    }

    public Label getDownloadingLabel() {
        return DownloadingLabel;
    }

    public void setDownloadingLabel(Label downloadingLabel) {
        DownloadingLabel = downloadingLabel;
    }

    public HyperlinkLabel getFileAccessLabel() {
        return FileAccessLabel;
    }

    public void setFileAccessLabel(HyperlinkLabel fileAccessLabel) {
        FileAccessLabel = fileAccessLabel;
    }

    public ProgressBar getDownloadProgressBar() {
        return downloadProgressBar;
    }

    public void setDownloadProgressBar(ProgressBar downloadProgressBar) {
        this.downloadProgressBar = downloadProgressBar;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        downloadProgressBar.progressProperty().bind();

    }
}
