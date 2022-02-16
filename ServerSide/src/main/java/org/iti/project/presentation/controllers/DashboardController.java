package org.iti.project.presentation.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data();
    }

    public void data() {
        // Line Chart
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");
        dataSeries1.getData().add(new XYChart.Data("Male", 100));
        dataSeries1.getData().add(new XYChart.Data("Female", 50));
        lineChart.getData().add(dataSeries1);

        // for pie chart
        PieChart.Data slice1 = new PieChart.Data("Egypt", 100);
        PieChart.Data slice2 = new PieChart.Data("England", 30);
        PieChart.Data slice3 = new PieChart.Data("Others", 20);
        pieChart.getData().addAll(slice1, slice2, slice3);

        // Rectangle clip = new Rectangle(
        // imageView.getFitWidth(), imageView.getFitHeight());
        // clip.setArcWidth(100);
        // clip.setArcHeight(100);
        // imageView.setClip(clip);
        // imageView.setEffect(new DropShadow(20, Color.BLACK));

    }

}
