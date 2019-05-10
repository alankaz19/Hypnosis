package gamestate;

import game.Game;
import game.Handler;
import game.Updater;
import gameobject.GameObject;
import gameobject.ObjectID;
import gameobject.Player;
import gameobject.items.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import scene.BackGround;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import resourcemanage.SoundResource;
import scene.Camera;
import scene.Texture;
import uiobject.HintBox;

public class LevelOne extends GameState {
    //躁點
    private class Noise implements Updater{
        float alpha = 0.2f;
        double x = (Math.random() * Game.WIDTH);
        double y = (Math.random() * Game.HEIGHT);
        double yVel = 10;
        double length;
        int colorNumber = 3;

        public void setLength(double length) {
            this.length = length;
        }
        
        @Override
        public void tick(){
            length = 700;
            y += yVel;
            if(y + length > Game.HEIGHT){
                y = -100;
                x = (Math.random() * Game.WIDTH);
            }
        }
    
        @Override
        public void render(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
            switch((int)(Math.random() * 4)){
                case 1:
                    g.setColor(java.awt.Color.blue);
                    break;
                case 2:
                    g.setColor(java.awt.Color.gray);
                    break;
                case 3:
                    g.setColor(java.awt.Color.red);
                    break;
            }
            
            g.drawLine((int)x, (int)y, (int)x, (int)(y+ length) );
            
        }
    
    }
    
    //放大鏡 碎片
    private boolean hasMagnifier;
    private boolean MagnifierHintShowed;
    private boolean hasJigsaw;
    private boolean JigsawHintShowed;
    private int hintTimer;
    
    
    private final int PLAYER = 0;
    private final int PICTURE1 = 1;
    private final int MSG_POSITION = 0;
    private Noise[] noise;
    private Handler handler;
    private BackGround backGround;
    private int keyPressed;
    private Camera cam;
    private String playerMsg;
    boolean sceneshowed;
    private AudioClip bgm;
    private HintBox magnifierHint;
    private HintBox jigsawHint;
    

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
        noise  = new Noise[5];
        magnifierHint = new HintBox(0);
        hintTimer = 0;
        jigsawHint = new HintBox(1);
        for (int i = 0; i < noise.length; i++) {
            noise[i] = new Noise();
        }
        bgm = SoundResource.getInstance().getClip("/Art/Sound Effect/Level1.wav");
        sceneshowed = false;
        hintTimer = 0;
        handler = new Handler();
        backGround = new BackGround(1);
        cam = new Camera(0, 0, 400);
        playerMsg = " 利用WASD鍵來移動 ···";
        handler.addObject(new Player(0, Game.HEIGHT / 2, ObjectID.PLAYER, 15));
        handler.addObject(new Bar(500, 320, ObjectID.BAR));
        handler.addObject(new Picture(600, 0, ObjectID.PICTURE){
            @Override
            public void render(Graphics g) {
                this.renderRotate(g,10);
                if(this.show){
                    if(clickable){
                        g.drawImage(Texture.getInstance().paintThumbnail[0], x, y,width,height, null);
                        this.getShining().renderAnimation(g, x, y, width,height);
                    }else{
                        g.drawImage(Texture.getInstance().paintThumbnail[0], x, y,width,height, null);
                    }
                }
            }
        });
        handler.addObject(new Picture(600 + 600 ,1 , ObjectID.PICTURE));
        handler.addObject(new Closet(1600 ,442 ,ObjectID.PICTURE));
        handler.addObject(new Picture(600 + 1380 ,2 , ObjectID.PICTURE));
        handler.addObject(new Picture(600 + 1980 ,3 , ObjectID.PICTURE));
        handler.addObject(new Door(800 + 2160, ObjectID.DOOR));
        handler.addObject(new Sofa(-100,400, ObjectID.DOOR));
    }

    @Override
    public void tick() {
        event();
        for (Noise noise : noise) {
            noise.tick();
        }
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
        
        
        
        
        g.translate(cam.getX(), cam.getY()); //begin of cam
        handler.render(g);
        handler.getObject().get(PLAYER).renderMsg(g); // message following character head
        g.translate(-cam.getX(), -cam.getY());//end of cam
        g.drawImage(Texture.getInstance().background[10], 0, 0,Game.WIDTH,Game.HEIGHT, null);
        
        if(hasMagnifier){
            hintTimer++;
            if(!magnifierHint.isShowed() && hintTimer < 200){
                magnifierHint.render(g);
            }else if(hintTimer > 120){
                magnifierHint.setShowed(true);
                magnifierHint.fadeOut(g);
            }
        }
        for (Noise noise : noise) {
            noise.render(g);
        }
        
        
    }

    @Override
    public void event() {
//        System.out.println("Player x: " + handler.getObject().get(PLAYER).getX());//pirnt 角色x
        
        //場景事件觸發
        if(handler.getObject().get(2).isShow()){
            if(!sceneshowed){
                gsm.newState(GameStateManager.LEVEL1_SCENE);
                sceneshowed = true;
            }
            for (int i = 2; i < handler.getObject().size() - 1; i++) {
                handler.getObject().get(i).setShow(true);
            }
        }
        
        //碰撞事件
        for (int i = 1; i < handler.getObject().size(); i++) {
            if (handler.getObject().get(PLAYER).LevelOneCheckCollision(handler.getObject().get(i))) {
                handler.getObject().get(i).setIsCollision(true);
            } else {
                handler.getObject().get(i).setIsCollision(false);
            }
        }
        
        
        //moving event start----
        if (keyPressed == KeyEvent.VK_D) {
            handler.getObject().get(PLAYER).setxVel(2);
            backGround.setScrollX(10);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(-4);
            }
            if (handler.getObject().get(PLAYER).getX() >= 1000) {
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for (int i = 1; i < handler.getObject().size(); i++) {
                    handler.getObject().get(i).setxVel(0);
                }
            }
        } else if (keyPressed == KeyEvent.VK_A) {
            handler.getObject().get(PLAYER).setxVel(-2);
            backGround.setScrollX(-10);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(4);
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
        //moving event End----
        
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
        //拉霸 第一幅畫旋轉後轉場
        if (handler.getObject().get(1).getIsCollision()) {
            handler.getObject().get(1).setClicked(true);
            handler.getObject().get(1).setClickable(false);
            handler.getObject().get(2).setClicked(true);
        }
        
        //第一幅畫
        if (handler.getObject().get(2).getIsCollision()) {
            gsm.newState(GameStateManager.FIRST_PICTURE);
        }
        
        //第二幅畫
        if (handler.getObject().get(3).getIsCollision() && handler.getObject().get(3).isShow()) {
            gsm.newState(GameStateManager.CLICK_GAME);
            
        }
        
        //櫃子
        if (handler.getObject().get(4).getIsCollision()) {
            System.out.println("closet clicked");
            hasMagnifier = true;
        }
        //
        
        
        //第三幅畫
        if (handler.getObject().get(5).getIsCollision() && handler.getObject().get(5).isShow() && hasMagnifier) {
            this.hasJigsaw = true;
            gsm.newState(GameStateManager.SCRATCH_GAME);
        }
        
        //第四幅畫
        if (handler.getObject().get(6).getIsCollision() && handler.getObject().get(6).isShow() && hasJigsaw) {
                gsm.newState(GameStateManager.PUZZLE_GAME);
                
        }
        
        //門
        if (handler.getObject().get(7).getIsCollision()) {
            gsm.newState(GameStateManager.NUMBER_LOCK);
        }

    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }
}
