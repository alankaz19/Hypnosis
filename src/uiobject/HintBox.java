/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import game.Game;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class HintBox extends UIObject{
    private Message msg;
    private boolean showed;
    public float alpha;
    private int number;

    public HintBox(int number) {
        msg = new Message();
        this.showed = false;
        this.number = number;
        this.alpha = 0;
    }
    public HintBox(int number,int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        msg = new Message();
        this.showed = false;
        this.number = number;
        this.alpha = 0;
    }

    public void setMsgPosition(int msgX, int msgY) {
        this.msg.x = msgX;
        this.msg.y = msgY;
    }
    
    public void showMsg(int x, int y,int duration , String msg) {
        this.msg.x = x;
        this.msg.y = y;
        this.msg.showMsg(x, y, msg, duration, Color.green);
    }

    public boolean isShowed() {
        return showed;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }
    
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d =(Graphics2D)g;
            if(this.alpha <= 0.99f && this.alpha >= 0){
                this.alpha += 0.02f;
            }
            else if(this.alpha >= 0.9f){
                this.alpha = 1.0f;
            }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        g.drawImage(Texture.getInstance().hint[number], 0, 0,Game.WIDTH,Game.HEIGHT, null);
        this.msg.render(g);
    }
    
    public void fadeIn(Graphics g , float alphaVel) {
        Graphics2D g2d =(Graphics2D)g;
            if(this.alpha <= 0.99f && this.alpha >= 0){
                this.alpha += alphaVel;
            }
            else if(this.alpha >= 0.9f){
                this.alpha = 1.0f;
            }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        g.drawImage(Texture.getInstance().hint[number], x, y, width, height, null);
        this.msg.render(g);
    }
    
    public void fadeOut(Graphics g) {
        Graphics2D g2d =(Graphics2D)g;
            if(this.alpha >= 0.09f && this.alpha <= 1){
                this.alpha -= 0.02f;
            }
            else if(this.alpha <= 0.09f){
                this.alpha = 0f;
            }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        g.drawImage(Texture.getInstance().hint[number], 0, 0,Game.WIDTH,Game.HEIGHT, null);
        this.msg.render(g);
        if( this.alpha == 0f){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
        }
    }
    
    public void fadeOut(Graphics g , float alphaVel) {
        Graphics2D g2d =(Graphics2D)g;
            if(this.alpha >= 0.09f && this.alpha <= 1){
                this.alpha -= alphaVel;
            }
            else if(this.alpha <= 0.09f){
                this.alpha = 0f;
            }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        g.drawImage(Texture.getInstance().hint[number],  x, y, width, height, null);
        this.msg.render(g);
        if( this.alpha == 0.1f){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
        }
    }
    
}
