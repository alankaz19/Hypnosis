/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Color;
import java.awt.Graphics;
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
    
    //SHOW TRANSITION
    private boolean trainsitionShowed;
    private int timer;
    
    private BufferedImage fakeBackground;
    private Cursor cursor;
    private Frame frame;
    private Mask mask;
    private HintBox hint;
    private Button exit;
    private int keyPressed;
    
    private class Mask extends GameObject {
        BufferedImage img;
        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
            img = Texture.getInstance().paint[0];
            this.width = 300;
            this.height = 400;
        }    
        @Override
        public void tick() {
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(img, x, y,316,450, null);
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
        fakeBackground = Texture.getInstance().background[7];
        // mask 加上畫框寬度
        mask = new Mask((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2 + 58, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 + 20 , ObjectID.FRAME);
        frame = new Frame((Game.WIDTH -Texture.getInstance().paint[5].getWidth()) / 2, (Game.HEIGHT - Texture.getInstance().paint[5].getHeight())/2 -38, ObjectID.PICTURE_IN_PUZZLE2);
        hint = new HintBox(2);
        exit = new Button(955,523,200,100,Texture.getInstance().button[1],Texture.getInstance().button[0],1);
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
        if (mouseX >= exit.getX() && mouseX <= exit.getX() + exit.getWidth() && mouseY >= exit.getY() && mouseY <= exit.getY() + exit.getHeight()) {
            exit.setHovered(true);
        }else{
            exit.setHovered(false);
        }
//        if(timer >= 170 && !trainsitionShowed){
//            gsm.newState(GameStateManager.TRANSITION);
//            trainsitionShowed = true;
//        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        g.drawImage(fakeBackground, 0, 0, null);
        exit.render(g);
        frame.render(g);
        mask.render(g);
        hint.render(g);
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
        if (!trainsitionShowed && exit.isHovered()) {
            trainsitionShowed = true;
            gsm.newState(GameStateManager.TRANSITION);
        }
        else if (trainsitionShowed && exit.isHovered()) {
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

