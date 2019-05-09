/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import game.Handler;
import gameobject.ObjectID;
import gameobject.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import scene.BackGround;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class LevelThree extends GameState{
    
    private final int PLAYER = 0;
    private final int EVIL = 1;
    private final int BACKGROUND = 10;//not correct number yet
    
    
    private BackGround background;
    private Handler handler;
    private int keyPressed;

    public static LevelThree LEVEL_THREE;

    public  LevelThree getInstance(){
        if(LEVEL_THREE == null){
            LEVEL_THREE = new LevelThree(GameStateManager.getInstance());
        }
        return LEVEL_THREE;
    }

    public LevelThree(GameStateManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init() {
        
        handler = new Handler();
        background = new BackGround(BACKGROUND){
             @Override
            public void render(Graphics g) {
                g.drawImage(Texture.getInstance().background[BACKGROUND],0,0,Game.WIDTH, Game.HEIGHT, null);
            }
        };
        handler.addObject(new Player(0, Game.HEIGHT / 2+50, ObjectID.PLAYER,5));
    }

    @Override
    public void tick() {
        event();
        handler.tick();
        background.tick();
    }

    

    @Override
    public void render(Graphics g) {
        
        background.render(g);
        
        handler.render(g);
        
        
    }

    @Override
    public void event(){
//        System.out.println("Player x: " +handler.getObject().get(PLAYER).getX());//pirnt 角色x
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(k == KeyEvent.VK_ESCAPE){
            gsm.newState(GameStateManager.OPTION_STATE);
        }
        if(k == KeyEvent.VK_ENTER){
            gsm.setState(GameStateManager.MENU_STATE);
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k == keyPressed ){ // only assign -1 to pressedKey if no other key is pressed to chane the pressedKey value
            keyPressed = -1;
        }
    }
    
   
    
    @Override
    public void mousePressed(int x, int y) {
      
        
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

    }
}