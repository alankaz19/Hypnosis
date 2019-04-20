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
    
    private GameState[] gameStates;
    
    //現在狀態 
    private int currentState;
    private int prevState;
    
    //各個狀態常數
    protected static final int MENU_STATE = 0;
    protected static final int OPTION_STATE = 1;
    protected static final int LEVEL1_STATE = 2;
    protected static final int PUZZLE = 3;
    protected static final int LEVEL2_STATE = 4;
    protected static final int LEVEL3_STATE = 5;
    protected static final int LEVEL4_STATE = 6;
    public static GameStateManager gsm;

    public static GameStateManager getInstance(){
        if(gsm == null){
            gsm = new GameStateManager();
        }
        return gsm;
    }

    public GameStateManager() {
        gameStates = new GameState[10];
//        gameStates.add(new MenuState(this));
//        gameStates.add(new Option(this));
//        gameStates.add(new LevelOne(this));
//        gameStates.add(new PuzzleGame(this));
        currentState = MENU_STATE;
        loadState(currentState);
    }


    public void loadState(int state){
        if(state == MENU_STATE){
            gameStates[state] = new MenuState(this);
        }
        if(state == OPTION_STATE){
            gameStates[state] = new Option(this);
        }
        if(state == LEVEL1_STATE){
            gameStates[state] = new LevelOne(this);
        }
        if(state == PUZZLE){
            gameStates[state] = new PuzzleGame(this);
        }
    }

    public void unloadState(){
        gameStates[currentState] = null;
    }

    public void setState(int state) {
        //loadState(state);
        gameStates[currentState].getInstance();
        prevState = currentState;  //紀錄前一個state
        currentState = state;

    }

    public void newState(int state){
        loadState(state);
        currentState = state;
        gameStates[currentState].init();
    }

    public int getPrevState(){
        return prevState;
    }
    public void tick() {
        gameStates[currentState].tick();
    }
    
    public void render(Graphics g) {
        gameStates[currentState].render(g);
    }
    
    public  void keyPressed(int k){
        gameStates[currentState].keyPressed(k);
    }
    public  void keyReleased(int k){
        gameStates[currentState].keyReleased(k);
    }
    public  void mousePressed(int x, int y){
        gameStates[currentState].mousePressed(x,y);
    }
    public  void mouseDragged(int x, int y){
        gameStates[currentState].mouseDragged(x, y);
    }
}
