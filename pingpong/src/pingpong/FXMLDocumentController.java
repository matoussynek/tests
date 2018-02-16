/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Matouš
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private AnchorPane gameField;
    @FXML
    private Rectangle player;
    @FXML
    private Circle ball;
    @FXML
    private Label scr;  
    
    
    private final int GF_H = 600;
    private final int GF_W = 800;
    private int P_H = 20;
    private int P_W = 150;
    private int pSpeed = 30;
    private int bDefX =30;
    private int bDefY =100;
    
    private int BSPEED = 5;
    private int bSpeedX = BSPEED;
    private double bSpeedY = BSPEED;
    
    private Timeline timeline = new Timeline();
    
    private boolean running = false;
    
    private int score =0;
    
    private Background bgd;
    
    public final int NOB = 4;
    
    private Bricks b;

    int cycleCount =0;
    @FXML
    private Label instruction;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        b = new Bricks(gameField);
        b.set();
        
        bgd = new Background(new BackgroundFill(Color.PINK,CornerRadii.EMPTY,Insets.EMPTY));
        gameField.setBackground(bgd);
        
        
        gameField.setPrefHeight(GF_H);
        gameField.setPrefWidth(GF_W);
                      
        scr.setTranslateX(GF_W/2-100);
        scr.setTranslateY(50);
        scr.setAlignment(Pos.CENTER);
        
        ball.setTranslateX(bDefX);
        ball.setTranslateY(bDefY);
        ball.setFill(Color.CRIMSON);
        ball.setStroke(ball.getFill());
        
        player.setHeight(P_H);
        player.setWidth(P_W);
        
        player.setTranslateY(GF_H - P_H - 5);
        player.setTranslateX(GF_W/2-P_W/2);
        player.setStroke(player.getFill());
        
        player.setFocusTraversable(true);
        
        instruction.setTranslateX(-25);
        instruction.setTranslateY(300);
        
        run();
        
    }    
    
    private void run(){
        
        KeyFrame frame;
        frame = new KeyFrame(Duration.millis(15), event ->{
            if (!running) {
                instruction.setVisible(true);
                return;
            }
            instruction.setVisible(false);
            cycleCount++;
            if (cycleCount ==50){
                cycleCount = 0;
                b.move();
            }
            ball.setTranslateX(ball.getTranslateX()+bSpeedX);
            ball.setTranslateY(ball.getTranslateY()+bSpeedY);
            
            if (ball.getTranslateX() <=ball.getRadius()){
                bSpeedX = BSPEED;
            }
            else if (ball.getTranslateX()>=GF_W-ball.getRadius()){
                bSpeedX = -BSPEED;
            }
            if (ball.getTranslateY() <=ball.getRadius() ){
                bSpeedY = BSPEED;
            }
            
            
            
            if (ball.getTranslateY()+ball.getRadius() == player.getTranslateY() && (ball.getTranslateX()>player.getTranslateX()-ball.getRadius() && ball.getTranslateX()< player.getTranslateX()+P_W+ball.getRadius())){
                bSpeedY = -BSPEED;
            }
            else if (ball.getTranslateY()>=GF_H){
                System.out.println("Game Over!");
                ball.setTranslateX(bDefX);
                ball.setTranslateY(bDefY);
                score = 0;
                scr.setText("" +0);
                b.restart();
            }
            
            score ++;
            switch (b.collide(ball)){
                case UP:
                    System.out.println("top");
                    bSpeedY = -BSPEED;
                    break;
                case DOWN:
                    System.out.println("bot");
                    bSpeedY = BSPEED;
                    break;
                case RIGHT:
                    System.out.println("right");
                    bSpeedX = BSPEED;
                    break;
                case LEFT:
                    System.out.println("left");
                    bSpeedX = -BSPEED;
                    break;
                default:
                    score--;
                    break;
            }
            scr.setText("" +(score));
            
            
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return;
    }
    
    @FXML
    private void moveP(KeyEvent event){
        switch (event.getCode()){
            case LEFT:
                if (player.getTranslateX()>0){
                    player.setTranslateX(player.getTranslateX()-pSpeed);
                }
                running = true;
                break;
            case RIGHT:
                if (player.getTranslateX()<GF_W-P_W){
                    player.setTranslateX(player.getTranslateX()+pSpeed);
                }
                running = true;
                break;
            case SPACE:
                running = (running) ? false : true ;
                break;
            case ESCAPE:
                exit();
                break;
        }
    }
}
