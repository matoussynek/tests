/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Matouš
 */
public class Pipes {
    Random r;
    int top;
    int bot;
    int w = 60;
    int x = 800;
    Color col = Color.GREEN;
    Rectangle t;
    Rectangle b;
    int speed = 5;
    Pane gameField;
    boolean pipeChecked = false;
    boolean passed = true;
    
    public Pipes(Pane scene){
        r = new Random();
        gameField = scene;
        top = r.nextInt(600/2-30);
        bot = r.nextInt(600/2-30);
        t = new Rectangle(w,top,col);
        b = new Rectangle(w,bot,col);
        gameField.getChildren().add(t);
        gameField.getChildren().add(b);
        t.setTranslateX(x);
        t.setTranslateY(0);
        b.setTranslateX(x);
        b.setTranslateY(600-bot);
        t.setFill(col);
        b.setFill(col);
        
    }
    
    public boolean update(){
        x-=speed;
        t.setTranslateX(x);
        b.setTranslateX(x);
        if (x+w<0){
            gameField.getChildren().remove(t);
            gameField.getChildren().remove(b);     
            return true;
        }
        return false;
    }
    public boolean collide(Bird bird){
        if (bird.bird.getTranslateX()>x && bird.bird.getTranslateX()<x+w ){
            if (bird.bird.getTranslateY()<top+bird.r || bird.bird.getTranslateY()>b.getTranslateY()-bird.r){
                t.setFill(Color.RED);
                b.setFill(Color.RED);
                passed = false;
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
}
