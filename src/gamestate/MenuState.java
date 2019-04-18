/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import game.Game;
import scene.Texture;

/**
 *
 * @author Kai
 */

public class MenuState extends GameState {
    private BufferedImage img;

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

    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        if(x >= 295 && x <= 434 && y >= 456 && y <= 500){
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
}
