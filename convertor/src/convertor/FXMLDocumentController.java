/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Matouš
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Slider slayer;
    @FXML
    private Label out;
    @FXML
    private Label rate;
    @FXML
    private TextField inbox;
    @FXML
    private Label in;
    @FXML
    private TextField outbox;
    @FXML
    private CheckBox check;
    @FXML
    private Button convert;
    @FXML
    private Button clear;
    @FXML
    private VBox box;
    
    private double r;
    private double a;
    private double b;
    boolean selected = false;
    
    private Background bgd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rate.setText("Conversion rate: "+slayer.getValue());
        slayer.valueProperty().addListener((observable, oldValue, newValue) -> {

            rate.setText("Conversion rate: "+round((double) newValue,2));
            if (selected){
                convert();
            }
        });
        
        inbox.textProperty().addListener((observable,oldValue,newValue) ->{
            if (selected){
                convert();
            }
        });
        bgd = new Background(new BackgroundFill(Color.PINK,CornerRadii.EMPTY,Insets.EMPTY));
            box.setBackground(bgd);
    } 
    
    @FXML
    private void convert() {
        r = slayer.getValue();
        try {
            a = Double.parseDouble(inbox.getText());
        }
        catch (NumberFormatException e) {
            inbox.clear();
        }
        outbox.setText(""+round((r*a),2));
        
    }

    
    @FXML
    private void select(){
        selected = !selected;
    }
    
    @FXML
    private void clear(){
        rate.setText("Conversion rate: "+0);
        inbox.clear();
        outbox.clear();
        a = r  = b = 0;
        slayer.setValue(0);
        selected = false;
        check.setSelected(false);
        
        
    }
    public double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
}
