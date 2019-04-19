/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;



import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kai
 */
public class BackGround {
    
    private BufferedImage img;
    private int x, y, scrollX;

    
    public BackGround(int index){
        //start position
        this.img = Texture.getInstance().background[index];
    }


    public void tick(){
        x += scrollX;
//        if(x == 5000){
//            scrollX = 0;
//        }
    }

    //paint method
    public void render(Graphics g){
        g.drawImage(img,0,0,1280, 720, x , y , 2142 + x, 1205, null);

        //2141
    }

    public void setScrollX(int scrollX) {
        this.scrollX = scrollX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
