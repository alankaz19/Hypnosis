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

    public BufferedImage[] background = new BufferedImage[7];
    public BufferedImage[] player = new BufferedImage[10];

    //texture loader
    private static Texture tex;

    public static Texture getInstance(){
        if(tex == null){
            tex = new Texture();
        }
        return tex;
    }

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
        
        background[0] = ImageResource.getInstance().getImage("/Art/Background/MenuEx1.png");
        background[1] = ImageResource.getInstance().getImage("/Art/Background/backGround1.png");
        background[2] = ImageResource.getInstance().getImage("/Art/Background/TestOption.png");
        background[3] = ImageResource.getInstance().getImage("/Art/Background/backGround2.png");
        background[4] = ImageResource.getInstance().getImage("/Art/Background/farMountain.png");
        background[5] = ImageResource.getInstance().getImage("/Art/Background/nearMountains.png");
        background[6] = ImageResource.getInstance().getImage("/Art/Background/grassGround.png");
    }
    
}

