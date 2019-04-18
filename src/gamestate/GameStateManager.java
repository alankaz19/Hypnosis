/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Kai
 */
public class GameStateManager {
    
    private ArrayList<GameState> gameStates;
    
    //現在狀態 
    private int currentState;
    private int prevState;
    
    //各個狀態常數
    protected static final int MENU_STATE = 0;
    protected static final int OPTION_STATE = 1;
    protected static final int LEVEL1_STATE = 2;
    protected static final int LEVEL2_STATE = 3;
    protected static final int LEVEL3_STATE = 4;
    protected static final int LEVEL4_STATE = 5;
    public static GameStateManager gsm;

    public static GameStateManager getInstance(){
        if(gsm == null){
            gsm = new GameStateManager();
        }
        return gsm;
    }

    public GameStateManager() {
        gameStates = new ArrayList<>();
        gameStates.add(new MenuState(this));
        gameStates.add(new Option(this));
        gameStates.add(new LevelOne(this));

        currentState = MENU_STATE;
    }


    public void setState(int state) {
        prevState = currentState;  //紀錄前一個state
        currentState = state;
        gameStates.get(currentState).getInstance();
    }

    public void newGame(int state){
        prevState = currentState;  //紀錄前一個state
        currentState = state;
        gameStates.get(currentState).init();
    }

    public int getPrevState(){
        return prevState;
    }
    public void tick() {
        gameStates.get(currentState).tick();
    }
    
    public void render(Graphics g) {
        gameStates.get(currentState).render(g);
    }
    
    public  void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }
    public  void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
    public  void mousePressed(int x, int y){
        gameStates.get(currentState).mousePressed(x,y);
    }
    public  void mouseDragged(MouseEvent e){
        gameStates.get(currentState).mouseDragged(e);
    }
}
