/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.Graphics;
import java.util.Stack;

/**
 *
 * @author Kai
 */
public class GameStateManager {
    
    private GameState[] gameStates;
    Stack stateStorage;
    
    //現在狀態 
    private int currentState;
    private int prevState;
    
    //滑鼠座標
    private int mouseX;
    private int mouseY;
    
    //各個狀態常數
    protected static final int MENU_STATE = 0;
    protected static final int OPTION_STATE = 1;
    protected static final int LEVEL1_STATE = 2;
    protected static final int PUZZLE_GAME = 3;
    protected static final int LEVEL2_STATE = 4;
    protected static final int LEVEL3_STATE = 5;
    protected static final int CLICK_GAME = 6;
    protected static final int SCRATCH_GAME = 7;
    protected static final int INTRO = 8;
    protected static final int LEVEL1_SCENE = 9;
    protected static final int TRANSITION = 10;
    protected static final int EASTER_EGG = 11;
    protected static final int TRANSITION_2 = 12;
    protected static final int NUMBER_LOCK = 13;
    protected static final int FIRST_PICTURE = 14;
    protected static final int HYPNOSIS_TRANSITION = 15;
    protected static final int THIRD_PICTURE = 16;
    protected static final int FORTH_PICTURE = 17;

    public static GameStateManager gsm;
    
    //單一實例
    public static GameStateManager getInstance(){
        if(gsm == null){
            gsm = new GameStateManager();
        }
        return gsm;
    }

    public GameStateManager() {
        gameStates = new GameState[18];
        currentState = MENU_STATE;
        loadState(currentState);
    }

    // 載入場景
    public void loadState(int state){
//        switch(state){
//            case MENU_STATE:
//                gameStates[state] = new MenuState(this);
//                break;
//            case OPTION_STATE:
//                gameStates[state] = new Option(this);
//                break;
//            case INTRO:
//                gameStates[state] = new Intro(this);
//                break;
//            case TRANSITION:
//                gameStates[state] = new Transition(this);
//                break;
//            case LEVEL1_STATE:
//                gameStates[state] = new LevelOne(this);
//                break;
//            case LEVEL1_SCENE:
//                gameStates[state] = new LevelOneScene(this);
//                break;
//            case LEVEL2_STATE:
//                gameStates[state] = new LevelTwo(this); 
//                break;
//            case LEVEL3_STATE:
//                gameStates[state] = new LevelTwo(this); 
//                break;
//            case PUZZLE_GAME:
//                gameStates[state] = new MiniPuzzleGame(this);
//                break;
//            case CLICK_GAME:
//                gameStates[state] = new MiniClickGame(this);
//                break;
//            case SCRATCH_GAME:
//                gameStates[state] = new MiniScratchOffGame(this);
//                break;
//            case EASTER_EGG:
//                gameStates[state] = new EasterEgg(this);
//                break;
//        }
        
        if(state == MENU_STATE){
            gameStates[state] = new MenuState(this);
        }
        if(state == OPTION_STATE){
            gameStates[state] = new Option(this);
        }
        if(state == INTRO){
            gameStates[state] = new Intro(this);
        }
        if(state == LEVEL1_STATE){
            gameStates[state] = new LevelOne(this);
        }
        if(state == LEVEL1_SCENE){
            gameStates[state] = new LevelOneScene(this);
        }
        if(state == LEVEL2_STATE){
            gameStates[state] = new LevelTwo(this);
        }
        if(state == LEVEL3_STATE){
            gameStates[state] = new LevelThree(this);
        }
        if(state == PUZZLE_GAME){
            gameStates[state] = new MiniPuzzleGame(this);
        }
        if(state == CLICK_GAME){
            gameStates[state] = new MiniClickGame(this);
        }
        if(state == SCRATCH_GAME){
            gameStates[state] = new MiniScratchOffGame(this);
        }
        if(state == NUMBER_LOCK){
            gameStates[state] = new NumberLock(this);
        }
        if(state == TRANSITION){
            gameStates[state] = new Transition(this);
        }
        if(state == TRANSITION_2){
            gameStates[state] = new Transition2(this);
        }
        if(state == EASTER_EGG){
            gameStates[state] = new EasterEgg(this);
        }
        if(state == FIRST_PICTURE){
            gameStates[state] = new FirstPicture(this);
        }
        if(state == HYPNOSIS_TRANSITION){
            gameStates[state] = new HypnosisTransition(this);
        }
        if(state == THIRD_PICTURE){
            gameStates[state] = new ThirdPicture(this);
        }
        if(state == FORTH_PICTURE){
            gameStates[state] = new ForthPicture(this);
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
        prevState = currentState; 
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
    public void mouseReleased(int x, int y){
        gameStates[currentState].mouseReleased(x,y);
    }
    public void mouseMoved(int x, int y){
        gameStates[currentState].mouseMoved(x,y);
    }
    public void setMousePoiont(int x, int y){
        this.mouseX = x;
        this.mouseY = y;
    }
    
    public int getMouseX() {
        return this.mouseX;
    }
    
    public int getMouseY() {
        return this.mouseY;
    }
}
