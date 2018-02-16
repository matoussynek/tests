/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import com.sun.javafx.scene.traversal.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Matouš
 */



public class Bricks {
    Rectangle [] array;
    public final double w = 150;
    public final double h = 20;
    AnchorPane scene;
    public int len = 16;
    Timeline timeline;
        
    public Bricks(AnchorPane s){
        array = new Rectangle[len];
        scene = s;
        timeline = new Timeline();

    }
    public void set(){
        for (int i = 0;i<array.length;i++){
            array[i] = new Rectangle(w,h,Color.AQUAMARINE);
            scene.getChildren().add(array[i]);
            array[i].setTranslateX(85+(i%4)*(w+10));
            array[i].setTranslateY(50-(int)(i/4)*(-40));
            System.out.println("I "+i+" X " +array[i].getTranslateX() + " Y " +array[i].getTranslateY());
        }
        
    }
    public void restart(){
        for (int i = 0;i<array.length;i++){
            array[i].setVisible(true);
            array[i].setTranslateY(50-(int)(i/4)*(-40));
        }
    }
    
    public Direction collide(Circle ball){
        int allDis = 0;
        for (int i = 0;i<array.length;i++)
        {
            if (array[i].isVisible()){
               if (ball.getTranslateY()+ball.getRadius() == array[i].getTranslateY() && (ball.getTranslateX()>array[i].getTranslateX()-ball.getRadius() && ball.getTranslateX()< array[i].getTranslateX()+w+ball.getRadius())){
                    array[i].setVisible(false);
                    return Direction.UP; 
                }
                else if ((array[i].getTranslateY()+h == ball.getTranslateY()-ball.getRadius()) && (array[i].getTranslateX()<ball.getTranslateX()+ball.getRadius()&& array[i].getTranslateX()+w>=ball.getTranslateX()-ball.getRadius())){
                    array[i].setVisible(false);
                    return Direction.DOWN; 
                }
                else if ((array[i].getTranslateX()+w == ball.getTranslateX()-ball.getRadius()) && (array[i].getTranslateY()-ball.getRadius()<ball.getTranslateY() && array[i].getTranslateY()+h+ball.getRadius()>=ball.getTranslateY())){
                    array[i].setVisible(false);
                    return Direction.RIGHT; 
                }
                else if ((array[i].getTranslateX() == ball.getTranslateX()+ball.getRadius()) && (array[i].getTranslateY()-ball.getRadius()<ball.getTranslateY() && array[i].getTranslateY()+h+ball.getRadius()>=ball.getTranslateY())){
                    array[i].setVisible(false);
                    return Direction.LEFT;
                }
            }
            else allDis++;
        }
        if (allDis == array.length) restart();
        return Direction.NEXT;
    }
    
    public void move(){
            for (int i = 0;i<array.length;i++){
               array[i].setTranslateY(array[i].getTranslateY()+5); 
            }
    }
}
