/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author Kai
 */
public class Message extends UIObject{
    boolean show;
    private String msg;
    private Color color;
    private int msgFrame;
    private int msgFrameCount;
    private float alpha;
    private int xtemp;
    private int ytemp;
    private Font font;
    private int bubbleWidth = 11;

    public Message() {
        
    }
    
    public void showMsg(int x,int y,String msg,int Duration,Color color){
        this.x = x;
        this.y = y;
        xtemp = x;
        ytemp = y;
        this.msg = msg;
        this.color = color;
        this.msgFrame = Duration;
        this.msgFrameCount = 0;
        this.show = true;
        this.alpha = 0;
    }
    
    private void drawString(Graphics g, String text, int x, int y) {
        int bubbleWidth = this.bubbleWidth;
        int count = 1;
        for (int i = 0; i < i + bubbleWidth; i += bubbleWidth){
            if(text.length() - i > bubbleWidth){
                String line = text.substring(i, bubbleWidth * count++);
                g.drawString(line, x, y += g.getFontMetrics().getHeight());
            }
            else if(text.length() <= bubbleWidth){
                g.drawString(text, x, y += 45);
                break;
            }
            else if(text.length() - i <= bubbleWidth){
                String line = text.substring(i);
                g.drawString(line, x, y += g.getFontMetrics().getHeight());
                break;
            }
        }
    }

    @Override
    public void tick() {
        
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        if(msgFrameCount < msgFrame){
            Font font = this.font;
            g.setFont(font);
            g.setColor(color);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            drawString(g, msg, x,  y);
            msgFrameCount++;
        }
    }
    
    public void fadeIn(Graphics g) {
        if(this.alpha <= 0.99f && this.alpha >= 0){
        this.alpha += 0.005f;
        }
        else if(this.alpha >= 0.9f){
            this.alpha = 1.0f;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        
    }
    
    public void fadeOut(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        if(this.alpha > 0.99f && this.alpha >= 0){
            this.alpha -= 0.01f;
        }else if(this.alpha <= 0.09f){
            this.alpha = 0;
        }
    }
    
    public void setFont(Font font){
        this.font = font;
    }
    
    public boolean isShow() {
        return show;
    }
    
    public void setLongWord(){
        this.bubbleWidth = 100;
    }
    
    public void shake(){
        this.x =  xtemp + (int)(Math.random()* 5) - 5 ;
        this.y =  ytemp + (int)(Math.random()* 5) - 5 ;
    }
    public void shake(int x, int y){
        this.x =  x +  (int)(Math.random()* 5) - 5 ;
        this.y =  y +  (int)(Math.random()* 5) - 5 ;
    }
}
