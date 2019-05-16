/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import gameobject.GameObject;
import gameobject.ObjectID;
import static gamestate.MenuState.SECRET;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import scene.AudioManager;
import scene.Texture;
import uiobject.Button;
import uiobject.HintBox;

/**
 *
 * @author Kai
 */
public class MiniClickGame extends GameState {
    
    public static MiniClickGame MINI_CLICK_GAME;
    
    private Frame frame;
    private Mask mask;
    private HintBox hint;
    private Button exit;
    private BufferedImage fakeBackground;
    private int keyPressed;
    
    private class Mask extends GameObject {
        BufferedImage img;
        private boolean cleared;
        private float alpha;
        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
            cleared = false;
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
            if(!SECRET){
                g.drawImage(Texture.getInstance().paint[1], x, y,316,450, null);
            }else{
                g.drawImage(Texture.getInstance().paint[8], x, y,316,450, null);
            }
            
        }

        public void setCleared(boolean cleared) {
            this.cleared = cleared;
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
            if(!SECRET){
                g.drawImage(Texture.getInstance().paint[2], x + 58, y + 56 ,316,450, null);
            }else{
                g.drawImage(Texture.getInstance().paint[9], x + 58, y + 56 ,316,450, null);
            }
        }

    }
    
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
        fakeBackground = Texture.getInstance().background[7];
        // mask 加上畫框寬度
        mask = new Mask((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20 , ObjectID.FRAME);
        frame = new Frame((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 -38, ObjectID.PICTURE_IN_PUZZLE2);
        hint = new HintBox(3);
        exit = new Button(955,523,200,100,Texture.getInstance().button[1],Texture.getInstance().button[0],1);
    }

    @Override
    public void tick() {
        mask.tick();
        event();
    }

    @Override
    public void event() {
        if (mask.cleared && mouseX >= exit.getX() && mouseX <= exit.getX() + exit.getWidth() && mouseY >= exit.getY() && mouseY <= exit.getY() + exit.getHeight()) {
            exit.setHovered(true);
        }else{
            exit.setHovered(false);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        g.drawImage(fakeBackground, 0, 0, null);
        frame.render(g);
        if(mask.cleared){
            exit.render(g);
        }
        mask.render(g);
        hint.render(g);
    }


    @Override
    public void keyPressed(int k) {
        keyPressed = k;
//        if(keyPressed == KeyEvent.VK_ESCAPE){
//            gsm.setState(GameStateManager.LEVEL1_STATE);
//        }
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
        if(x > mask.x && x < mask.x + mask.getWidth() && y > mask.y && y < mask.y + mask.getHeight()){
            if(mask.alpha - 0.05 < 0.09){
                mask.alpha = 0;
                mask.cleared = true;
                return;
            }
            mask.alpha -= 0.11f;
            AudioManager.getInstance().getPlayList()[AudioManager.PAPER].play();
        }
        
        if (exit.isHovered()) {
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
    }
    
    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
        this.setMousePos(x, y);
    }
}
