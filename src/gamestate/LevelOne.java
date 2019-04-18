package gamestate;

import game.Game;
import game.Handler;
import gameobject.ObjectID;
import gameobject.Player;
import scene.BackGround;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import scene.Camera;

public class LevelOne extends GameState {
    private Handler handler;
    private BackGround backGround;
    private int keyPressed;
    private Camera cam;

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
        handler.addObject(new Player(0, Game.HEIGHT/2,ObjectID.PLAYER));
        cam = new Camera(0,0);
    }

    @Override
    public void tick() {
        event();
        backGround.tick();
        handler.tick();
        
        for (int i = 0; i < handler.getObject().size(); i++) {
            if(handler.getObject().get(i).getID() == ObjectID.PLAYER){
            cam.tick(handler.getObject().get(i));
            System.out.println("Player x: " +handler.getObject().get(i).getX());
//            System.out.println("Player y: " +handler.getObject().get(i).getY());
//            System.out.println("backg x: " +backGround.getX());
//            System.out.println("backg y: " +backGround.getY());
            }
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics g2d = (Graphics2D)g;
        backGround.render(g);
        for (int i = 0; i < handler.getObject().size(); i++) {
            if(handler.getObject().get(i).getX() > 980 && handler.getObject().get(i).getID() == ObjectID.PLAYER){
//                g2d.translate(cam.getX()+200, cam.getY()); //begin of cam
            }else
            if(handler.getObject().get(i).getX() > 400 && handler.getObject().get(i).getID() == ObjectID.PLAYER){
                g2d.translate(cam.getX(), cam.getY()); //begin of cam
            }
        }
   
        
        handler.render(g);
        
        for (int i = 0; i < handler.getObject().size(); i++) {
//            if(handler.getObject().get(i).getX() > 980 && handler.getObject().get(i).getID() == ObjectID.PLAYER){
////                g2d.translate(-cam.getX()+200, -cam.getY()); //begin of cam
//            }else
            if(handler.getObject().get(i).getX() > 400 && handler.getObject().get(i).getID() == ObjectID.PLAYER){
                g2d.translate(-cam.getX(), -cam.getY());//end of cam
            }
        }
       


    }
    @Override
    public void event(){
        if(keyPressed == KeyEvent.VK_D){
                handler.getObject().get(0).setxVel(1);
                backGround.setScrollX(6);
                if(handler.getObject().get(0).getX() >= 1040){
                    handler.getObject().get(0).setxVel(0);
                }
                if(backGround.getX()>5120)
                    backGround.setScrollX(0);

        }
        else if(keyPressed == KeyEvent.VK_A){
                handler.getObject().get(0).setxVel(-1);
                backGround.setScrollX(-6);
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
