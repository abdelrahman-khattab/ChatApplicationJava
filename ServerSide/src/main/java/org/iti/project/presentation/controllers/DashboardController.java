package org.iti.project.presentation.controllers;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.iti.project.services.impls.AdminImpl;
import org.iti.project.services.interfaces.AdminInt;

import javafx.scene.text.Text;
import org.iti.project.services.impls.ContactImpl;
import org.iti.project.services.impls.SignInImpl;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<?, ?> lineChart;
    @FXML
    private static Text onliine = null;
    @FXML
    private PieChart pieChart;

    @FXML
    private static Label onlineUserNumber;

    @FXML
    private static Label OfflineUserNumer;

    private AdminInt adminObj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data();
    }

    public void data() {
       HashMap<String,Integer> gender =  new HashMap<>();
        HashMap<String,Integer> countries =  new HashMap<>();
        try {
            adminObj = new AdminImpl();
            gender.putAll(adminObj.countOfGender());
            countries.putAll(adminObj.countOfCountries());

            // oline , offline users


                // Line Chart
            if(gender != null){
                XYChart.Series dataSeries1 = new XYChart.Series();
                dataSeries1.setName("2014");
                dataSeries1.getData().add(new XYChart.Data("Male", gender.get("Male")));
                dataSeries1.getData().add(new XYChart.Data("Female", gender.get("Female")));
                lineChart.getData().add(dataSeries1);
            }


                // for pie chart
            if(countries != null){
                PieChart.Data slice1 = new PieChart.Data("Egypt", countries.get("Egypt"));
                PieChart.Data slice2 = new PieChart.Data("England", countries.get("Morocco"));
                PieChart.Data slice3 = new PieChart.Data("Others", countries.get("asdas"));
                pieChart.getData().addAll(slice1, slice2, slice3);

            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }





    }
}

