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
import scene.AudioManager;
import uiobject.Message;

/**
 *
 * @author Kai
 */
public class Npc extends GameObject {
    private Texture tex = Texture.getInstance();
    private Animation npcFloat;
    private Animation npcDown;
    private ActionPlayer player;
    private Message msg;
    private BufferedImage chatBubble;
    private int speed;
    private int maxSpeed;
    private int diveCoolDown;
    private boolean diving;
    private int gravity, lifeC, lineC;
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
        this.diveCoolDown = 300;
        this.gravity = 1;
        this.falling = false;
        this.isCollision = false;
        //對話相關
        this.chatBubble = Texture.getInstance().ui[3];
        this.msg = new Message();
        this.msg.setFont(Fonts.getBitFont(17));
        this.msg.showMsg(x, y, "妳是來趕我走的嗎?", 800,  Color.red);
        this.lineC = 0;
        //
        heart = new ArrayList<>();
        for(int i = 0; i < getLifeC()*50; i+= 50){
            heart.add(new Heart((Game.WIDTH/2 + 370) + i,10,ObjectID.HEART));
        }
    }
    private class Heart extends GameObject{
        private BufferedImage img;
        public Heart(int x, int y, ObjectID id) {
            super(x, y, id);
            this.img = ImageResource.getInstance().getImage("/Art/Game Material/heart.png");
            width = 50;
            height = 50;
        }
        @Override
        public void tick() {

        }
        @Override
        public void render(Graphics g) {
            g.drawImage(img,x,y,width,height,null);
        }
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;
        if(!npcExhausted()){
            if(y < player.getY()){
                y += 2;
            }
            if (y > player.getY()){
                y -= 2;
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
//        msg.setPosition(x, y);
        if(this.dir == 1){
            msg.shake(x - 20, y - 80);
        }
        if(this.dir == 0){
            msg.shake(x + 20, y - 80);
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
                lineC++;
                if(lineC % 2 == 1){
                    msg.showMsg(x, y, "別再欺騙自己!", 300, Color.red);
                    AudioManager.getInstance().getPlayList()[AudioManager.ATTACK].play();
                }else{
                    msg.showMsg(x, y, "妳其實非常脆弱!", 300, Color.red);
                    AudioManager.getInstance().getPlayList()[AudioManager.ATTACK].play();
                    lineC = 0;
                }

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
                if(lineC % 2 == 1){
                    msg.showMsg(x , y, "妳永遠都別想出去!", 300, Color.red);
                    AudioManager.getInstance().getPlayList()[AudioManager.ATTACK].play();
                }else{
                    msg.showMsg(x , y, "不要搶走我的東西!", 300, Color.red);
                    AudioManager.getInstance().getPlayList()[AudioManager.ATTACK].play();
                    lineC = 0;
                }
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

    public Rectangle getBot(){
        return new Rectangle(x+width/4 + 20,y + height/2,width/2 - 30,height/2 - 15);
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
//        renderMsg(g);
        if(!npcExhausted()){
            if(this.dir == 1){
                g.drawImage(chatBubble, x - 50, y -70,175, 70, null);
            }
            if(this.dir == 0){
                g.drawImage(chatBubble, x , y -70,175, 70, null);
            }
        }


        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        if(this.alpha <= 0.99f && this.alpha >= 0){
        this.alpha += 0.005f;
        }
        else if(this.alpha >= 0.9f){
            this.alpha = 1.0f;
        }
        msg.render(g);
    }

    public ArrayList<Heart> getHeart() {
        return heart;
    }

    @Override
    public ObjectID getID() {
        return null;
    }

    public Message getMsg() {
        return msg;
    }
}
