/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kai
 */
public class Button extends UIObject{
    private BufferedImage[] imgs;
    private boolean released;
    private boolean touchable;
    
    
    private int scale;
    private int frame;
    
    public Button(int x,int y, int width, int height, BufferedImage img1, BufferedImage img2,int scale) {
        this.x = x;
        this.y = y;
        this.width =width;
        this.height = height;
        this.imgs = new BufferedImage[2];
        this.imgs[0] = img1;
        this.imgs[1] = img2;
        this.scale = scale;
        released = true;
        frame =0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setTouchable(boolean touchable) {
        this.touchable = touchable;
    }

    public boolean isTouchable() {
        return touchable;
    }
    
    
    @Override
    public void setClicked(boolean clicked) {
        this.Clicked = clicked;
    }

    @Override
    public boolean isClicked() {
        return this.Clicked;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public boolean isReleased() {
        return released;
    }
    
    public void tick() {
        if(this.touchable){
            if(this.released){
                this.Clicked = false;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(this.Clicked){
            frame++;
            if(frame < 10){
                g.drawImage(imgs[0], x, y, this.width * scale, this.height * scale, null);
                frame = 0;    
            }
        }
        if(this.released){
            g.drawImage(imgs[1], x, y, this.width * scale, this.height * scale, null);
        }
        
        if(this.Hovered){
            g.drawImage(imgs[0], x, y, this.width * scale, this.height * scale, null);
        }else{
            g.drawImage(imgs[1], x, y, this.width * scale, this.height * scale, null);
        }
    }
}
    