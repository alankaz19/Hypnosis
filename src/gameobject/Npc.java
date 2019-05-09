/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import game.Game;
import resourcemanage.ImageResource;
import scene.Animation;
import scene.Texture;
import uiobject.Fonts;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Kai
 */
public class Npc extends GameObject {
    private Texture tex = Texture.getInstance();
    private Animation npcFloat;
    private Animation npcDown;
    private ActionPlayer player;
    private int speed;
    private int maxSpeed;
    private int diveCoolDown;
    private boolean diving;
    private int gravity, lifeC;
    private ArrayList<Heart> heart;

    public Npc(int x, int y, ObjectID id, int movement, ActionPlayer player) {
        super(x, y, id);
        this.width = 128;
        this.height = 128;
        this.dir = 0;
        this.player = player;
        this.speed = 3; this.maxSpeed = 13;
        this.npcFloat = new Animation(movement, tex.npc[0], tex.npc[1], tex.npc[2]);
        this.npcDown = new Animation(movement, tex.npc[3]);
        this.diving = true;
        this.lifeC = 5;
        this.diveCoolDown = 600;
        this.gravity = 1;
        this.falling = false;
        this.isCollision = false;
        showMsg("Start Message", 650, Color.red, 0, Fonts.getHorrorFont(30));
        heart = new ArrayList<>();
        for(int i = 0; i < getLifeC()*40; i+= 40){
            heart.add(new Heart((Game.WIDTH/2 + 420) + i,20,ObjectID.HEART));
        }
    }
    private class Heart extends GameObject{
        private BufferedImage img;
        public Heart(int x, int y, ObjectID id) {
            super(x, y, id);
            this.img = ImageResource.getInstance().getImage("/Art/Game Material/heart.png");
            width = 40;
            height = 40;
        }
        @Override
        public void tick() {

        }
        @Override
        public void render(Graphics g) {
            g.drawImage(img,x,y,40,40,null);
        }
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;
        if(!npcExhausted()){
            if(y < player.getY()){
                y += 3;
            }
            if (y > player.getY()){
                y -= 3;
            }
            npcFloat.runAnimation();
        }else{
            xVel = 0;
            yVel += gravity;
            if(yVel > maxSpeed){
                yVel = maxSpeed;
            }
            npcDown.runAnimation();
        }
        for (Heart heart : heart) {
            heart.tick();
        }
    }
    public boolean checkBorder() {
        this.xDest = x + xVel;
        this.yDest = y + yVel;

        if(super.checkBorder()){
            if(xDest + width > Game.WIDTH ){
                this.xVel = 0;
                return true;
            }else if(xDest < 0){
                this.xVel = 0;
                return true;
            }else if(yDest < 0){
                this.yVel = 0;
                return true;
            }
        }
        return false;
    }

    public int getLifeC() {
        return lifeC;
    }

    public boolean npcExhausted(){
        if(heart.isEmpty()){
            falling = true;
            isCollision = true;
            return true;
        }
        return false;
    }

    public void dive(){
        if(this.x < Game.WIDTH/2 && !diving){
            xVel += speed;
            if(xVel >= maxSpeed){
                xVel = maxSpeed;
                diving = true;
                heart.remove(heart.size()-1);
            }
        }
        else if(x + width >= Game.WIDTH - 20){
            diveCoolDown --;
            if(diveCoolDown == 0){
                diving = false;
                diveCoolDown = 100;
            }
        }
        if(this.x > Game.WIDTH/2 && !diving){
            xVel -= speed;
            if( xVel <= -maxSpeed){
                xVel = -maxSpeed;
                diving = true;
                heart.remove(heart.size()-1);
            }
        }
        else if(x <= 20){
            diveCoolDown --;
            if(diveCoolDown == 0){
                diving = false;
                diveCoolDown = 100;
            }
        }

    }

    @Override
    public Rectangle getBound(){
        return new Rectangle(x+29,y+10,width-72,height-25);
    }

    @Override
    public void render(Graphics g) {
        if(!npcExhausted()){
            if(this.xVel < 0){
                this.dir = 0;
                npcFloat.renderAnimation(g, x, y, width, height);
            }
            else if(this.xVel > 0){
                this.dir = 1;
                npcFloat.renderAnimation(g, x + width, y, -width, height);
            }
            else{
                if(this.dir == 1){
                    npcFloat.renderAnimation(g, x, y, width, height);
                    //g.drawRect(x, y, width, height);//畫判斷框
                }
                if(this.dir == 0){
                    npcFloat.renderAnimation(g, x + width, y, -width, height);
                    //g.drawRect(x, y, width, height);//畫判斷框
                }
            }
        }else{
            npcDown.renderAnimation(g,x,y-15,width,height);
        }
        for(Heart heart : heart){
            heart.render(g);
        }
        renderMsg(g);

    }
    @Override
    public ObjectID getID() {
        return null;
    }
}
