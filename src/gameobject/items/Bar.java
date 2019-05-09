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
 * @author alank
 */
public class Bar extends GameObject{
    Texture tex = Texture.getInstance();
    private Animation Shining;

    public Bar(int x, int y ,ObjectID id) {
        super(x, y,id);
        this.width = 64 * 2;
        this.height = 64 * 2;
        Shining = new Animation(10, tex.barUp[1], tex.barUp[2], tex.barUp[3], tex.barUp[4], tex.barUp[5], tex.barUp[4], tex.barUp[3], tex.barUp[2], tex.barUp[1]);
        this.collisionWidth = 30;
        this.collisionHeight = 70;
    }
    

    @Override //clickMethod
    public void tick() {
        x += xVel;
        this.collisionX = this.x + 45;
        this.collisionY = this.y + 20;
        if(this.getIsCollision()){
            this.setClickable(true);
            System.out.println("tuched");
            Shining.runAnimation();
        }
        
    }

    @Override   
    public void render(Graphics g) {
        if(!this.clicked){
            g.drawImage(tex.barUp[0],x, y, width, height,null);
        }else{
            g.drawImage(tex.barDown[0],x, y, width, height,null);
        }
        
        if(clickable && !this.clicked){
            this.Shining.renderAnimation(g, x, y, width, height);
        }
//        g.drawRect(this.collisionX, this.collisionY, this.collisionWidth, this.collisionHeight);//畫判斷框
    }

    @Override
    public ObjectID getID() {
        return this.id;
    }
}

