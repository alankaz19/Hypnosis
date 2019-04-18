package gamestate;

import game.Game;
import game.Handler;
import gameobject.Player;
import scene.BackGround;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LevelOne extends GameState {
    private Handler handler;
    private BackGround backGround;
    private int keyPressed;

    public static LevelOne LevelOne;

    public  LevelOne getInstance(){
        if(LevelOne == null){
            LevelOne = new LevelOne(GameStateManager.getInstance());
        }
        return LevelOne;
    }

    public LevelOne(GameStateManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init() {
        handler = new Handler();
        backGround = new BackGround(1);
        handler.addObject(new Player(0, Game.HEIGHT/2));
    }

    @Override
    public void tick() {
        event();
        backGround.tick();
        handler.tick();

    }

    @Override
    public void render(Graphics g) {
        backGround.render(g);
        handler.render(g);

    }
    @Override
    public void event(){
        if(keyPressed == KeyEvent.VK_D){
                handler.getObject().get(0).setxVel(1);
                backGround.setScrollX(5);
                if(handler.getObject().get(0).getX() >= 1000){
                    handler.getObject().get(0).setxVel(0);
                    backGround.setScrollX(0);
                }

        }
        else if(keyPressed == KeyEvent.VK_A){
                handler.getObject().get(0).setxVel(-1);
                backGround.setScrollX(-5);
            if(handler.getObject().get(0).getX() <= 0){
                handler.getObject().get(0).setxVel(0);
                backGround.setScrollX(0);
            }

        }
        else{
            handler.getObject().get(0).setxVel(0);
            backGround.setScrollX(0);
        }
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.OPTION_STATE);
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
    public void mouseDragged(MouseEvent e) {

    }
}
