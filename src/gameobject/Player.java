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
    private Texture tex = Texture.getInstance();
    private Animation playerWalk;
    private Animation playerIdle;
    private Animation playerSit;
    private int gravity;
    private final int MAX = 20;


    public Player(int x, int y,ObjectID id,int movementSpeed) {
        super(x, y,id);
        this.width = 256;
        this.height = 256;
        this.dir = 1;
        this.playerWalk = new Animation(movementSpeed, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                tex.player[6],tex.player[7],tex.player[8],tex.player[9]);
        this.playerIdle = new Animation(18, tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14],
                tex.player[15],tex.player[16],tex.player[17],tex.player[18],tex.player[19],tex.player[20],tex.player[21],tex.player[22],tex.player[23],tex.player[24],tex.player[25]);
        this.playerSit = new Animation(10,tex.player[40]);
        gravity = 1;
    }


    @Override
    public boolean checkBorder() {
        this.xDest = x + xVel;
        this.yDest = y + yVel;

            if(super.checkBorder()){
                if(xDest + width > Game.WIDTH ){
                    this.xVel = 0;
                }else if(xDest < 0){
                    this.xVel = 0;
                }else if(yDest < 0){
                    this.yVel = 0;
                }
            }
        return true;
    }

    @Override
    public void tick() {
       checkBorder();
       x += xVel;
       y += yVel;
       if(this.id == ObjectID.PLAYER){
           if(falling || jumping){
               yVel += gravity;

               if(yVel > MAX){
                   yVel = MAX;
               }
           }
           playerWalk.runAnimation();
           playerIdle.runAnimation();
       }
       if(this.id == ObjectID.INTROPLAYER){
           playerSit.runAnimation();
       }
    }

    @Override   
    public void render(Graphics g) {
        if(this.id == ObjectID.INTROPLAYER){
            if(this.dir == 1){
                playerSit.renderAnimation(g,x + width, y, -width, height);
            }
            if(this.dir == 0){
                playerSit.renderAnimation(g, x , y, width, height);
            }
        }else if(this.xVel > 0){
            this.dir = 1;
            playerWalk.renderAnimation(g, x + width, y, -width, height);
        }else if(this.xVel < 0){
            this.dir = 0;
            playerWalk.renderAnimation(g, x , y, width, height);
        }
        else{
            if(this.dir == 1){
                playerIdle.renderAnimation(g,x + width, y, -width, height);
                //g.drawRect(x, y, width, height);//畫判斷框
            }
            if(this.dir == 0){
                playerIdle.renderAnimation(g, x , y, width, height);
                //g.drawRect(x, y, width, height);//畫判斷框
            }
        }
    }
    public Rectangle getBot(){
        return new Rectangle(x+width/4,y + height/2,width/2,height/2);
    }
    public Rectangle getTop(){
        return new Rectangle(x+width/4,y,width/2,height/2);
    }
    public Rectangle getRight(){
        return new Rectangle(x + width - 15,y + 5,5,height - 10);
    }
    public Rectangle getLeft(){
        return new Rectangle(x + 10,y + 5,5,height - 10);
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }

}
