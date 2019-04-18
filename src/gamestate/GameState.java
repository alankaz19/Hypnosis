/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Kai
 */
public abstract class GameState {
    
    protected GameStateManager gsm;

    
    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public abstract GameState getInstance();
    public abstract void init();
    public abstract void tick();
    public abstract void event();
    public abstract void render(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void mousePressed(int x, int y);
    public abstract void mouseDragged(MouseEvent e);
}
