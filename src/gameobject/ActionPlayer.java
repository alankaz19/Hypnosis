package gameobject;

import game.Game;
import resourcemanage.ImageResource;
import scene.Animation;
import scene.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import scene.AudioManager;

public class ActionPlayer extends GameObject {
    private Texture tex = Texture.getInstance();
    private Animation playerWalk;
    private Animation playerIdle;
    private Animation playerJump;
    private Animation playerDead;
    private Animation playerStayDead;
    private boolean dead;
    private int gravity, lifeC;
    private final int MAX = 20;


    public int getLifeC() {
        return lifeC;
    }

    public void setLifeC(int lifeC) {
        this.lifeC = lifeC;
    }

    public boolean isDead() {
        return dead;
    }

    public ActionPlayer(int x, int y, ObjectID id, int movementSpeed) {
        super(x, y,id);
        this.width = 256;
        this.height = 256;
        this.dir = 1;
        this.playerWalk = new Animation(movementSpeed, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                tex.player[6],tex.player[7],tex.player[8],tex.player[9]);
        this.playerIdle = new Animation(10, tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14],
                tex.player[15],tex.player[16],tex.player[17],tex.player[18],tex.player[19],tex.player[20],tex.player[21],tex.player[22],tex.player[23],tex.player[24],tex.player[25]);
        this.playerJump = new Animation(movementSpeed,  tex.player[28]);
        this.playerDead = new Animation(movementSpeed,tex.player[30],tex.player[31],tex.player[32],tex.player[33],tex.player[34],tex.player[35],tex.player[36],tex.player[37],tex.player[38]);
        this.playerStayDead = new Animation(movementSpeed,tex.player[38]);
        gravity = 1;
        lifeC = 5;
    }

    public boolean playerDead(){
        if(lifeC <= 0){
            xVel = 0;
            lifeC = 0;
            return true;
        }
        return false;
    }


    @Override
    public boolean checkBorder() {
        this.xDest = x + xVel;
        this.yDest = y + yVel;

        if(super.checkBorder()){
            if(xDest - 45 + width > Game.WIDTH ){
                this.xVel = 0;
            }else if(xDest + 50 < 0){
                this.xVel = 0;
            }else if(yDest < 0){
                this.yVel = 0;
            }
        }
        return true;
    }

    @Override
    public void tick() {
        if(this.getID() != ObjectID.ENDSCENE)
        checkBorder();
        x += xVel;
        y += yVel;

        if(falling || jumping){
            yVel += gravity;

            if(yVel > MAX){
                yVel = MAX;
            }
        }
        if(!playerDead()){
            playerWalk.runAnimation();
            playerIdle.runAnimation();
            playerJump.runAnimation();
        }else if(playerDead() && !dead){
            playerDead.runAnimation();
        }else if(playerDead.hasPlayedOnce()){
            playerStayDead.runAnimation();
        }

    }

    @Override
    public void render(Graphics g) {
        if(!playerDead()) {
            if (this.xVel > 0) {
                if (falling && jumping) {
                    this.dir = 1;
                    playerJump.renderAnimation(g, x + width, y, -this.width, this.height);
                    
                } else {
                    this.dir = 1;
                    playerWalk.renderAnimation(g, x + width, y, -width, height);
                }
            } else if (this.xVel < 0) {
                if (falling && jumping) {
                    this.dir = 0;
                    playerJump.renderAnimation(g, x, y, this.width, this.height);
                } else {
                    this.dir = 0;
                    playerWalk.renderAnimation(g, x, y, width, height);
                }
            } else {
                if (this.dir == 1) {
                    playerIdle.renderAnimation(g, x + width, y, -width, height);
                }
                if (this.dir == 0) {
                    playerIdle.renderAnimation(g, x, y, width, height);
                }
            }
        }else if(playerDead() && !dead){
            if(this.dir == 1){
                playerDead.renderAnimation(g,x+width,y,-width,height);
            }
            if(this.dir == 0){
                playerDead.renderAnimation(g,x,y,width,height);
            }
        }
        if(playerDead.hasPlayedOnce()){
            dead = true;
            if(this.dir == 1){
                playerStayDead.renderAnimation(g,x+width,y,-width,height);
            }
            if(this.dir == 0){
                playerStayDead.renderAnimation(g,x,y,width,height);
            }
        }
    }
    public Rectangle getBot(){
        return new Rectangle(x+width/4 + 20,y + height/2,width/2 - 30,height/2);
    }
    public Rectangle getTop(){
        return new Rectangle(x+width/4 + 20,y+10,width/2 -30,height/2);
    }
    public Rectangle getRight(){
        return new Rectangle(x + width - 42 ,y + 5,5,height - 10);
    }
    public Rectangle getLeft(){
        return new Rectangle(x + 28,y + 5,5,height - 10);
    }
    public Rectangle getBound(){
        return new Rectangle(x+34,y+15,width-72,height-15);
    }
    public boolean checkFloor(GameObject o ){
        if(this.getTop().intersects(o.getBound())){
            return true;
        }
        if(this.getBot().intersects(o.getBound())){
            return true;
        }
        return false;
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
}
