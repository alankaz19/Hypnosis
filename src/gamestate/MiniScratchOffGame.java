/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import resourcemanage.ImageResource;
import uiobject.DialogBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class MiniScratchOffGame extends GameState{

    public static MiniScratchOffGame MINI_SCRATCH_GAME;
    private int mouseX, mouseY;
    private Mask mask;
    private Riddle riddle;

    class Mask extends GameObject {
        BufferedImage img;

        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
            this.img = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.drawImage(img, x,y,400,400,null);

        }

        @Override
        public ObjectID getID() {
            return null;
        }
    }

    class Riddle extends GameObject{
        BufferedImage img;

        public Riddle(int x, int y, ObjectID id) {
            super(x, y, id);
            this.img = ImageResource.getInstance().getImage("/Art/Game Material/girl.png");
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.setClip(new Ellipse2D.Float(mouseX,mouseY, 100,100));
            g.drawImage(img,x,y,400,400,null);
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
         mask = new Mask (700,240,ObjectID.PICTURE);
         riddle = new Riddle(700,240,ObjectID.PICTURE);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void event() {
    }

    @Override
    public void render(Graphics g) {
        mask.render(g);
        riddle.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENU_STATE);
        }
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        
    }

    @Override
    public void mouseDragged(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void mouseReleased(int x) {

    }

}
