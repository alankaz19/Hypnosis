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
public class Light extends GameObject{
    Texture tex = Texture.getInstance();
    private Animation Shining;

    public Light(int x, ObjectID id) {
        super(x,110,id);
        this.width = 200;
        this.height = 200;
        Shining = new Animation(10, tex.light[0], tex.light[1], tex.light[2], tex.light[3], tex.light[2], tex.light[1], tex.light[0]);
    }
    
    public Light(int x,int y, ObjectID id) {
        super(x,y,id);
        this.width = 120;
        this.height = 120;
        Shining = new Animation(10, tex.light[0], tex.light[1], tex.light[2], tex.light[3], tex.light[2], tex.light[1], tex.light[0]);

    }
    
    
    @Override
    public void tick() {
        x += xVel;
        this.Shining.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        this.Shining.renderAnimation(g, x, y, width, height);
    }
    
    public void sceneRender(Graphics g) {
        g.drawImage(tex.light[3], x, y, width, height, null);
    }
    
}
