/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametry;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.awt.Toolkit;
import javafx.event.EventHandler;
import javafx.event.EventType;

/**
 * FXML Controller class
 *
 * @author Matouš
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Circle coin;
    @FXML
    private GridPane bullshit;
    @FXML
    private Label label;
    @FXML
    private ImageView heart;

    
    static int column =3;
    static int row = 3;
    
    static int Crow = 3;
    static int Ccol = 3;
    
    private Toolkit toolkit;
    private int score;
    

    private final static int MAX_ROW = 9;
    private final static int MAX_COL = 9;
    private Background bgd;
    
    private Random r;
    @FXML
    private VBox box;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }
    private void init(){
        
        
        score = 0;
        label.setText(" Score: 0");
        
        r = new Random();
        row = r.nextInt(MAX_ROW);
        column = r.nextInt(MAX_COL);
        Crow = r.nextInt(MAX_ROW);
        Ccol = r.nextInt(MAX_COL);

        GridPane.setColumnIndex(heart,column);
        GridPane.setRowIndex(heart, row);
        
        GridPane.setColumnIndex(coin,Ccol);
        GridPane.setRowIndex(coin, Crow);
        

        toolkit = Toolkit.getDefaultToolkit();
    }
    @FXML
    private void mouseEntr(MouseEvent event){
        System.out.println("Mouse clicked at: X " +event.getX() +" Y " +event.getY());
    }
    
    @FXML
    private void move(KeyEvent event) {
         heart.setScaleX(1);
         heart.setRotate(0);
         switch (event.getCode()){
            case UP :
                row = (row -1 <0) ? MAX_ROW :(row -1) % MAX_ROW;
                heart.setScaleX(1);
                heart.setRotate(-90);
                break;
         
            case DOWN:
                row = (row + 1) % MAX_ROW;
                heart.setScaleX(1);
                heart.setRotate(90);
                break;
         
            case RIGHT:
                column = (column + 1) % MAX_COL;
                break;
         
             case LEFT:
                column = (column -1 <0) ? MAX_COL :(column -1) % MAX_COL;
                heart.setScaleX(-1);
                break;
             
             case ESCAPE:
                exit();
                break;
             case N:
                 newGame();
                 break;
    }
         
         if ((row == Crow) && (column == Ccol)){
             eatCoin();
         }
         
         GridPane.setColumnIndex(heart,column);
         GridPane.setRowIndex(heart, row);
    }
    private void eatCoin(){
        while (row==Crow){
            Crow = r.nextInt(MAX_ROW);
        }
        while (column==Ccol){
            Ccol = r.nextInt(MAX_COL);
        }
        toolkit.beep();
        score++;
        label.setText("Score: " + score);
        GridPane.setColumnIndex(coin,Ccol);
        GridPane.setRowIndex(coin, Crow);
        
        if ((score%3)==0){
            bgd = new Background(new BackgroundFill(Color.color(gimmieFloat(),gimmieFloat(),gimmieFloat()),CornerRadii.EMPTY,Insets.EMPTY));
            box.setBackground(bgd);
        }

    }
    @FXML
    private void close(){
        exit();
    }
    @FXML
    private void newGame(){
        init();
    }

    private float gimmieFloat(){
        float f = r.nextFloat();
        while(f<0.75) f = r.nextFloat();
        System.out.println("F = " +f);
        return f;
    }
    

    
}
