/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import scene.Texture;
import uiobject.Button;
import uiobject.Cursor;
import uiobject.HintBox;

/**
 *
 * @author alank
 */
public class FirstPicture extends GameState {
    public static FirstPicture FIRST_PICTURE;
    private Cursor cursor;
    private Frame frame;
    private Mask mask;
    private HintBox hint;
    private Button exitButton;
    private BufferedImage exit;
    private boolean hintShowed;
    private BufferedImage fakeBackground;
    private int keyPressed;
    
    private class Mask extends GameObject {
        BufferedImage img;
        private float alpha;
        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
            img = Texture.getInstance().paint[4];
            this.width = 300;
            this.height = 400;
            this.alpha = 1f;
        }    

        public void renderMask(Graphics2D g2d){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        }

        @Override
        public void tick() {
        }

        @Override
        public void render(Graphics g) {
            this.renderMask((Graphics2D)g);
            //x y 必須加上畫框的邊框寬度
            g.drawImage(Texture.getInstance().paint[4], x, y,316,450, null);
        }

    }
    
    //frame
    private class Frame extends GameObject{
        public Frame(int x, int y, ObjectID id) {
            super(x, y, id);
            this.width = Texture.getInstance().paint[5].getWidth();
            this.height = Texture.getInstance().paint[5].getHeight();
        }       

        @Override
        public void tick() {
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(Texture.getInstance().paint[5], x, y, null);
        }

    }
    
    public FirstPicture(GameStateManager gsm) {
        super(gsm);
        this.init();
    }
    
    @Override
    public FirstPicture getInstance() {
        if(FIRST_PICTURE == null){
            FIRST_PICTURE = new FirstPicture(GameStateManager.getInstance());
        }
        return FIRST_PICTURE;
    }


    @Override
    public void init() {
        // mask 加上畫框寬度
        exit = Texture.getInstance().ui[1];
        mask = new Mask((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20 , ObjectID.FRAME);
        frame = new Frame((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 -38, ObjectID.PICTURE_IN_PUZZLE2);
        hint = new HintBox(Texture.getInstance().hint[1]);
        hint.showMsg(Game.WIDTH /2 + 100, 200, 500, "eoeoeooeoeoeoeeo");
        fakeBackground = Texture.getInstance().background[7];
        cursor = new Cursor();
    }

    @Override
    public void tick() {
        mask.tick();
        event();
    }

    @Override
    public void event() {
        cursor.setPosition(mouseX, mouseY);
    }

    @Override
    public void render(Graphics g) {
        this.fadeIn(g);
        g.drawImage(fakeBackground, 0, 0, null);
        g.drawImage(exit,1000,440,100,100,null);
        frame.render(g);
        if(this.hintShowed){
            hint.render(g);
        }
        exitButton.render(g);
    }


    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k == keyPressed){
            keyPressed = -1;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        //點擊畫框變亮 
        if(!hintShowed){
            this.hintShowed = true;
        }
        if(exitButton.isHovered()){
            exitButton.isClicked();
            
        }
    }
    
    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
        if(exitButton.isReleased()){
                gsm.setState(GameStateManager.LEVEL1_STATE);
            }
    }

    @Override
    public void mouseMoved(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }
}

