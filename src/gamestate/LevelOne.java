package gamestate;

import game.Game;
import game.Handler;
import gameobject.ObjectID;
import gameobject.Player;
import gameobject.items.*;
import java.awt.Graphics;
import scene.BackGround;

import java.awt.event.KeyEvent;
import scene.Camera;

public class LevelOne extends GameState {
    private final int PLAYER = 0;
    private final int PICTURE1 = 1;
    private final int PICTURE2 = 2;
    private final int PICTURE3 = 3;
    private final int PICTURE4 = 4;
    
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
        cam = new Camera(0, 0,400);
        handler.addObject(new Player(0, Game.HEIGHT / 2, ObjectID.PLAYER,8));
        handler.addObject(new Picture(818, ObjectID.PICTURE1));
        handler.addObject(new Picture(820+640, ObjectID.PICTURE2));
        handler.addObject(new Picture(820+1280, ObjectID.PICTURE3));
        handler.addObject(new Picture(824+1920, ObjectID.PICTURE4));
        handler.addObject(new Door(824+2260, ObjectID.DOOR));
    }

    @Override
    public void tick() {
        event();
        backGround.tick();
        handler.tick();
        cam.tick(handler.getObject().get(PLAYER));
    }

    

    @Override
    public void render(Graphics g) {
        backGround.render(g);
        
        g.translate(cam.getX(), cam.getY()); //begin of cam
        
        handler.render(g);

        g.translate(-cam.getX(), -cam.getY());//end of cam
        
//        for (int i = 1; i < handler.getObject().size(); i++) {
//            if(handler.getObject().get(PLAYER).checkCollision(handler.getObject().get(i))){
//               handler.getObject().get(PLAYER).renderMsg(g, 50);
//            }
//        }    
        
    }

    @Override
    public void event(){
//        System.out.println("Player x: " +handler.getObject().get(PLAYER).getX());//pirnt 角色x

        for (int i = 1; i < handler.getObject().size(); i++) {
            if(handler.getObject().get(PLAYER).checkCollision(handler.getObject().get(i))){
                handler.getObject().get(i).setIsCollision(true);
//                handler.getObject().get(PLAYER).showMsg("collision測試", 150, Color.BLACK);
            }else{
                handler.getObject().get(i).setIsCollision(false);
            }
        }
        
        if(keyPressed == KeyEvent.VK_D){
            handler.getObject().get(PLAYER).setxVel(1);
            backGround.setScrollX(5);
            for(int i = 1;i < handler.getObject().size();i++){
                handler.getObject().get(i).setxVel(-2);
            }
            if(handler.getObject().get(PLAYER).getX() >= 1050){
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for(int i = 1;i < handler.getObject().size();i++){
                   handler.getObject().get(i).setxVel(0);
                }
            }
        }
        else if(keyPressed == KeyEvent.VK_A){
                handler.getObject().get(PLAYER).setxVel(-1);
                backGround.setScrollX(-5);
                for(int i = 1;i < handler.getObject().size();i++){
                    handler.getObject().get(i).setxVel(2);
                }
            if(handler.getObject().get(PLAYER).getX() <= 0){
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for(int i = 1;i < handler.getObject().size();i++){
                    handler.getObject().get(i).setxVel(0);
                }
            }
        }
        else{
            handler.getObject().get(PLAYER).setxVel(0);
            backGround.setScrollX(0);
            for(int i = 1;i < handler.getObject().size();i++){
                    handler.getObject().get(i).setxVel(0);
            }
        }
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
        if(handler.getObject().get(1).getIsCollision()){
            gsm.newState(GameStateManager.PUZZLE_GAME);
        }
        if(handler.getObject().get(5).getIsCollision()){
                gsm.newState(GameStateManager.LEVEL2_STATE);
            }
        
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
