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

    public Picture(int x,ObjectID id) {
        super(x, 242,id);
        this.width = 128;
        this.height = 178;
        this.clickable =false;
    }
    
    public void setOnClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
    }

    @Override   //畫框發光
    public void render(Graphics g) {
        BufferedImage pic = Texture.getInstance().paint[6];
        
        if(clickable){
            this.Shining.renderAnimation(g, x, y);
        }else{
            g.drawImage(rotate(pic, 30.0,0,0,Color.PINK,false), x, y,width,height, null);
        }
        
        
        

    }
    
    @Override
    public ObjectID getID() {
        return this.id;
    }
    
    public BufferedImage rotate(BufferedImage source, double degrees, int x, int y, Color color, boolean resizable){
    double radians = Math.toRadians((degrees % 360.0 + 360.0) % 360.0);
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
    return rotate(source, degrees, -1, -1, Color.WHITE, true);
}
    
}


