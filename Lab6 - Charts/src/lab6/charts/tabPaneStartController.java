/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.charts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Matouš
 */
public class tabPaneStartController implements Initializable {
    
    private Label label;
    @FXML
    private TabPane tpane;
    @FXML
    private Tab lineTab;
    @FXML
    private Tab barTab;
    @FXML
    private Tab pieTab;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tpane.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue) -> { 
            if (newValue.equals(lineTab)){
                try{
                    lineTab.setContent((Node) FXMLLoader.load(this.getClass().getResource("lineChart.fxml")));}
                catch(Exception e){}
            }
            if (newValue.equals(barTab)){
                try{
                    barTab.setContent((Node) FXMLLoader.load(this.getClass().getResource("barChart.fxml")));}
                catch(Exception e){}
            }
            if (newValue.equals(pieTab)){
                try{
                    pieTab.setContent((Node) FXMLLoader.load(this.getClass().getResource("pieChart.fxml")));}
                catch(Exception e){}
            }
        });
        try{
            lineTab.setContent((Node) FXMLLoader.load(this.getClass().getResource("lineChart.fxml")));}
        catch(Exception e){}
    }    
    
}
