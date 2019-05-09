/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.items;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Graphics;
import resourcemanage.ImageResource;

/**
 *
 * @author alank
 */
public class Sofa extends GameObject{

        public Sofa(int x, int y, ObjectID id) {
            super(x, y, id);
            this.width = 428;
            this.height = 282;
        }
        @Override
        public void tick() {
            x += xVel;
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(ImageResource.getInstance().getImage("/Art/Game Material/sofa.png"), x, y,width,height, null);
        }
        
    }