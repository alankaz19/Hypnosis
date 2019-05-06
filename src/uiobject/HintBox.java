/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class HintBox extends UIObject{
    private Message msg;
    private BufferedImage img;
    public boolean showed;
    public float alpha;

    public HintBox() {
        this.showed = false;
        this.alpha = 0;
    }
    
    
    public void setMsgPosition(int msgX, int msgY) {
        this.msg.x = msgX;
        this.msg.y = msgY;
    }
    
    public void showMsg(int x, int y,int duration , String msg) {
        this.msg.showMsg(x, y, msg, duration, Color.black);
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d =(Graphics2D)g;
            if(this.alpha <= 0.99f && this.alpha >= 0){
            this.alpha += 0.09f;
            }
            else if(this.alpha >= 0.9f){
                this.alpha = 1.0f;
            }
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        this.msg.render(g);
        g.drawImage(Texture.getInstance().ui[2], 0, 0, null);
    }
}
