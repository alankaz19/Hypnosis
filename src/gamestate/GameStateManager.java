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
    
    //各個狀態常數
    private static final int MENU_STATE = 0;
    private static final int LEVEL1_STATE = 1;
    private static final int LEVEL2_STATE = 2;
    private static final int LEVEL3_STATE = 3;
    private static final int LEVEL4_STATE = 4;
    private static final int OPTION_STATE = 5;
    
    public GameStateManager() {
        gameStates = new ArrayList<>();
        
        currentState = MENU_STATE;
        gameStates.add(new MenuState(this));
        
    }
    
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }
    
    public void tick() {
        gameStates.get(currentState).tick();
    }
    
    public void render(Graphics g) {
        gameStates.get(currentState).render(g);
    }
    
    public  void keyPressed(KeyEvent e){
        gameStates.get(currentState).keyPressed(e);
    }
    public  void keyReleased(KeyEvent e){
        gameStates.get(currentState).keyReleased(e);
    }
    public  void MouseClicked(MouseEvent e){
        gameStates.get(currentState).MouseClicked(e);
    }
    public  void MouseDragged(MouseEvent e){
        gameStates.get(currentState).MouseDragged(e);
    }
}
