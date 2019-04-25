/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Graphics;
import uiobject.DialogBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class MiniClickGame extends GameState {
    public static MiniClickGame MINI_CLICK_GAME;
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
    
    
    public MiniClickGame(GameStateManager gsm) {
        super(gsm);
        this.init();
    }
    

    @Override
    public MiniClickGame getInstance() {
         if(MINI_CLICK_GAME == null){
            MINI_CLICK_GAME = new MiniClickGame(GameStateManager.getInstance());
        }
        return MINI_CLICK_GAME;
    }
    
    
    @Override
    public void init() {
        frame = new Frame(300, 300, ObjectID.FRAME);
        paint = new Paint(300, 300, ObjectID.PICTURE_IN_PUZZLE1);
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
        //點擊畫框變亮 
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x) {

    }

}
