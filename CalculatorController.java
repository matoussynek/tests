/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javafx.scene.input.KeyCode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Matouš y Manu pero solo hace el design. Lul
 */
public class CalculatorController implements Initializable {
    @FXML
    private VBox vbox;
    @FXML
    private TextField txtfld;
    @FXML
    private GridPane grdpn;
    @FXML
    private Button del;
    @FXML
    private Button div;
    @FXML
    private Button mul;
    @FXML
    private Button minus;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button plus;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button equ;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button zero;
    @FXML
    private Button dot;


   /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtfld.setAlignment(Pos.CENTER_RIGHT);
    }    

    /**
     * 
     * @param event 
     */
    boolean done = false;
    @FXML
    private void showNum(ActionEvent event) {
        if (done == true) txtfld.clear();
        txtfld.setText(txtfld.getText()+((Button)event.getSource()).getText());
        done = false;
    }
    
    
    int a;
    float af;
    char op;
    boolean opUsed = false;
    boolean dotUsed = false;
    
    @FXML
    private void setOperation(ActionEvent event) {
        if (opUsed){
            Equals();
        }
        if (dotUsed){
            af = Float.parseFloat(txtfld.getText());
        }
        else{
            a = Integer.parseInt(txtfld.getText());
            af = a;
        }
        op = (((Button)event.getSource()).getText()).charAt(0);
        done = true;
        opUsed = true;
        
    }
    
    int b;
    float bf;
    
    @FXML
    private void Equals() {
        float out = 0;
        if (dotUsed){
            bf = Float.parseFloat(txtfld.getText());
        }
        else{
            b = Integer.parseInt(txtfld.getText());
        }
        switch (op) {
            case '+' :
                out = (dotUsed) ? af + bf : a + b;
                break;
            case '-' :
                out = (dotUsed) ? af - bf : a - b;
                break;
            case 'X' :
                out = (dotUsed) ? af * bf : a * b;
                break;
            case '/' :
                out = (dotUsed) ? af / bf : a / b;
                break;
            default :
                txtfld.setText("Error");
                break;
        }
        if (dotUsed){
            txtfld.setText(Float.toString(out));
        }
        else{
            int temp = (int)out;
            txtfld.setText(Integer.toString(temp));
        }
        done = true;
        a=0;
        b=0;
        op=0;
        opUsed = false;
        
    }
    
    @FXML
    private void Clear() {
        txtfld.clear();
        a=0;
        b=0;
        op=0;
        done = false;
        dotUsed = false;
    }
    @FXML
    private void floatNumber(ActionEvent event) {
        dotUsed = true;
        txtfld.setText(txtfld.getText()+((Button)event.getSource()).getText());
    }
    /*
    @FXML
    private void keyListener(KeyEvent event){
        System.out.println(event.getCode().toString());
        switch (event.getCode().toString()){
            case "NUMPAD0":
                showNum((ActionEvent) zero.getOnAction());
                break;
            case "NUMPAD1":
                showNum((ActionEvent) one.getOnAction());
                break;
            case "NUMPAD2":
                showNum((ActionEvent) two.getOnAction());
                break;
            case "NUMPAD3":
                showNum((ActionEvent) three.getOnAction());
                break;
            case "NUMPAD4":
                showNum((ActionEvent) four.getOnAction());
                break;
            case "NUMPAD5":
                showNum((ActionEvent) five.getOnAction());
                break;
            case "NUMPAD6":
                showNum((ActionEvent) six.getOnAction());
                break;
            case "NUMPAD7":
                showNum((ActionEvent) seven.getOnAction());
                break;
            case "NUMPAD8":
                showNum((ActionEvent) eight.getOnAction());
                break;
            case "NUMPAD9":
                showNum((ActionEvent) nine.getOnAction());
                break;
            case "ADD":
                setOperation((ActionEvent) plus.getOnAction());
                break;
            case "SUBTRACT":
                setOperation((ActionEvent) minus.getOnAction());
                break;
            case "MULTIPLY":
                setOperation((ActionEvent) mul.getOnAction());
                break;
            case "DIVIDE":
                setOperation((ActionEvent) div.getOnAction());
                break;
            case "C":
                Clear();
                break;
            case "EQUALS":
                Equals();
                break;
            case "DECIMAL" :
                floatNumber((ActionEvent) dot.getOnAction());
                break;
            default:
                break;
        }
    }
*/
}

