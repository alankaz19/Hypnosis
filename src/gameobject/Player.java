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
        this.dir = 1;
        this.playerWalk = new Animation(8, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                                           tex.player[6], tex.player[7], tex.player[8], tex.player[9]);
    }
    
    @Override
    public boolean checkBorder() {
        this.xDest = x + xVel;
            if(super.checkBorder()){
                if(xDest + width > Game.WIDTH * xVel){
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

    @Override   
    public void render(Graphics g) {
        if(this.xVel > 0){
            this.dir = 1;
            playerWalk.renderAnimation(g, x, y, width, height);
        }else if(this.xVel < 0){
            this.dir = 0;
            playerWalk.renderAnimation(g, x + width, y, -width, height);

        }
        else{
            if(this.dir == 1){
                g.drawImage(tex.player[0],x,y,width,height,null);
                g.drawRect(x, y, width, height);
            }
            if(this.dir == 0){
                g.drawImage(tex.player[0],x + width,y,-width,height,null);
                g.drawRect(x, y, width, height);
            }
        }
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
}   
