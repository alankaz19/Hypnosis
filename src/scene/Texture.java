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

    public BufferedImage[] background = new BufferedImage[10];
    public BufferedImage[] player = new BufferedImage[30];
    public BufferedImage[] playParkour;
    public BufferedImage[] npc = new BufferedImage[3];
    public BufferedImage[] paint = new BufferedImage[7];
    public BufferedImage[] ui = new BufferedImage[10];
    public BufferedImage[] cursor = new BufferedImage[2];
    public BufferedImage[] floor = new BufferedImage[2];

    //texture loader
    private static Texture tex;

    public static Texture getInstance(){
        if(tex == null){
            tex = new Texture();
        }
        return tex;
    }

    public Texture() {
        player_sheet = ImageResource.getInstance().getImage("/Art/Character/player.png");
        playerSheet = new SpriteSheet(player_sheet);
        npc_Sheet = ImageResource.getInstance().getImage("/Art/Character/Dragon.png");
        npcSheet = new SpriteSheet(npc_Sheet);
        getTextures();
    }
    
    private void getTextures(){
        
        player[0] = playerSheet.grabImage(1, 1, 48, 48);//face right
        player[1] = playerSheet.grabImage(17, 1, 48, 48);//walk
        player[2] = playerSheet.grabImage(18, 1, 48, 48);
        player[3] = playerSheet.grabImage(19, 1, 48, 48);
        player[4] = playerSheet.grabImage(20, 1, 48, 48);
        player[5] = playerSheet.grabImage(21, 1, 48, 48);
        player[6] = playerSheet.grabImage(1, 2, 48, 48);
        player[7] = playerSheet.grabImage(2, 2, 48, 48);
        player[8] = playerSheet.grabImage(3, 2, 48, 48);
        player[9] = playerSheet.grabImage(4, 2, 48, 48);
        player[10] = playerSheet.grabImage(1, 1, 48, 48);//Idle
        player[11] = playerSheet.grabImage(2, 1, 48, 48);
        player[12] = playerSheet.grabImage(3, 1, 48, 48);
        player[13] = playerSheet.grabImage(4, 1, 48, 48);
        player[14] = playerSheet.grabImage(5, 1, 48, 48);
        player[15] = playerSheet.grabImage(6, 1, 48, 48);
        player[16] = playerSheet.grabImage(7, 1, 48, 48);
        player[17] = playerSheet.grabImage(8, 1, 48, 48);
        player[18] = playerSheet.grabImage(9, 1, 48, 48);
        player[19] = playerSheet.grabImage(10, 1, 48, 48);
        player[20] = playerSheet.grabImage(11, 1, 48, 48);
        player[21] = playerSheet.grabImage(12, 1, 48, 48);
        player[22] = playerSheet.grabImage(13, 1, 48, 48);
        player[23] = playerSheet.grabImage(14, 1, 48, 48);
        player[24] = playerSheet.grabImage(15, 1, 48, 48);
        player[25] = playerSheet.grabImage(16, 1, 48, 48);
        player[26] = playerSheet.grabImage(2, 6, 48, 48);//Jump
        player[27] = playerSheet.grabImage(3, 6, 48, 48);
        player[28] = playerSheet.grabImage(4, 6, 48, 48);
        player[29] = playerSheet.grabImage(5, 6, 48, 48);

        npc[0] = playerSheet.grabImage(7, 6, 48, 48);
        npc[1] = playerSheet.grabImage(8, 6, 48, 48);
        npc[2] = playerSheet.grabImage(9, 6, 48, 48);

        background[0] = ImageResource.getInstance().getImage("/Art/Background/MenuEx1.png");
        background[1] = ImageResource.getInstance().getImage("/Art/Background/backGround1.png");
        background[2] = ImageResource.getInstance().getImage("/Art/Background/TestOption.png");
        background[3] = ImageResource.getInstance().getImage("/Art/Background/backGround2.png");
        background[4] = ImageResource.getInstance().getImage("/Art/Background/farMountain.png");
        background[5] = ImageResource.getInstance().getImage("/Art/Background/nearMountains.png");
        background[6] = ImageResource.getInstance().getImage("/Art/Background/road.png");
        background[7] = ImageResource.getInstance().getImage("/Art/Background/fakeBackground.png");
        background[8] = ImageResource.getInstance().getImage("/Art/Background/magicBackground.png");
        background[9] = ImageResource.getInstance().getImage("/Art/Background/room.png");
        
        paint[0] = ImageResource.getInstance().getImage("/Art/Game Material/picture1.png");
        paint[1] = ImageResource.getInstance().getImage("/Art/Game Material/picture2.png");
        paint[2] = ImageResource.getInstance().getImage("/Art/Game Material/picture3.png");
        paint[3] = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
        paint[4] = ImageResource.getInstance().getImage("/Art/Game Material/qr.png");
        paint[5] = ImageResource.getInstance().getImage("/Art/Game Material/frame.png");
        paint[6] = ImageResource.getInstance().getImage("/Art/Game Material/picture.png");

        ui[0] = ImageResource.getInstance().getImage("/Art/UI/dialogBubble.png");
        ui[1] = ImageResource.getInstance().getImage("/Art/Game Material/Exit.png");
        ui[2] = ImageResource.getInstance().getImage("/Art/UI/dialogBox.png");

        floor[0] = ImageResource.getInstance().getImage("/Art/Game Material/floor.png");

    }
    
}

