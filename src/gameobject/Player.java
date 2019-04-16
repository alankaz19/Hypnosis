/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import resourcemanage.ImageResource;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kai
 */
public class Player extends GameObject {
    BufferedImage  img;
    BufferedImage animation [];

    public Player(int x, int y) {
        super(x, y);
        this.img = ImageResource.getInstance().getImage("/Art/Character/testing2.png");
        this.width = 64;
        this.height = 128;

    }


    @Override //move method
    public void tick() {
        x += xVel;


    }

    @Override   //testing
    public void render(Graphics g) {
        //BufferedImage test1 = img.getSubimage(0,0,this.width,this.height);
        g.drawImage(img,x,y,x + this.width+50,y + this.height+140,0,0,this.width,this.height,null);

    }
}
