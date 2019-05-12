/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import game.Updater;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import uiobject.Fonts;
import uiobject.Message;

/**
 *
 * @author alank
 */
public class HypnosisTransition extends GameState {
    private class Noise implements Updater{
        float alpha = 0.2f;
        double x = (Math.random() * Game.WIDTH);
        double y = (Math.random() * Game.HEIGHT);
        double yVel = 10;
        double length;
        int colorNumber = 3;

        public void setLength(double length) {
            this.length = length;
        }
        
        @Override
        public void tick(){
            length = 700;
            y += yVel;
            if(y + length > Game.HEIGHT){
                y = -100;
                x = (Math.random() * Game.WIDTH);
            }
        }
    
        @Override
        public void render(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
            switch((int)(Math.random() * 4)){
                case 1:
                    g.setColor(java.awt.Color.blue);
                    break;
                case 2:
                    g.setColor(java.awt.Color.gray);
                    break;
                case 3:
                    g.setColor(java.awt.Color.red);
                    break;
                case 4:
                    g.setColor(java.awt.Color.white);
                    break;
            }
            
            g.drawLine((int)x, (int)y, (int)x, (int)(y+ length) );
            
        }
    
    }
    
    public static HypnosisTransition HYPNOSIS_TRANSITION;
    private Message msg;
    private int timer;
    private final int TIME = 190;
    private Noise[] noise;
    
    public  HypnosisTransition getInstance(){
        if(HYPNOSIS_TRANSITION == null){
            HYPNOSIS_TRANSITION = new HypnosisTransition(GameStateManager.getInstance());
        }
        return HYPNOSIS_TRANSITION;
    }
    
    public HypnosisTransition(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init() {
        timer = 0;
        msg = new Message();
        msg.setFont(Fonts.getHorrorFont(40));
        msg.setLongWord();
        noise  = new Noise[5];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = new Noise();
        }
    }
    
    @Override
    public void tick() {
        event();
        msg.shake();
        timer++;
        for (Noise noise : noise) {
            noise.tick();
        }
    }

    @Override
    public void event() {
        if(!msg.isShow()){
            msg.showMsg(Game.WIDTH / 2 - 160 , Game.HEIGHT /2 - 45,"3  ·  ·  2  ·  ·  1  ·  ·", 2000, Color.white);
        }
        if(timer >= TIME){
            gsm.newState(GameStateManager.LEVEL1_STATE);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        
        msg.render(g);
        for (Noise noise : noise) {
            noise.render(g);
        }
    }

    @Override
    public void keyPressed(int k) {
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x,int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {

    }

}
