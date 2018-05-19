/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.charts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Matouš
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart pchart;
    int[] array = new int[10];
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i<array.length;i++) array[i]=0;
        for (int i = 0; i< 1000; i ++){
            double value = Math.random() * 10;
            array[(int) Math.floor(value)]++;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i< array.length;i++){
            pieChartData.add(new PieChart.Data(""+i,array[i]));
        }
        pchart.setData(pieChartData);
        pchart.setTitle("Pie Chart");
    }    
    
}
