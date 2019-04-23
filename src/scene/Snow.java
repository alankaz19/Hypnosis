/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import game.Game;
import java.awt.Graphics;

/**
 *
 * @author Kai
 */
public class Snow {
    double x = Math.random() * Game.WIDTH;
    double y = (Math.random() *-500) +50;
    double yVel = Math.random() * 2.5 ;
    double xVel = Math.random() * 1 ;
    
    
    public void tick(){
        y += yVel;
        x -= xVel;
        if( y > Game.HEIGHT){
            y = Math.random() * -400;
        }
        if(x < 0){
            x = Game.WIDTH;
        }
    }
    
    public void redner(Graphics g){
        g.setColor(java.awt.Color.white);
        g.drawLine((int)x, (int)y, (int)x, (int)y+3);
    }
}
