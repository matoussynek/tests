/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametry;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Matouš
 */
public class FXMLDocumentController implements Initializable {
    private Circle circle;
    
    static int column = 2;
    static int row = 2;
    
    private final static int MAX_ROW = 4;
    private final static int MAX_COL = 4;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //circle.setFill(Color.BLACK);
    }    
    @FXML
    private void mouseEntr(MouseEvent event){
        System.out.println("Mouse clicked at: X " +event.getX() +" Y " +event.getY());
        GridPane.setRowIndex(circle, row);
        GridPane.setColumnIndex(circle,column);
        
    }
    public static void move(KeyEvent event) {
         if (event.getCode() == KeyCode.UP){
             if (row-1>=0){
                 row--;
             } 
             //row = ((row+1)<=4) ? row++ : row;
         }
         else if (event.getCode() == KeyCode.DOWN){
            if (row+1<=MAX_ROW){
                 row++;
             }
            //row = ((row-1)<0) ? row : row--;
         }
         else if (event.getCode() == KeyCode.RIGHT){
              if (column+1<=MAX_COL){
                 column++;
             }
         }
         else if (event.getCode() == KeyCode.LEFT){
             if (column-1>=0){
                 column--;
             }
         }
         else if (event.getCode()== KeyCode.ESCAPE){
             exit();
         }
         System.out.println("X: " +column +" Y: "+row);
    }
    

    
}
