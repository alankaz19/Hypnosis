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
import java.awt.image.BufferedImage;
import scene.Animation;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class Picture extends GameObject {
    Texture tex = Texture.getInstance();
    private Animation Shining;
    private BufferedImage picture;
    private double rotateDegrees;
    private double DurationCount;
    private int number;
    
    //旋轉變大的屬性
    private int heightForPicture1 = 50;
    private int widthForPicture1 = 50;
    private int changeValueForHeight = 1;
    private int changeValueForWidth = 1;
    private int frameCountForHeight = 0;
    private int frameCountForWidth = 0;
    

    public Picture(int x,int number,ObjectID id) {
        super(x, 222,id);
        this.width = 220;
        this.height = 220;
        this.clickable =false;
        this.DurationCount = 0;
        this.collisionWidth = 140;
        this.collisionHeight = 190;
        this.picture = Texture.getInstance().paintThumbnail[number];
        this.Shining = new Animation(10, tex.pictureFrame[1], tex.pictureFrame[2], tex.pictureFrame[3], tex.pictureFrame[4], tex.pictureFrame[5], tex.pictureFrame[6], tex.pictureFrame[5], tex.pictureFrame[4], tex.pictureFrame[3], tex.pictureFrame[2], tex.pictureFrame[1]);

    }
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
        
        if(this.getIsCollision()){
            this.setClickable(true);
            Shining.runAnimation();
        }else{
            this.setClickable(false);
        }
        this.collisionX = this.x + 40;
        this.collisionY = this.y + 17;
    }

    @Override   //畫框發光
    public void render(Graphics g) {
        if(this.show){
            if(clickable){
                g.drawImage(picture, x, y,width,height, null);
                this.Shining.renderAnimation(g, x, y, width,height);
            }else{
                g.drawImage(picture, x, y,width,height, null);
            }
//            g.drawRect(this.collisionX, this.collisionY, this.collisionWidth, this.collisionHeight);//畫判斷框
        }
        
    }
    
    public void renderRotate(Graphics g,int MaxFrame){
        if(this.clicked){
            if(heightForPicture1 > changeValueForHeight && frameCountForHeight == MaxFrame){
                changeValueForHeight +=2;
                frameCountForHeight = 5;
            }
            if(widthForPicture1 > changeValueForWidth && frameCountForWidth == MaxFrame){
                changeValueForWidth +=3;
                frameCountForWidth = 0;
            }
            frameCountForHeight++;
            frameCountForWidth++;
            g.drawImage(rotate(Texture.getInstance().paintThumbnail[number], 30.0), x , y, width - widthForPicture1 + changeValueForWidth ,height - heightForPicture1 + changeValueForHeight, null);
        }
            g.drawImage(rotate(Texture.getInstance().paintThumbnail[number], 30.0,x,y,null,true), x, y, width - widthForPicture1 + changeValueForWidth ,height - heightForPicture1 + changeValueForHeight, null);
    }
    
    public void setState(int pictureNumber){
        this.picture = Texture.getInstance().paintThumbnail[pictureNumber];
    }
    
    @Override
    public ObjectID getID() {
        return this.id;
    }

    public Animation getShining() {
        return Shining;
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
            DurationCount += 0.20;
            return rotate(source, degrees, -1, -1, null, true);
        }else{
            this.show = true;
        }
        return source;
    }

}


