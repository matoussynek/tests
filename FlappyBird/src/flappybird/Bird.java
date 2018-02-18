/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import javafx.scene.shape.Circle;

/**
 *
 * @author Matouš
 */
public class Bird {
    Circle bird;
    int r;
    int x = 30;
    int y;
    double velocity = 0;
    double gravity = 0.6;
    int lift = 15;
    
    public Bird(Circle b){
        bird = b;
        r = (int)bird.getRadius();
        y = (int)bird.getTranslateY();
    }
    public void update(){
        velocity+=gravity;
        velocity*=0.9;
        y += velocity;
        bird.setTranslateY(y);
        
        if (y > 600 - r || y<0){
            velocity = 0;
        }
    }
    public void up(){
        velocity -=lift;
    }
}
