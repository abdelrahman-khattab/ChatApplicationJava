package org.iti.project.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import org.iti.project.services.impls.AdminImpl;
import org.iti.project.services.interfaces.AdminInt;


public class DashBoardController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChar;

    @FXML
    private PieChart pieChart;
    @FXML
    private  Label onlineUserNumber;
    @FXML
    private Label OfflineUserNumer;
    @FXML
    private Label totalUserNumber;
    private AdminInt adminObj;
    private static DashBoardController dashBoardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int onlineUsersCounter=0 ;
        int totalUsersCounter=0 ;
        //int [] gender={0,0};
        updateStatistics();
        data( onlineUsersCounter,  totalUsersCounter);
        System.out.println("intialize DashBoard");
    }

    public static void setController(DashBoardController dashBoardController) {
        DashBoardController.dashBoardController = dashBoardController;
    }
    public static DashBoardController getInstance(){
        return dashBoardController;
    }

    public void data(int onlineUsers, int TotalUsers){
        System.out.println("show Data");

        onlineUserNumber.setText(String.valueOf(onlineUsers));
        OfflineUserNumer.setText(String.valueOf(TotalUsers-onlineUsers));
        totalUserNumber.setText(String.valueOf(TotalUsers));



        // Rectangle clip = new Rectangle(
        // imageView.getFitWidth(), imageView.getFitHeight());
        // clip.setArcWidth(100);
        // clip.setArcHeight(100);
        // imageView.setClip(clip);
        // imageView.setEffect(new DropShadow(20, Color.BLACK));




    }

    public void updateStatistics() {

        HashMap<String, Integer> countries = new HashMap<>();
        try {
            adminObj = new AdminImpl();

            countries.clear();
            countries.putAll(adminObj.countOfCountries());

            createBarChart();
            // for pie chart
            if (countries != null) {
                ObservableList<PieChart.Data> pieChartDate = FXCollections.observableArrayList(
                        new PieChart.Data("Egypt", countries.get("Egypt")),
                        new PieChart.Data("England", countries.get("Morocco")),
                        new PieChart.Data("Others", countries.get("asdas"))

                );
                pieChart.setData(pieChartDate);
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public  void createBarChart() {
        HashMap<String, Integer> gender = new HashMap<>();
        gender.clear();
        try {
            gender.putAll(adminObj.countOfGender());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ObservableList<Series<String, Integer>> answer = FXCollections.observableArrayList();

        Series<String, Integer> barChartFemaleSeries = new Series<String, Integer>();

        Series<String, Integer> barChartMaleSeries = new Series<String, Integer>();

        barChartFemaleSeries.getData().add(new XYChart.Data<String, Integer>("Female", gender.get("Female")));
        barChartMaleSeries .getData().add(new XYChart.Data<String, Integer>("Male", gender.get("Male")));
        //barChar.getStylesheets().add("barchartsample/Chart.css");

        answer.addAll(barChartFemaleSeries ,barChartMaleSeries );
        barChar.setData(answer);
    }

}