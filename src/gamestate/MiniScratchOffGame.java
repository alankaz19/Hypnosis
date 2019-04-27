/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import resourcemanage.ImageResource;
import scene.BackGround;
import scene.Texture;
import uiobject.DialogBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class MiniScratchOffGame extends GameState{
    private static final BufferedImage ORIGINAL = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
    private BufferedImage exit;
    
    private BackGround fakeBackground;
    private int keyPressed;
    
    public static MiniScratchOffGame MINI_SCRATCH_GAME;
    
    private class Frame extends GameObject {

        public Frame(int x, int y, ObjectID id) {
            super(x, y, id);
        }
        
        
        @Override
        public void tick() {
            //
        }

        @Override
        public void render(Graphics g) {
            //畫圖
        }
        
    }
    
    private class Mask extends GameObject {

        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
        }
        
        
        @Override
        public void tick() {
            //
        }

        @Override
        public void render(Graphics g) {
            //畫圖
        }
        
    }
    
    private class Paint extends GameObject{
    
        private boolean Visible;
        public Paint(int x, int y, ObjectID id) {
            super(x, y, id);
            Visible = false;
            this.width = 300;
            this.height = 400;
        }
        
        
        @Override
        public void tick() {
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(Texture.getInstance().paint[0], x, y, null);
        }
        
        public void setVisible(boolean Visible){
            this.Visible = Visible;
        }
    }
    private Frame frame;
    private Paint paint;
    private Mask mask;
    private DialogBox hint;
    private Button exitButton;
    private boolean isDone;
    
    
    public MiniScratchOffGame(GameStateManager gsm) {
        super(gsm);
        this.init();
    }
    

    @Override
    public MiniScratchOffGame getInstance() {
         if(MINI_SCRATCH_GAME == null){
            MINI_SCRATCH_GAME = new MiniScratchOffGame(GameStateManager.getInstance());
        }
        return MINI_SCRATCH_GAME;
    }
    
    
    @Override
    public void init() {
        mask = new Mask(300, 300, ObjectID.FRAME);
        paint = new Paint(300, 300, ObjectID.PICTURE_IN_PUZZLE2);
        hint = new DialogBox();
        exitButton = new Button();
        isDone = false;
    }

    @Override
    public void tick() {
        frame.tick();
        paint.tick();
//        hint.tick();
//        continueButton.tick();
        
    }

    @Override
    public void event() {
    }

    @Override
    public void render(Graphics g) {
        frame.render(g);
        paint.render(g);
//        hint.render();
//        continueButton.render();
    }

    @Override
    public void keyPressed(int k) {
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        
    }

    @Override
    public void mouseDragged(int x, int y) {
        //瓜瓜瓜
    }

    @Override
    public void mouseReleased(int x) {

    }
    

}
