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
    SpriteSheet arrowButtonSheet;
    SpriteSheet arrowButtonSheet2;
    SpriteSheet lockNumberSheet;
    SpriteSheet doorSheet;
    SpriteSheet closetSheet;
    SpriteSheet pictureFrameSheet;
    SpriteSheet barSheet;
    SpriteSheet paintSheet;
    SpriteSheet jigsawSheet;
    SpriteSheet buttonSheet;
    SpriteSheet lightSheet;
    SpriteSheet eyeSheet;
    SpriteSheet heartSheet;
    private BufferedImage player_sheet;
    private BufferedImage npc_Sheet;
    private BufferedImage arrow_Button_Sheet;
    private BufferedImage arrow_Button_Sheet2;
    private BufferedImage lock_Number_Sheet;
    private BufferedImage door_Sheet;
    private BufferedImage closet_Sheet;
    private BufferedImage picture_Frame_Sheet;
    private BufferedImage bar_Sheet;
    private BufferedImage paint_Sheet;
    private BufferedImage jigsaw_Sheet;
    private BufferedImage button_Sheet;
    private BufferedImage light_Sheet;
    private BufferedImage eye_Sheet;
    private BufferedImage heart_Sheet;

    public BufferedImage[] background = new BufferedImage[13];
    public BufferedImage[] player = new BufferedImage[50];
    public BufferedImage[] playParkour;
    public BufferedImage[] npc = new BufferedImage[4];
    public BufferedImage[] paint = new BufferedImage[8];
    public BufferedImage[] paintThumbnail = new BufferedImage[7];
    public BufferedImage[] item = new BufferedImage[4];
    public BufferedImage[] ui = new BufferedImage[10];
    public BufferedImage[] hint = new BufferedImage[8];
    public BufferedImage[] cursor = new BufferedImage[2];
    public BufferedImage[] floor = new BufferedImage[2];
    public BufferedImage[] arrowButton = new BufferedImage[4];
    public BufferedImage[] lockNumber = new BufferedImage[10];
    public BufferedImage[] door = new BufferedImage[7];
    public BufferedImage[] closet = new BufferedImage[7];
    public BufferedImage[] pictureFrame = new BufferedImage[7];
    public BufferedImage[] barUp = new BufferedImage[7];
    public BufferedImage[] barDown = new BufferedImage[1];
    public BufferedImage[] button = new BufferedImage[12];
    public BufferedImage[] jigsaw = new BufferedImage[9];
    public BufferedImage[] light = new BufferedImage[4];
    public BufferedImage[] eyes = new BufferedImage[6];
    public BufferedImage[] hearts = new BufferedImage[6];

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
        npc_Sheet = ImageResource.getInstance().getImage("/Art/Character/enemy.png");
        npcSheet = new SpriteSheet(npc_Sheet);
        arrow_Button_Sheet = ImageResource.getInstance().getImage("/Art/UI/Lockbutton.png");
        arrowButtonSheet = new SpriteSheet(arrow_Button_Sheet);
        arrow_Button_Sheet2 = ImageResource.getInstance().getImage("/Art/UI/Lockbutton2.png");
        arrowButtonSheet2 = new SpriteSheet(arrow_Button_Sheet2);
        lock_Number_Sheet = ImageResource.getInstance().getImage("/Art/UI/numberLock.png");
        lockNumberSheet = new SpriteSheet(lock_Number_Sheet);
        door_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/doorSheet.png");
        doorSheet = new SpriteSheet(door_Sheet);
        closet_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/closetSheet.png");
        closetSheet = new SpriteSheet(closet_Sheet);
        picture_Frame_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/pictureFrameSheet.png");
        pictureFrameSheet = new SpriteSheet(picture_Frame_Sheet);
        bar_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/barSheet.png");
        barSheet = new SpriteSheet(bar_Sheet);
        paint_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/paintSheet.png");
        paintSheet = new SpriteSheet(paint_Sheet);
        jigsaw_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/jigsawSheet.png");
        jigsawSheet = new SpriteSheet(jigsaw_Sheet);
        button_Sheet = ImageResource.getInstance().getImage("/Art/UI/buttonSheet.png");
        buttonSheet = new SpriteSheet(button_Sheet);
        light_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/lightSheet.png");
        lightSheet = new SpriteSheet(light_Sheet);
        eye_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/eyeSheet.png");
        eyeSheet = new SpriteSheet(eye_Sheet);
        heart_Sheet = ImageResource.getInstance().getImage("/Art/Game Material/itemSheets/heartSheet.png");
        heartSheet = new SpriteSheet(heart_Sheet);
        
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
        player[30] = playerSheet.grabImage(12, 6, 48, 48);//dead
        player[31] = playerSheet.grabImage(13, 6, 48, 48);
        player[32] = playerSheet.grabImage(14, 6, 48, 48);
        player[33] = playerSheet.grabImage(15, 6, 48, 48);
        player[34] = playerSheet.grabImage(16, 6, 48, 48);
        player[35] = playerSheet.grabImage(16, 6, 48, 48);
        player[36] = playerSheet.grabImage(17, 6, 48, 48);
        player[37] = playerSheet.grabImage(18, 6, 48, 48);
        player[38] = playerSheet.grabImage(19, 6, 48, 48);
        player[39] = playerSheet.grabImage(21, 6, 48, 48);
        player[40] = playerSheet.grabImage(2, 8, 48, 48);// sit
        
        
        //enemyAnimation
        npc[0] = npcSheet.grabImage(7, 6, 48, 48);
        npc[1] = npcSheet.grabImage(8, 6, 48, 48);
        npc[2] = npcSheet.grabImage(9, 6, 48, 48);
        npc[3] = npcSheet.grabImage(21, 6, 48, 48);//npc dead
        
        //arrowButton
        arrowButton[0] = arrowButtonSheet.grabImage(1, 1, 64, 64);
        arrowButton[1] = arrowButtonSheet.grabImage(2, 1, 64, 64);
        arrowButton[2] = arrowButtonSheet2.grabImage(1, 1, 64, 64);
        arrowButton[3] = arrowButtonSheet2.grabImage(2, 1, 64, 64);
        
        //LockNumber
        lockNumber[0] = lockNumberSheet.grabImage(1, 1, 64, 64);
        lockNumber[1] = lockNumberSheet.grabImage(1, 1, 64, 64);
        lockNumber[2] = lockNumberSheet.grabImage(2, 1, 64, 64);
        lockNumber[3] = lockNumberSheet.grabImage(3, 1, 64, 64);
        lockNumber[4] = lockNumberSheet.grabImage(4, 1, 64, 64);
        lockNumber[5] = lockNumberSheet.grabImage(5, 1, 64, 64);
        lockNumber[6] = lockNumberSheet.grabImage(6, 1, 64, 64);
        lockNumber[7] = lockNumberSheet.grabImage(7, 1, 64, 64);
        lockNumber[8] = lockNumberSheet.grabImage(8, 1, 64, 64);
        lockNumber[9] = lockNumberSheet.grabImage(9, 1, 64, 64);

        //backgorund
        
        background[0] = ImageResource.getInstance().getImage("/Art/Background/Menu.png");
        background[1] = ImageResource.getInstance().getImage("/Art/Background/backGround1.png");
        background[2] = ImageResource.getInstance().getImage("/Art/Background/TestOption.png");
        background[3] = ImageResource.getInstance().getImage("/Art/Background/backGround2.png");
        background[4] = ImageResource.getInstance().getImage("/Art/Background/farMountain.png");
        background[5] = ImageResource.getInstance().getImage("/Art/Background/nearMountains.png");
        background[6] = ImageResource.getInstance().getImage("/Art/Background/road.png");
        background[7] = ImageResource.getInstance().getImage("/Art/Background/fakeBackground.png");
        background[8] = ImageResource.getInstance().getImage("/Art/Background/magicBackground.png");
        background[9] = ImageResource.getInstance().getImage("/Art/Background/room.png");
        background[10] = ImageResource.getInstance().getImage("/Art/Background/vignette.png");
        background[11] = ImageResource.getInstance().getImage("/Art/Background/noise.png");
        background[12] = ImageResource.getInstance().getImage("/Art/Background/vignette2.png");
        
        //大張圖畫與相框
        paint[0] = ImageResource.getInstance().getImage("/Art/Game Material/picture/picture1.png");
        paint[1] = ImageResource.getInstance().getImage("/Art/Game Material/picture/picture2.png");
        paint[2] = ImageResource.getInstance().getImage("/Art/Game Material/picture/picture2Base.png");
        paint[3] = ImageResource.getInstance().getImage("/Art/Game Material/picture/picture3.png");
        paint[4] = ImageResource.getInstance().getImage("/Art/Game Material/picture/picture3Base.png");
        paint[5] = ImageResource.getInstance().getImage("/Art/Game Material/frame.png");
        paint[6] = ImageResource.getInstance().getImage("/Art/Game Material/picture/picture4.png");
        
        
        //掛畫縮圖
        paintThumbnail[0] = paintSheet.grabImage(1, 1, 200, 200);//第一幅
        paintThumbnail[1] = paintSheet.grabImage(2, 1, 200, 200);//第二幅
        paintThumbnail[2] = paintSheet.grabImage(3, 1, 200, 200);//第三幅
        paintThumbnail[3] = paintSheet.grabImage(6, 1, 200, 200);//第四幅底
        paintThumbnail[4] = paintSheet.grabImage(4, 1, 200, 200);//第四幅解
        paintThumbnail[5] = paintSheet.grabImage(5, 1, 200, 200);//第二幅解
        paintThumbnail[6] = paintSheet.grabImage(7, 1, 200, 200);//第三幅解
        
        //jigsaw
        jigsaw[0] = jigsawSheet.grabImage(1, 1, 100, 100);
        jigsaw[1] = jigsawSheet.grabImage(2, 1, 100, 100);
        jigsaw[2] = jigsawSheet.grabImage(3, 1, 100, 100);
        jigsaw[3] = jigsawSheet.grabImage(1, 2, 100, 100);
        jigsaw[4] = jigsawSheet.grabImage(2, 2, 100, 100);
        jigsaw[5] = jigsawSheet.grabImage(3, 2, 100, 100);
        jigsaw[6] = jigsawSheet.grabImage(1, 3, 100, 100);
        jigsaw[7] = jigsawSheet.grabImage(2, 3, 100, 100);
        jigsaw[8] = jigsawSheet.grabImage(3, 3, 100, 100);
        
        //個人檔案、放大鏡跟紙張圖示
        item[0] = ImageResource.getInstance().getImage("/Art/Game Material/paper.png");
        item[1] = ImageResource.getInstance().getImage("/Art/Game Material/magnifier.png");
        item[2] = ImageResource.getInstance().getImage("/Art/Game Material/magnifierForPuzzle.png");
        item[3] = ImageResource.getInstance().getImage("/Art/Game Material/file.png");
        
        
        ui[0] = ImageResource.getInstance().getImage("/Art/UI/dialogBubble.png");
        ui[1] = ImageResource.getInstance().getImage("/Art/UI/dialogBox.png");
        ui[2] = ImageResource.getInstance().getImage("/Art/UI/option.png");
        ui[3] = ImageResource.getInstance().getImage("/Art/UI/enemyBubble.png");
        
        
        //提示畫面
        hint[0] = ImageResource.getInstance().getImage("/Art/Hint/hint1.png");
        hint[1] = ImageResource.getInstance().getImage("/Art/Hint/hint2.png");
        hint[2] = ImageResource.getInstance().getImage("/Art/Hint/picture1Hint.png");
        hint[3] = ImageResource.getInstance().getImage("/Art/Hint/picture2Hint.png");
        hint[4] = ImageResource.getInstance().getImage("/Art/Hint/picture3Hint.png");
        hint[5] = ImageResource.getInstance().getImage("/Art/Hint/picture4Hint.png");
        hint[6] = ImageResource.getInstance().getImage("/Art/Game Material/file.png");
        hint[7] = ImageResource.getInstance().getImage("/Art/Hint/picture4Hint2.png");

        floor[0] = ImageResource.getInstance().getImage("/Art/Game Material/floor.png");
        
        
        //漸層發光的效果
        door[0] = doorSheet.grabImage(1, 1, 200, 200);
        door[1] = doorSheet.grabImage(2, 1, 200, 200);//light
        door[2] = doorSheet.grabImage(3, 1, 200, 200);
        door[3] = doorSheet.grabImage(4, 1, 200, 200);
        door[4] = doorSheet.grabImage(5, 1, 200, 200);
        door[5] = doorSheet.grabImage(6, 1, 200, 200);
        door[6] = doorSheet.grabImage(7, 1, 200, 200);
        
        closet[0] = closetSheet.grabImage(1, 1, 101, 101);
        closet[1] = closetSheet.grabImage(2, 1, 101, 101);//light
        closet[2] = closetSheet.grabImage(3, 1, 101, 101);
        closet[3] = closetSheet.grabImage(4, 1, 101, 101);
        closet[4] = closetSheet.grabImage(5, 1, 101, 101);
        closet[5] = closetSheet.grabImage(6, 1, 101, 101);
        
        pictureFrame[0] = pictureFrameSheet.grabImage(1, 1, 200, 200);
        pictureFrame[1] = pictureFrameSheet.grabImage(2, 1, 200, 200);//light
        pictureFrame[2] = pictureFrameSheet.grabImage(3, 1, 200, 200);
        pictureFrame[3] = pictureFrameSheet.grabImage(4, 1, 200, 200);
        pictureFrame[4] = pictureFrameSheet.grabImage(5, 1, 200, 200);
        pictureFrame[5] = pictureFrameSheet.grabImage(6, 1, 200, 200);
        pictureFrame[6] = pictureFrameSheet.grabImage(7, 1, 200, 200);
        
        barUp[0] = barSheet.grabImage(1, 1, 64, 64);
        barUp[1] = barSheet.grabImage(3, 1, 64, 64);//light
        barUp[2] = barSheet.grabImage(5, 1, 64, 64);
        barUp[3] = barSheet.grabImage(7, 1, 64, 64);
        barUp[4] = barSheet.grabImage(9, 1, 64, 64);
        barUp[5] = barSheet.grabImage(11, 1, 64, 64);
        
        barDown[0] = barSheet.grabImage(2, 1, 64, 64);
        
        light[0] = lightSheet.grabImage(1, 1, 200, 200);
        light[1] = lightSheet.grabImage(2, 1, 200, 200);
        light[2] = lightSheet.grabImage(3, 1, 200, 200);
        light[3] = lightSheet.grabImage(4, 1, 200, 200);
        
        eyes[0] = eyeSheet.grabImage(1, 1, 30, 30);
        eyes[1] = eyeSheet.grabImage(2, 1, 30, 30);
        eyes[2] = eyeSheet.grabImage(3, 1, 30, 30);
        eyes[3] = eyeSheet.grabImage(4, 1, 30, 30);
        eyes[4] = eyeSheet.grabImage(5, 1, 30, 30);
        eyes[5] = eyeSheet.grabImage(6, 1, 30, 30);
        
        hearts[0] = heartSheet.grabImage(1, 1, 100, 100);
        hearts[1] = heartSheet.grabImage(2, 1, 100, 100);
        hearts[2] = heartSheet.grabImage(3, 1, 100, 100);
        hearts[3] = heartSheet.grabImage(4, 1, 100, 100);
        hearts[4] = heartSheet.grabImage(5, 1, 100, 100);
        hearts[5] = heartSheet.grabImage(6, 1, 100, 100);
        
        
        //buttons
        button[0] = buttonSheet.grabImage(1, 1, 200, 100);//exit dark
        button[1] = buttonSheet.grabImage(2, 1, 200, 100);//exit light
        button[2] = buttonSheet.grabImage(1, 2, 200, 100);//help dark
        button[3] = buttonSheet.grabImage(2, 2, 200, 100);//help light
        button[4] = buttonSheet.grabImage(1, 3, 200, 100);//home dark
        button[5] = buttonSheet.grabImage(2, 3, 200, 100);//home light
        button[6] = buttonSheet.grabImage(1, 4, 200, 100);//resume dark
        button[7] = buttonSheet.grabImage(2, 4, 200, 100);//resume light
        button[8] = buttonSheet.grabImage(1, 5, 200, 100);//start dark
        button[9] = buttonSheet.grabImage(2, 5, 200, 100);//start light
        button[10] = buttonSheet.grabImage(1, 6, 200, 100);//continue dark
        button[11] = buttonSheet.grabImage(2, 6, 200, 100);//continue light
    }
    
}

