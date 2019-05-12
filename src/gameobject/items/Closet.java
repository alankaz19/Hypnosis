/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.items;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import scene.Animation;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class Closet extends GameObject{
    Texture tex = Texture.getInstance();
    private Animation shining;

    public Closet(int x, int y ,ObjectID id) {
        super(x, y,id);
        this.width = 101 * 2;
        this.height = 101 * 2;
        shining = new Animation(10, tex.closet[1], tex.closet[2], tex.closet[3], tex.closet[4], tex.closet[5], tex.closet[4], tex.closet[3], tex.closet[2], tex.closet[1]);
        this.collisionWidth = 180;
        this.collisionHeight = 180;
       
    }
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
        this.collisionX = this.x + 10;
        this.collisionY = this.y + 10;
        if(this.getIsCollision()){
            this.setClickable(true);
            this.shining.runAnimation();
        }else{
            this.setClickable(false);
        }
        
    }

    @Override   
    public void render(Graphics g) {
        g.drawImage(tex.closet[0],x, y, width, height,null);
        if(clickable && !clicked){
            this.shining.renderAnimation(g, x, y, width, height);
        }
//        g.drawRect(this.collisionX, this.collisionY, this.collisionWidth, this.collisionHeight);//畫判斷框
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
}
