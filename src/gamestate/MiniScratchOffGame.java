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
import uiobject.DialogBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class MiniScratchOffGame extends GameState{
    private static final BufferedImage ORIGINAL = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
    private BufferedImage check;
    private BufferedImage exit;
    
    private BackGround fakeBackground;
    private int keyPressed, selection;
    
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

        @Override
        public ObjectID getID() {
            return this.id;
        }


        
    }
    
    private class Paint extends GameObject{
    
        private int keyPressed;
        private boolean isVisible;
        public Paint(int x, int y, ObjectID id) {
            super(x, y, id);
            isVisible = false;
        }
        
        
        @Override
        public void tick() {
            //點擊變亮
        }

        @Override
        public void render(Graphics g) {
            //畫圖
        }

        @Override
        public ObjectID getID() {
            return this.id;
        }
        
        private void setIsVisible(){
            //if 全亮 isVisible = true;
        }
    }
    private Frame frame;
    private Paint paint;
    private DialogBox hint;
    private Button continueButton;
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
        frame = new Frame(300, 300, ObjectID.FRAME);
        paint = new Paint(300, 300, ObjectID.PICTURE_IN_PUZZLE2);
        hint = new DialogBox();
        continueButton = new Button();
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
