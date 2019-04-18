/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import scene.Texture;
import game.Game;
import java.awt.*;
import scene.Animation;

/**
 *
 * @author Kai
 */
public class Player extends GameObject {
    Texture tex = Texture.getInstance();
    private Animation playerWalk;

    public Player(int x, int y,ObjectID id) {
        super(x, y,id);
        this.width = 128;
        this.height = 256;
        this.playerWalk = new Animation(8, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                                                 tex.player[6], tex.player[7], tex.player[8], tex.player[9]);
    }

    @Override
    public boolean checkBorder() {
        this.xDest = x + xVel;
            if(super.checkBorder()){
                if(xDest + width > Game.WIDTH){
                    this.xVel = 0;
                }else if(xDest < 0){
                    this.xVel = 0;
                }
            }
        
        return true;
    }
    
    

    @Override //move method
    public void tick() {
        x += xVel;
        playerWalk.runAnimation();
        this.checkBorder();

    }

    @Override   //testing
    public void render(Graphics g) {
        //BufferedImage test1 = img.getSubimage(0,0,this.width,this.height);
        if(this.xVel > 0){
            playerWalk.renderAnimation(g, x, y, width, height);
        }else if(this.xVel < 0){
            playerWalk.renderAnimation(g, x + width, y, -width, height);
        }
        else{
            g.drawImage(tex.player[0],x,y,width,height,null);
        }
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
    
    
    
}
