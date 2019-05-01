/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.items;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import scene.Animation;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class Picture extends GameObject {
    private boolean clickable;
    private Animation Shining;
    private BufferedImage pic = Texture.getInstance().paint[6];
    private double rotateDegrees;
    private double DurationCount;
    
    //旋轉變大的屬性
    private int heightForPicture1 = 80;
    private int widthForPicture1 = 40;
    private int changeValueForHeight = 2;
    private int changeValueForWidth = 1;
    private int frameCountForHeight = 0;
    private int frameCountForWidth = 0;
    

    public Picture(int x,ObjectID id) {
        super(x, 242,id);
        this.width = 128;
        this.height = 178;
        this.clickable =false;
        this.DurationCount = 0;
    }
    
    public void setOnClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
        
        if(this.show){
            
        }
    }

    @Override   //畫框發光
    public void render(Graphics g) {
        if(this.show){
            Graphics2D g2d = (Graphics2D)g;
            if(clickable){
                this.Shining.renderAnimation(g, x, y);
            }else{
                g.drawImage(pic, x, y,width,height, null);
            }
        }
        
    }
    
    public void renderRotate(Graphics g,int MaxFrame){
        if(this.clicked){
            if(heightForPicture1 > changeValueForHeight && frameCountForHeight == MaxFrame){
                changeValueForHeight +=1;
                frameCountForHeight = 4;
            }
            if(widthForPicture1 > changeValueForWidth && frameCountForWidth == MaxFrame){
                changeValueForWidth +=1;
                frameCountForWidth = 0;
            }
            frameCountForHeight++;
            frameCountForWidth++;
            g.drawImage(rotate(Texture.getInstance().paint[6], 30.0), x, y,width - widthForPicture1 + changeValueForWidth,height - heightForPicture1 + changeValueForHeight, null);
        }
        g.drawImage(rotate(Texture.getInstance().paint[6], 30.0,x,y,Color.PINK,true), x, y,width - widthForPicture1 + changeValueForWidth,height - heightForPicture1 + changeValueForHeight, null);
    }
    
    @Override
    public ObjectID getID() {
        return this.id;
    }
    
    public BufferedImage rotate(BufferedImage source, double degrees, int x, int y, Color color, boolean resizable){
        this.rotateDegrees = degrees;
        double radians = Math.toRadians((rotateDegrees-DurationCount % 360.0 + 360.0) % 360.0);
        int width = source.getWidth();
        int height = source.getHeight();
        double rx = x;
        double ry = y;
        int cx = 0;
        int cy = 0;
        if (resizable){
            double dwidth = Math.abs(source.getWidth() * Math.cos(radians)) + Math.abs(source.getHeight() * Math.sin(radians));
            double dheight = Math.abs(source.getWidth() * Math.sin(radians)) + Math.abs(source.getHeight() * Math.cos(radians));
            width = (int)Math.round(dwidth);
            height = (int)Math.round(dheight);
            rx = dwidth / 2;
            ry = dheight / 2;
            cx = (int)Math.round((dwidth - source.getWidth()) / 2);
            cy = (int)Math.round((dheight - source.getHeight()) / 2);
        }
        BufferedImage out = new BufferedImage(width, height, source.getType());
        Graphics2D g2d = out.createGraphics();
        AffineTransform at = (AffineTransform) (g2d.getTransform().clone());
        at.rotate(radians, rx, ry);
        g2d.setTransform(at);
        g2d.drawImage(source, cx, cy, color, null);
        g2d.dispose();
        return out;
    }
 
    public BufferedImage rotate(BufferedImage source, double degrees){
        this.rotateDegrees = degrees;
        if(DurationCount < rotateDegrees){
            DurationCount += 0.0645;
            return rotate(source, degrees, -1, -1, Color.WHITE, true);
        }else{
            this.show = true;
        }
        return source;
    }

}


