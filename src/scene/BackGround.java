/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;



import resourcemanage.ImageResource;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kai
 */
public class BackGround {
    private BufferedImage img;
    private int x, y, scrollX;

    public BackGround(){
        //start position
        this.x = 0;
        this.y = 0;
        this.scrollX = 3;
        this.img = ImageResource.getInstance().getImage("/Art/BackGround/backGround1.png");
    }


    public void tick(){
        x += scrollX;
    }

    //paint method
    public void render(Graphics g){
        g.drawImage(img,0,0,1280, 720, x , y , 2142 + x, 1205, null);


        //2141
    }

}
