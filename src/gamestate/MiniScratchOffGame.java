/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import gameobject.GameObject;
import gameobject.ObjectID;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import resourcemanage.ImageResource;
import scene.Texture;
import uiobject.HintBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class MiniScratchOffGame extends GameState{

    public static MiniScratchOffGame MINI_SCRATCH_GAME;
    private int mouseX, mouseY;
    private int keyPressed;
    private Frame frame;
    private Mask mask;
    private Riddle riddle;
    private BufferedImage fakeBackground;
    private BufferedImage exit;
    
    private class Frame extends GameObject{
        private boolean Visible;
        public Frame(int x, int y, ObjectID id) {
            super(x, y, id);
            this.Visible = false;
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

        public void setVisible(boolean Visible){
            this.Visible = Visible;
        }
    }
    
    //Mask class
    class Mask extends GameObject {
        BufferedImage img;

        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
            this.img = ImageResource.getInstance().getImage("/Art/Game Material/picture2.png");
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.drawImage(img, x,y,316,452,null);
        }

        @Override
        public ObjectID getID() {
            return null;
        }
    }
    
    //Riddle class
    class Riddle extends GameObject{
        BufferedImage img;
        
        public Riddle(int x, int y, ObjectID id) {
            super(x, y, id);
            this.img = ImageResource.getInstance().getImage("/Art/Game Material/picture4.png");
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.setClip(new Ellipse2D.Float(mouseX,mouseY, 125,125));
            g.drawImage(img, x,y,316,452,null);
        }

        @Override
        public ObjectID getID() {
            return null;
        }
    }

    @Override
    public MiniScratchOffGame getInstance() {
        if(MINI_SCRATCH_GAME == null){
            MINI_SCRATCH_GAME = new MiniScratchOffGame(GameStateManager.getInstance());
        }
        return MINI_SCRATCH_GAME;
    }

    
    public MiniScratchOffGame(GameStateManager gsm) {
        super(gsm);
        this.init();
    }
    
    @Override
    public void init() {
        exit = Texture.getInstance().ui[1];
        this.fakeBackground = Texture.getInstance().background[7];
        frame = new Frame((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 -38, ObjectID.PICTURE_IN_PUZZLE2);
        mask = new Mask ((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20,ObjectID.PICTURE);
        riddle = new Riddle((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20 , ObjectID.FRAME);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void event() {
    }

    @Override
    public void render(Graphics g) {
        this.fadeIn(g);
        g.drawImage(fakeBackground, 0, 0, null);
        g.drawImage(exit,1000,440,100,100,null);
        frame.render(g);
        mask.render(g);
        riddle.render(g);
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
        if ( x >= 1000 && x <= 1240 && y >= 440 && y <= 540) {
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
        mouseX = x;
        mouseY = y;

    }

}
