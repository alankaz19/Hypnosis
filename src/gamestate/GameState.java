/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Kai
 */
public abstract class GameState {
    
    protected GameStateManager gsm;
    protected float alpha;
    
    protected GameState(GameStateManager gsm){  
        this.alpha = 0;
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
    public abstract void mouseDragged(int x, int y);
    public abstract void mouseReleased(int x, int y);
    public void fadeIn(Graphics g){
        if(this.alpha <= 0.99f && this.alpha >= 0) {
            this.alpha += 0.01f;
        }
        else if(this.alpha >= 0.99f) {
            this.alpha = 1.0f;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
    }
}
