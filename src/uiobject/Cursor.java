/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import gameobject.GameObject;
import java.awt.Graphics;
import scene.Animation;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class Cursor extends UIObject{
    Texture tex = Texture.getInstance();
    private boolean Clickable;
    private Animation shining;

    public Cursor() {
        this.width = 60;
        this.height = 60;
        this.shining = new Animation(10 ,tex.eyes[0] ,tex.eyes[1] ,tex.eyes[2] ,tex.eyes[3] ,tex.eyes[4] ,tex.eyes[5] ,tex.eyes[4] ,tex.eyes[3] ,tex.eyes[2] ,tex.eyes[1] ,tex.eyes[0]);
    }
    


    public boolean isClickable() {
        return Clickable;
    }

    public void setClickable(boolean Clickable) {
        this.Clickable = Clickable;
    }
    
    public void setPosition(int mouseX, int mouseY) {
        this.x = mouseX;
        this.y = mouseY;
    }

    
    @Override
    public void tick() {
        this.shining.runAnimation();
    }

    @Override
    public void render(Graphics g) {
            this.shining.renderAnimation(g, x, y, width, height);
    }

}
