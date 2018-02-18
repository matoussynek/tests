/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import static javafx.application.Platform.exit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Matouš
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Circle bird;
    @FXML
    private Pane gameField;
    
    Timeline timeline = new Timeline();
    
    private final int H = 600;
    private final int W = 800;
    
    private Bird b;
    
    private Queue<Pipes> pip;
    
    private save s;
    
    private int frameCounter =0;
    
    private Background bgd;
    
    private int score =0;
    private int hs = score;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label hsL;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        gameField.setPrefSize(W, H);
        
        bgd = new Background(new BackgroundFill(Color.PINK,CornerRadii.EMPTY,Insets.EMPTY));
        gameField.setBackground(bgd);
        
        bird.setTranslateX(30);
        bird.setTranslateY(H/2);
        
        b = new Bird(bird);
        
        
        pip = new LinkedList<>();
        pip.add(new Pipes(gameField));

        try{
            s = new save();
            hs = s.load();
        }
        catch(Exception e){
        }
        if (hs >-1){
            hsL.setText("HS: " +(hs));
        }
        else hs = 0;
        run();
    }    

    private void run(){
        KeyFrame frame;
        frame = new KeyFrame(Duration.millis(15), event ->{
            frameCounter++;
            b.update();
            if (frameCounter == 80){
                frameCounter =0;
                pip.add(new Pipes(gameField));
            }
            for (Iterator<Pipes> it = pip.iterator(); it.hasNext();) {
                Pipes p = it.next();
                if (p.update()){
                    pip.remove();
                }
                if (!p.collide(b) && !p.pipeChecked) {
                    scoreLabel.setText("Score: " +(++score));
                    hs = (hs<score) ? score : hs;
                    hsL.setText("HS: " +(hs));
                    p.pipeChecked = true;
                }
                if (!p.passed){
                    score = 0;
                    scoreLabel.setText("Score: " +score);
                }
            }
            
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        
    }
    @FXML
    private void up(KeyEvent event) throws Exception{
        if (event.getCode() == KeyCode.SPACE){
            b.up();
        }
        else if (event.getCode()== KeyCode.ESCAPE){
            s.write(hs);
            exit();
        }
        
    }
    
}
