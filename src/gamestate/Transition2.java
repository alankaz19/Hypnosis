/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import game.Updater;
import java.awt.Color;
import java.awt.Graphics;
import uiobject.Fonts;
import uiobject.Message;

/**
 *
 * @author alank
 */
public class Transition2 extends GameState{
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
            switch((int)(Math.random() * colorNumber)){
                case 1:
                    g.setColor(java.awt.Color.blue);
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
    
    public static Transition2 TRANSITION_2;
    private Line[] lines;
    private Message msg;
    
    public  Transition2 getInstance(){
        if(TRANSITION_2 == null){
            TRANSITION_2 = new Transition2(GameStateManager.getInstance());
        }
        return TRANSITION_2;
    }
    
    public Transition2(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init() {
        lines  = new Line[1000];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line();
        }
        msg = new Message();
        msg.setFont(Fonts.getHorrorFont(50));
        
    }
    
    @Override
    public void tick() {
        for (Line line : lines) {
            line.tick();
        }
        event();
        msg.shake();
        
    }

    @Override
    public void event() {
        if(!msg.isShow()){
            msg.showMsg(Game.WIDTH / 2 - 140 , Game.HEIGHT /2 - 60,"妳今天吃青菜了嗎？？", 2000, Color.red);
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

