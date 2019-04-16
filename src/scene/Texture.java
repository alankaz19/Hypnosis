/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import java.awt.image.BufferedImage;
import resourcemanage.ImageResource;

/**
 *
 * @author Kai
 */
public class Texture {
    
    SpriteSheet playerSheet;
    private BufferedImage player_sheet = null;
    
    public BufferedImage[] backgorund = new BufferedImage[1];
    public BufferedImage[] player = new BufferedImage[20];
    

    public Texture() {
    
        player_sheet = ImageResource.getInstance().getImage("/Art/Character/testing2.png");
        
        playerSheet = new SpriteSheet(player_sheet);
        getTextures();
    }
    
    private void getTextures(){
        
        player[0] = playerSheet.grabImage(1, 1, 64, 128);//face right
        player[1] = playerSheet.grabImage(2, 1, 64, 128);
        player[2] = playerSheet.grabImage(3, 1, 64, 128);
        player[3] = playerSheet.grabImage(4, 1, 64, 128);
        player[4] = playerSheet.grabImage(5, 1, 64, 128);
        player[5] = playerSheet.grabImage(6, 1, 64, 128);
        player[6] = playerSheet.grabImage(7, 1, 64, 128);
        player[7] = playerSheet.grabImage(8, 1, 64, 128);
        player[8] = playerSheet.grabImage(9, 1, 64, 128);
        player[9] = playerSheet.grabImage(10, 1, 64, 128);
        player[10] = playerSheet.grabImage(1, 2, 64, 128);//face left
        player[11] = playerSheet.grabImage(2, 2, 64, 128);
        player[12] = playerSheet.grabImage(3, 2, 64, 128);
        player[13] = playerSheet.grabImage(4, 2, 64, 128);
        player[14] = playerSheet.grabImage(5, 2, 64, 128);
        player[15] = playerSheet.grabImage(6, 2, 64, 128);
        player[16] = playerSheet.grabImage(7, 2, 64, 128);
        player[17] = playerSheet.grabImage(8, 2, 64, 128);
        player[18] = playerSheet.grabImage(9, 2, 64, 128);
        player[19] = playerSheet.grabImage(10, 2, 64, 128);
    }
    
}

