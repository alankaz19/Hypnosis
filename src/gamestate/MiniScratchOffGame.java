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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import scene.Texture;
import uiobject.Button;
import uiobject.HintBox;

/**
 *
 * @author Kai
 */
public class MiniScratchOffGame extends GameState{

    public static MiniScratchOffGame MINI_SCRATCH_GAME;
    
    private int keyPressed;
    private HintBox hint;
    private HintBox jigsawHint;
    private int hintTimer;
    private Frame frame;
    private Mask mask;
    private Riddle riddle;
    private Magnifier magnifier;
    private BufferedImage fakeBackground;
    private BufferedImage jigsaw;
    private Button exit;
    private boolean jigsawClicked;
    
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
            this.img = Texture.getInstance().paint[3];
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
            this.img =  Texture.getInstance().paint[4];
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.setClip(new Ellipse2D.Float(mouseX - 62 ,mouseY - 62, 132,132));
            g.drawImage(img, x,y,316,452,null);
        }

        @Override
        public ObjectID getID() {
            return null;
        }
    }
    
    //magnifier
    class Magnifier extends GameObject {

        public Magnifier(int x, int y, ObjectID id) {
            super(x, y, id);
        }
        

        @Override
        public void tick() {
            this.x = mouseX;
            this.y = mouseY;
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(Texture.getInstance().item[2], mouseX - 123, mouseY - 97 ,350,350, null);
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
        fakeBackground = Texture.getInstance().background[7];
        frame = new Frame((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 -38, ObjectID.PICTURE_IN_PUZZLE2);
        mask = new Mask ((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20,ObjectID.PICTURE);
        riddle = new Riddle((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20 , ObjectID.FRAME);
        magnifier = new Magnifier(mouseX, mouseX, ObjectID.MAGNIFIER);
        hint = new HintBox(4);
        hintTimer = 0;
        jigsawHint = new HintBox(1);
        jigsaw = Texture.getInstance().item[0];
        exit = new Button(955,523,200,100,Texture.getInstance().button[1],Texture.getInstance().button[0],1);
    }

    @Override
    public void tick() {
        event();
        magnifier.tick();
    }

    @Override
    public void event() {
        if (jigsawClicked && this.mouseX >= exit.getX()&& this.mouseX <= exit.getX() + exit.getWidth() && this.mouseY >= exit.getY() && this.mouseY <= exit.getY() + exit.getHeight()) {
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
        hint.render(g);
        frame.render(g);
        mask.render(g);
        if(jigsawHint.isShowed() && hintTimer <= 180){
            hintTimer++;
            jigsawHint.render(g);
            }
        
        if(hintTimer >= 180){
            jigsawHint.fadeOut(g);
        }
        
        //透明值設回1
        Graphics2D g2d =(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        if(hintTimer >= 180){
            exit.render(g);
        }
        
        if(this.mouseX >= 425 && this.mouseX <=870 && this.mouseY >= 30 && this.mouseY <= 615){
            magnifier.render(g);
        }
        riddle.render(g);
        if(!jigsawClicked){
            g.drawImage(jigsaw, 740, 495,60,60, null);
        }
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;

    }

    @Override
    public void keyReleased(int k) {
        if(k == keyPressed){
            keyPressed = -1;
        }
    }
    
    @Override
    public void mousePressed(int x, int y) {
        if (x >= exit.getX() && x <= exit.getX() + exit.getWidth() && y >= exit.getY() && y <= exit.getY() + exit.getHeight()) {
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
        if(x >= 740 && x <= 800 && y >= 495 && y <= 555){
            this.jigsawClicked = true;
            jigsawHint.setShowed(true);
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
