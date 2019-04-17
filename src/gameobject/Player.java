/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import scene.Texture;
import game.Game;
import resourcemanage.ImageResource;

import java.awt.*;
import java.awt.image.BufferedImage;
import scene.Animation;

/**
 *
 * @author Kai
 */
public class Player extends GameObject {
    private Texture tex = Texture.getInstance();
    private Animation playerWalkRight;
    private Animation playerWalkLeft;

    public Player(int x, int y) {
        super(x, y);
        this.width = 64;
        this.height = 128;
        this.playerWalkRight = new Animation(10, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                                                 tex.player[6], tex.player[7], tex.player[8], tex.player[9]);
        this.playerWalkLeft = new Animation(10, tex.player[11], tex.player[12], tex.player[13], tex.player[14],
                                                tex.player[15],tex.player[16], tex.player[17], tex.player[18], tex.player[19]);
    }


    @Override //move method
    public void tick() {
        x += xVel;
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
    }

    @Override   //testing
    public void render(Graphics g) {
        //BufferedImage test1 = img.getSubimage(0,0,this.width,this.height);
        if(this.xVel > 0){
            playerWalkRight.renderAnimation(g, x, y, 128, 256);

        }else if(this.xVel < 0){
            playerWalkLeft.renderAnimation(g, x, y, 128, 256);
        }
        else{
            g.drawImage(tex.player[0],x,y,128,256,null);
        }
    }
}
