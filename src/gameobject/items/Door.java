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
    private Animation Shining;

    public Door(int x,ObjectID id) {
        super(x, 270,id);
        this.width = 350;
        this.height = 350;
        this.clickable =false;
        this.collisionWidth = 160;
        this.collisionHeight = 315;
        Shining = new Animation(10, tex.door[1], tex.door[2], tex.door[3], tex.door[4], tex.door[5], tex.door[6], tex.door[5], tex.door[4], tex.door[3], tex.door[2], tex.door[1]);

    }
    
    public void setOnClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
        if(this.getIsCollision()){
            this.setClickable(true);
            Shining.runAnimation();
            System.out.println("tuched");
        }else{
            this.setClickable(false);
        }
        this.collisionX = this.x + 100;
        this.collisionY = this.y + 20;
        
    }

    @Override   //門框發光
    public void render(Graphics g) {
        g.drawImage(tex.door[0],x, y, width, height,null);
        if(clickable){
            this.Shining.renderAnimation(g, x, y, width, height);
       }
//        g.drawRect(this.collisionX, this.collisionY, this.collisionWidth, this.collisionHeight);//畫判斷框
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
    
}
