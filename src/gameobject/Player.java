/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import resourcemanage.ImageResource;
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
    private Animation npcIdle;
    private Animation playerSit;
    private Animation shrinkSit;
    private int gravity;
    private final int MAX = 20;


    public Player(int x, int y,ObjectID id,int movementSpeed) {
        super(x, y,id);
        this.width = 256+24;
        this.height = 256+24;
        this.dir = 1;
        this.playerWalk = new Animation(movementSpeed / 2, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                                        tex.player[6],tex.player[7],tex.player[8],tex.player[9]);
        this.playerIdle = new Animation(15, tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14],
                                        tex.player[15],tex.player[16],tex.player[17],tex.player[18],tex.player[19],tex.player[20],tex.player[21],tex.player[22],tex.player[23],tex.player[24],tex.player[25]);
        this.npcIdle = new Animation(15, tex.npc[4], tex.npc[5], tex.npc[6], tex.npc[7], tex.npc[8],
                                        tex.npc[9],tex.npc[10],tex.npc[11],tex.npc[12],tex.npc[13],tex.npc[14],tex.npc[15],tex.npc[16],tex.npc[17],tex.npc[18],tex.npc[19]);
        this.playerSit = new Animation(10,tex.player[40]);
        this.shrinkSit = new Animation(10, ImageResource.getInstance().getImage("/Art/Character/doc.png"));
        gravity = 1;
        this.collisionWidth = 110;
        this.collisionHeight = 260;
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
        if(this.id == ObjectID.SHRINK){
            shrinkSit.runAnimation();
        }
        if(this.id == ObjectID.ENDGAMEPLAYER){
            playerIdle.runAnimation();
        }

        if(this.id == ObjectID.ENDGAMENPC){
            npcIdle.runAnimation();
        }
        this.collisionX = this.x + 85;
        this.collisionY = this.y + 25;
//        playerWalk.runAnimation();
//        playerIdle.runAnimation();
    }

    @Override   
    public void render(Graphics g) {
        if(this.id == ObjectID.SHRINK){
            shrinkSit.renderAnimation(g,x,y,width,height);
        }
        if(this.id == ObjectID.ENDGAMENPC) {
            npcIdle.renderAnimation(g, x, y, 224, 224);
        }
        if(this.id == ObjectID.ENDGAMEPLAYER) {
            playerIdle.renderAnimation(g, x + 224 , y, - 224, 224);
        }
        else if(this.id == ObjectID.INTROPLAYER){
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
