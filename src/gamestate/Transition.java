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
public class Transition extends GameState{
    private class Line implements Updater{
        double x = (Math.random() * Game.WIDTH);
        double y = (Math.random() * Game.HEIGHT);
        double xVel = Math.random() * 150 + 50 ;
        double length;
        int colorNumber = 3;
        

        public void setLength(double length) {
            this.length = length;
        }
        
        @Override
        public void tick(){
            length = (Math.random() * 2) + 1;
            x -= xVel;
            if(x + length < 0){
                x = Game.WIDTH + 100;
                y = (Math.random() * Game.HEIGHT);
            }
        }
    
        @Override
        public void render(Graphics g){
            switch((int)(Math.random() * 4)){
                case 1:
                    g.setColor(java.awt.Color.MAGENTA);
                    break;
                case 2:
                    g.setColor(java.awt.Color.red);
                    break;
                case 3:
                    g.setColor(java.awt.Color.green);
                    break;
            }
            
            g.drawLine((int)x, (int)y, (int)(x + length ), (int)y );
        }
    
    }
    
    public static Transition TRANSITION;
    private Line[] lines;
    private Message msg;
    private int timer;
    
    public  Transition getInstance(){
        if(TRANSITION == null){
            TRANSITION = new Transition(GameStateManager.getInstance());
        }
        return TRANSITION;
    }
    
    public Transition(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init() {
        timer = 0;
        lines  = new Line[1000];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line();
        }
        msg = new Message();
        msg.setFont(Fonts.getHorrorFont(40));
        msg.setLongWord();
    }
    
    @Override
    public void tick() {
        for (Line line : lines) {
            line.tick();
        }
        event();
        msg.shake();
        timer++;
    }

    @Override
    public void event() {
        if(!msg.isShow()){
            msg.showMsg(Game.WIDTH / 2 - 270 , Game.HEIGHT /2 - 45,"你   知   道   你   是   誰   嗎  ‧ ‧ ‧ ", 2000, Color.red);
        }
        if(timer >= 150){
            gsm.setState(GameStateManager.FIRST_PICTURE);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        
        for (Line line : lines) {
            line.render(g);
        }
        msg.render(g);
        Graphics2D g2d =(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        
        if(timer >= 30){
            this.fadeOut(g);
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
