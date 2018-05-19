/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.charts;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Matouš
 */
public class LineChartController implements Initializable {

    @FXML
    private LineChart<String,Number> lchart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    
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
        xAxis.setLabel("Ranges");
        yAxis.setLabel("Frequencies");
        XYChart.Series<String,Number> whatever = new XYChart.Series();
        for (int i = 0; i< array.length;i++){
            whatever.getData().add(new XYChart.Data<>(i + "-" + (i+1), array[i]));
        }
        whatever.setName("Histogram");
        lchart.getData().add(whatever);
    }   
    
    
}
