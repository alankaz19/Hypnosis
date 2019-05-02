/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Game;
import resourcemanage.SoundResource;
import scene.Texture;

/**
 *
 * @author Kai
 */

public class MenuState extends GameState {
    private BufferedImage img;
    private AudioClip intro;

    public static MenuState menuState;


    public  MenuState getInstance(){
        if(menuState == null){
            menuState = new MenuState(GameStateManager.getInstance());
        }
        return menuState;
    }

    public MenuState(GameStateManager gsm) {
        super(gsm);
        init();
        img = Texture.getInstance().background[0];
        intro.loop();
    }

    @Override
    public void init() {
        intro = SoundResource.getInstance().getClip("/Art/BackGround/IntroMusic.wav");

    }

    @Override
    public void tick() {

    }

    @Override
    public void event() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,0,0, Game.WIDTH, Game.HEIGHT,null);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_2){
            gsm.newState(GameStateManager.LEVEL2_STATE);
        }
        if(k == KeyEvent.VK_3){
            gsm.newState(GameStateManager.SCRATCH_GAME);
        }
        if(k == KeyEvent.VK_1){
            intro.stop();
            gsm.newState(GameStateManager.INTRO);
        }

    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        //開始遊戲
        if(x >= 295 && x <= 434 && y >= 456 && y <= 500){
            gsm.newState(GameStateManager.INTRO);
        }

    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x) {

    }

}
