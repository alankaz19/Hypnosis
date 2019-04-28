/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import gameobject.GameObject;
import java.awt.Graphics;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class Cursor extends UIObject{
    private int mouseX;
    private int mouseY;
    private boolean Checkable;
    private boolean Clickable;

    public Cursor() {
        this.width = 10;
        this.height = 10;
    }
    
    
    
    private void setPoint() {
        this.x = mouseX;
        this.y = mouseY;
    }

    public boolean isCheckable() {
        return Checkable;
    }

    public boolean isClickable() {
        return Clickable;
    }

    public void setCheckable(boolean Checkable) {
        this.Checkable = Checkable;
    }

    public void setClickable(boolean Clickable) {
        this.Clickable = Clickable;
    }
    
    public void setMouse(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    
    @Override
    public void tick() {
        setPoint();
    }

    @Override
    public void render(Graphics g) {
        if(Checkable){
            g.drawImage(Texture.getInstance().cursor[0], this.x, this.y, null);
        }
        if(Clickable){
            g.drawImage(Texture.getInstance().cursor[1], this.x, this.y, null);
        }
    }

}
