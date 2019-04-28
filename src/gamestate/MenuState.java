/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Game;
import scene.Texture;

/**
 *
 * @author Kai
 */

public class MenuState extends GameState {
    private BufferedImage img;

    public static MenuState menuState;


    public  MenuState getInstance(){
        if(menuState == null){
            menuState = new MenuState(GameStateManager.getInstance());
        }
        return menuState;
    }

    public MenuState(GameStateManager gsm) {
        super(gsm);
        img = Texture.getInstance().background[0];

    }

    @Override
    public void init() {
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

    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        //開始遊戲
        if(x >= 295 && x <= 434 && y >= 456 && y <= 500){
            gsm.newState(GameStateManager.CLICK_GAME);
        }

    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x) {

    }

}
