package gamestate;

import game.Game;
import game.Handler;
import gameobject.ObjectID;
import gameobject.Player;
import gameobject.items.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import scene.BackGround;

import java.awt.event.KeyEvent;
import resourcemanage.SoundResource;
import scene.Camera;
import scene.PaintUtil;
import uiobject.Fonts;

public class LevelOne extends GameState {

    private final int PLAYER = 0;
    private final int PICTURE1 = 1;
    private final int MSG_POSITION = 0;
    
    private Handler handler;
    private BackGround backGround;
    private int keyPressed;
    private Camera cam;
    private String playerMsg;
    boolean sceneshow;
    private AudioClip bgm;

    public static LevelOne LevelOne;

    public LevelOne getInstance() {
        if (LevelOne == null) {
            LevelOne = new LevelOne(GameStateManager.getInstance());
        }
        return LevelOne;
    }

    public LevelOne(GameStateManager gsm) {
        super(gsm);
        bgm.loop();
        init();
    }

    @Override
    public void init() {
        bgm = SoundResource.getInstance().getClip("/Art/BackGround/Level1.wav");
        sceneshow = false;
        handler = new Handler();
        backGround = new BackGround(1);
        cam = new Camera(0, 0, 400);
        playerMsg = " 利用WASD鍵來移動 ···";
        try{
            handler.addObject(new Player(0, Game.HEIGHT / 2, ObjectID.PLAYER, 8));
        handler.addObject(new Picture(600, ObjectID.PICTURE){
            @Override
            public void render(Graphics g) {
               this.renderRotate(g,10);
            }
        });
        handler.addObject(new Picture(600 + 640, ObjectID.PICTURE));
        handler.addObject(new Picture(600 + 1280, ObjectID.PICTURE));
        handler.addObject(new Picture(600 + 1920, ObjectID.PICTURE));
        handler.addObject(new Door(800 + 2260, ObjectID.DOOR));
        }catch(ExceptionInInitializerError e){
            e.printStackTrace();
        }
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        backGround.render(g);
        
        for (int i = 1; i < handler.getObject().size(); i++) {
            if (handler.getObject().get(PLAYER).checkCollision(handler.getObject().get(i))) {
                //show hint for the first paint only
                if (handler.getObject().get(1).getID() == ObjectID.PICTURE) {
                    g.translate(cam.getX(), cam.getY());
                    PaintUtil.paintFocus((Graphics2D) g, new Rectangle(handler.getObject().get(1).x, handler.getObject().get(i).y, 128, 178), 6);
                    g.translate(-cam.getX(), -cam.getY());
                }
                //show hint only after scene is shown
                if(sceneshow){
                    if (handler.getObject().get(i).getID() == ObjectID.PICTURE) {
                        g.translate(cam.getX(), cam.getY());
                        PaintUtil.paintFocus((Graphics2D) g, new Rectangle(handler.getObject().get(i).x, handler.getObject().get(i).y, 128, 178), 6);
                        g.translate(-cam.getX(), -cam.getY());
                    }
                    if (handler.getObject().get(i).getID() == ObjectID.DOOR) {
                        g.translate(cam.getX(), cam.getY());
                        PaintUtil.paintFocus((Graphics2D) g, new Rectangle(handler.getObject().get(i).x, handler.getObject().get(i).y, 200, 300), 6);
                        g.translate(-cam.getX(), -cam.getY());
                    }
                }
            }
        }
        g.translate(cam.getX(), cam.getY()); //begin of cam
        handler.render(g);
        handler.getObject().get(PLAYER).renderMsg(g); // message following character head
        g.translate(-cam.getX(), -cam.getY());//end of cam


    }

    @Override
    public void event() {
//        System.out.println("Player x: " + handler.getObject().get(PLAYER).getX());//pirnt 角色x
        if(handler.getObject().get(PICTURE1).isShow()){
            if(!sceneshow){
                gsm.newState(GameStateManager.LEVEL1_SCENE);
                sceneshow = true;
            }
            for (int i = 2; i < handler.getObject().size() - 1; i++) {
                handler.getObject().get(i).setShow(true);
            }
        }
        for (int i = 1; i < handler.getObject().size(); i++) {
            if (handler.getObject().get(PLAYER).checkCollision(handler.getObject().get(i))) {
                handler.getObject().get(i).setIsCollision(true);
            } else {
                handler.getObject().get(i).setIsCollision(false);
            }
        }

        if (handler.getObject().get(PLAYER).getX() == 0) {
            handler.getObject().get(PLAYER).showMsg(playerMsg, 300, Color.BLACK, MSG_POSITION,Fonts.getBitFont(30));
        }

        if (keyPressed == KeyEvent.VK_D) {
            handler.getObject().get(PLAYER).setxVel(1);
            backGround.setScrollX(5);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(-2);
            }
            if (handler.getObject().get(PLAYER).getX() >= 1050) {
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for (int i = 1; i < handler.getObject().size(); i++) {
                    handler.getObject().get(i).setxVel(0);
                }
            }
        } else if (keyPressed == KeyEvent.VK_A) {
            handler.getObject().get(PLAYER).setxVel(-1);
            backGround.setScrollX(-5);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(2);
            }
            if (handler.getObject().get(PLAYER).getX() <= 0) {
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for (int i = 1; i < handler.getObject().size(); i++) {
                    handler.getObject().get(i).setxVel(0);
                }
            }
        } else {
            handler.getObject().get(PLAYER).setxVel(0);
            backGround.setScrollX(0);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(0);
            }
        }
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.newState(GameStateManager.OPTION_STATE);
        }
        if (k == KeyEvent.VK_ENTER) {
            bgm.stop();
            gsm.setState(GameStateManager.MENU_STATE);
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == keyPressed) { // only assign -1 to pressedKey if no other key is pressed to chane the pressedKey value
            keyPressed = -1;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (handler.getObject().get(1).getIsCollision()) {
            handler.getObject().get(1).setClicked(true);
        }
        if (handler.getObject().get(2).getIsCollision()) {
            gsm.newState(GameStateManager.CLICK_GAME);
        }
        if (handler.getObject().get(3).getIsCollision()) {
            gsm.newState(GameStateManager.SCRATCH_GAME);
        }
        if (handler.getObject().get(4).getIsCollision()) {
            gsm.newState(GameStateManager.PUZZLE_GAME);
        }
        if (handler.getObject().get(5).getIsCollision()) {
            gsm.newState(GameStateManager.LEVEL2_STATE);
        }

    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
