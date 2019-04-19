/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.items;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Graphics;
import scene.Animation;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class Closet extends GameObject{
    Texture tex = Texture.getInstance();
    private boolean clickable;
    private Animation Shining;

    public Closet(int x, int y,ObjectID id) {
        super(x, y,id);
        this.width = 500;
        this.height = 500;
    }
    
    public void setOnClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    

    @Override //clickMethod
    public void tick() {
    }

    @Override   //畫框發光
    public void render(Graphics g) {
        if(clickable){
            this.Shining.renderAnimation(g, x, y);
        }
        g.drawRect(x, y, width, height);
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
}
