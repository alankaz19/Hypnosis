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

    public Message() {
        this.alpha = 0;
        
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
    }
    
    private void drawString(Graphics g, String text, int x, int y) {
        int bubbleWidth = 11;
        int count = 1;
        for (int i = 0; i < i + bubbleWidth; i += bubbleWidth){
            if(text.length() - i > bubbleWidth){
                String line = text.substring(i, bubbleWidth * count++);
                g.drawString(line, x, y += g.getFontMetrics().getHeight());
            }
            else if(text.length() <= bubbleWidth){
                g.drawString(text, x, y += g.getFontMetrics().getHeight() * 2);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        if(msgFrameCount < msgFrame){
            Font font = this.font;
            g.setFont(font);
            g.setColor(color);
            FontMetrics fm = g.getFontMetrics();
            int sa = fm.getAscent();
            Graphics2D g2d =(Graphics2D)g;
            if(this.alpha <= 0.99f && this.alpha >= 0){
            this.alpha += 0.09f;
            }
            else if(this.alpha >= 0.9f){
                this.alpha = 1.0f;
            }
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            drawString(g, msg, x,  y);
            msgFrameCount++;
            if(msgFrameCount == msgFrame){
               if(this.alpha > 0.99f && this.alpha >= 0){
                this.alpha -= 0.09f;
                }
                else if(this.alpha <= 0.09f){
                    this.alpha = 0;
                }
            }
        }
    }
    
    public void setFont(Font font){
        this.font = font;
    }
    
    public boolean isShow() {
        return show;
    }
    
    public void shake(){
        this.x =  xtemp + (int)(Math.random()* 5) - 5 ;
        this.y =  ytemp + (int)(Math.random()* 5) - 5 ;
    }
}
