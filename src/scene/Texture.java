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
    SpriteSheet npcSheet;
    private BufferedImage player_sheet;
    private BufferedImage npc_Sheet;

    public BufferedImage[] background = new BufferedImage[7];
    public BufferedImage[] player = new BufferedImage[10];
    public BufferedImage[] playParkour;
    public BufferedImage[] npc = new BufferedImage[3];
    public BufferedImage[] paint = new BufferedImage[7];
    public BufferedImage[] ui = new BufferedImage[10];
    public BufferedImage[] cursor = new BufferedImage[2];

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
        npc_Sheet = ImageResource.getInstance().getImage("/Art/Character/Dragon.png");
        npcSheet = new SpriteSheet(npc_Sheet);
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

        npc[0] = npcSheet.grabImage(1,2,80,64);
        npc[1] = npcSheet.grabImage(2,2,80,64);
        npc[2] = npcSheet.grabImage(3,2,80,64);

        background[0] = ImageResource.getInstance().getImage("/Art/Background/MenuEx1.png");
        background[1] = ImageResource.getInstance().getImage("/Art/Background/backGround1.png");
        background[2] = ImageResource.getInstance().getImage("/Art/Background/TestOption.png");
        background[3] = ImageResource.getInstance().getImage("/Art/Background/backGround2.png");
        background[4] = ImageResource.getInstance().getImage("/Art/Background/farMountain.png");
        background[5] = ImageResource.getInstance().getImage("/Art/Background/nearMountains.png");
        background[6] = ImageResource.getInstance().getImage("/Art/Background/road.png");
        
        paint[0] = ImageResource.getInstance().getImage("/Art/Game Material/picture1.png");
        paint[1] = ImageResource.getInstance().getImage("/Art/Game Material/picture2.png");
        paint[2] = ImageResource.getInstance().getImage("/Art/Game Material/picture3.png");
        paint[3] = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
        paint[4] = ImageResource.getInstance().getImage("/Art/Game Material/qr.png");
        paint[5] = ImageResource.getInstance().getImage("/Art/Game Material/jun.jpg");
        paint[6] = ImageResource.getInstance().getImage("/Art/Game Material/picture.png");

        ui[0] = ImageResource.getInstance().getImage("/Art/UI/dialogBubble.png");
    }
    
}

