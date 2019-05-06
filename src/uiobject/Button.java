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
    
    public Button(int x,int y,BufferedImage img1, BufferedImage img2) {
        this.x = x;
        this.y = y;
        this.imgs[0] = img1;
        this.imgs[1] = img2;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
