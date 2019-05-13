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
import scene.AudioManager;
import scene.Texture;
import uiobject.Button;

/**
 *
 * @author Kai
 */

public class MenuState extends GameState {
    private BufferedImage background;
    private AudioClip intro;
    private Button start;

    public static MenuState menuState;


    public  MenuState getInstance(){
        if(menuState == null){
            menuState = new MenuState(GameStateManager.getInstance());
        }
        return menuState;
    }

    public MenuState(GameStateManager gsm) {
        super(gsm);
        intro.play();
        init();
        
        background = Texture.getInstance().background[0];
        //intro.loop();
    }

    @Override
    public void init() {
        start = new Button(245,465,200,100,Texture.getInstance().button[9],Texture.getInstance().button[8],1);
        intro = SoundResource.getInstance().getClip("/Art/Sound Effect/IntroMusic.wav");
    }

    @Override
    public void tick() {
        event();
    }

    @Override
    public void event() {
        if (this.mouseX >= start.getX() && this.mouseX <= start.getX() + start.getWidth() && this.mouseY >= start.getY() && this.mouseY <= start.getY() + start.getHeight()) {
            start.setHovered(true);
        }else{
            start.setHovered(false);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        g.drawImage(background,0,0, Game.WIDTH, Game.HEIGHT,null);
        start.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_1){
            intro.stop();
            gsm.newState(GameStateManager.LEVEL2_STATE);
        }
        if(k == KeyEvent.VK_2){
            intro.stop();
            gsm.newState(GameStateManager.EASTER_EGG);
        }
    }
    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        //開始遊戲
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_ONE_CLICK].play();

        if(start.isHovered()){
            gsm.newState(GameStateManager.LEVEL1_STATE);
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
