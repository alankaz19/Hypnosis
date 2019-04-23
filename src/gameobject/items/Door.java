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
public class Door extends GameObject{
    Texture tex = Texture.getInstance();
    private boolean clickable;
    private Animation Shining;

    public Door(int x,ObjectID id) {
        super(x, 312,id);
        this.width = 200;
        this.height = 300;
        this.clickable =false;
    }
    
    public void setOnClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
    }

    @Override   //門框發光
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
