package gamestate;

import game.Game;
import game.Handler;
import game.Updater;
import gameobject.ObjectID;
import gameobject.Player;
import gameobject.items.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import scene.AudioManager;
import scene.BackGround;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import resourcemanage.SoundResource;
import scene.Camera;
import scene.Texture;
import uiobject.HintBox;
import uiobject.Cursor;

public class LevelOne extends GameState {
    //躁點
    private class Noise implements Updater{
        float alpha = 0.18f;
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
                case 4:
//                    g.setColor(java.awt.Color.white);
                    break;
            }
            
            g.drawLine((int)x, (int)y, (int)x, (int)(y+ length) );
            
        }
    
    }
    
    //放大鏡 碎片
    private boolean hasMagnifier;
    private boolean hasJigsaw;
    private BufferedImage jigsaw;
    private BufferedImage magnifier;
    private int hintTimer;// 提示展示時間
    
    //完成小遊戲判斷
    private boolean paint1Done;
    private boolean paint2Done;
    private boolean paint3Done;
    private boolean paint4Done;
    
    private final int PLAYER = 0;
    private Noise[] noise;
    private Handler handler;
    private Handler lightHandler;
    private BackGround backGround;
    private int keyPressed;
    private Camera cam;
    boolean sceneshowed;
    private HintBox magnifierHint;
    private Cursor eyes;
    private boolean eyeShow;
    

    public static LevelOne LevelOne;

    public LevelOne getInstance() {
        if (LevelOne == null) {
            LevelOne = new LevelOne(GameStateManager.getInstance());
        }
        return LevelOne;
    }

    public LevelOne(GameStateManager gsm) {
        super(gsm);
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_ONE_BACKGROUND].loop();
        init();
    }

    @Override
    public void init() {
        
        
        //放大鏡提示
        magnifierHint = new HintBox(0);
        hintTimer = 0;
        
        //畫面雜訊
        noise  = new Noise[5];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = new Noise();
        }
        
        backGround = new BackGround(1);
        sceneshowed = false;
        handler = new Handler();
        lightHandler = new Handler();
        cam = new Camera(0, 0, 400);
        
        //遊戲物件
        handler.addObject(new Player(0, Game.HEIGHT / 2, ObjectID.PLAYER, 15));
        handler.addObject(new Bar(450, 320, ObjectID.BAR));
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
        handler.addObject(new Picture(600 + 650 ,1 , ObjectID.PICTURE));
        handler.addObject(new Closet(2220 ,442 ,ObjectID.PICTURE));
        handler.addObject(new Picture(600 + 1300 ,2 , ObjectID.PICTURE));
        handler.addObject(new Picture(600 + 1950 ,3 , ObjectID.PICTURE));
        handler.addObject(new Door(800 + 2160, ObjectID.DOOR));
        handler.addObject(new Sofa(-100,400, ObjectID.DOOR));
        
        //燈光物件
        lightHandler.addObject(new Light(610 ,ObjectID.LIGHT));
        lightHandler.addObject(new Light(610 + 650 ,ObjectID.LIGHT));
        lightHandler.addObject(new Light(610 + 1300 ,ObjectID.LIGHT));
        lightHandler.addObject(new Light(610 + 1950 ,ObjectID.LIGHT));
        
        //道具圖示
        jigsaw = Texture.getInstance().item[0];
        magnifier = Texture.getInstance().item[1];
        
        //眼睛游標
        eyes = new Cursor();
    }

    @Override
    public void tick() {
        event();
        for (Noise noise : noise) {
            noise.tick();
        }
        backGround.tick();
        handler.tick();
        lightHandler.tick();
        eyes.tick();
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
        lightHandler.render(g);
        g.translate(-cam.getX(), -cam.getY());//end of cam
        
        if(eyeShow){
            eyes.render(g);
        }
        
        //暗角
        g.drawImage(Texture.getInstance().background[10], 0, 0,Game.WIDTH,Game.HEIGHT, null);
        
        //道具與提示
        if(hasJigsaw && !paint4Done){
            g.drawImage(jigsaw, 1100, 580, 100 , 100, null);
        }
        if(hasMagnifier){
            hintTimer++;
            g.drawImage(magnifier, 1000, 580, 100 , 100, null);
            if(!magnifierHint.isShowed() && hintTimer < 80){
                magnifierHint.render(g);
            }else if(hintTimer > 40){
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
            for (int i = 1; i < handler.getObject().size() - 1; i++) {
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
            for (int i = 0; i < lightHandler.getObject().size(); i++) {
                lightHandler.getObject().get(i).setxVel(-4);
            }
            if (handler.getObject().get(PLAYER).getX() >= 1000) {
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for (int i = 1; i < handler.getObject().size(); i++) {
                    handler.getObject().get(i).setxVel(0);
                }
                for (int i = 0; i < lightHandler.getObject().size(); i++) {
                lightHandler.getObject().get(i).setxVel(0);
            }
            }
        } else if (keyPressed == KeyEvent.VK_A) {
            handler.getObject().get(PLAYER).setxVel(-2);
            backGround.setScrollX(-10);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(4);
            }
            for (int i = 0; i < lightHandler.getObject().size(); i++) {
                lightHandler.getObject().get(i).setxVel(4);
            }
            if (handler.getObject().get(PLAYER).getX() <= 0) {
                handler.getObject().get(PLAYER).setxVel(0);
                backGround.setScrollX(0);
                for (int i = 1; i < handler.getObject().size(); i++) {
                    handler.getObject().get(i).setxVel(0);
                }
                for (int i = 0; i < lightHandler.getObject().size(); i++) {
                lightHandler.getObject().get(i).setxVel(0);
                }
            }
        } else {
            handler.getObject().get(PLAYER).setxVel(0);
            backGround.setScrollX(0);
            for (int i = 1; i < handler.getObject().size(); i++) {
                handler.getObject().get(i).setxVel(0);
            }
            for (int i = 0; i < lightHandler.getObject().size(); i++) {
                lightHandler.getObject().get(i).setxVel(0);
            }
        }
        //moving event End----
        
        //更換完成縮圖
        if(paint2Done) {
            Picture tempPic = (Picture)handler.getObject().get(3);
            tempPic.setState(5);
        }
        
//        if(paint3Done) {
//            Picture tempPic = (Picture)handler.getObject().get(5);
//            tempPic.setState(6);
//        }
        
        if(paint4Done) {
            Picture tempPic = (Picture)handler.getObject().get(6);
            tempPic.setState(4);
        }
        
        //眼睛游標
        this.showEyes();
        
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.newState(GameStateManager.OPTION_STATE);
        }
        if (k == KeyEvent.VK_ENTER) {
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
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_ONE_CLICK].play();

        //拉霸 第一幅畫旋轉後轉場
        if (handler.getObject().get(1).getIsCollision()) {
            handler.getObject().get(1).setClicked(true);
            handler.getObject().get(1).setClickable(false);
            handler.getObject().get(2).setClicked(true);
        }
        
        //第一幅畫
        if (handler.getObject().get(2).getIsCollision() && handler.getObject().get(2).isShow() &&!paint1Done) {
            gsm.newState(GameStateManager.FIRST_PICTURE);
            this.paint1Done = true;
        }else if(handler.getObject().get(2).getIsCollision() && handler.getObject().get(2).isShow() && paint1Done){
            gsm.setState(GameStateManager.FIRST_PICTURE);
        }
        
        //第二幅畫
        if (handler.getObject().get(3).getIsCollision() && handler.getObject().get(3).isShow() && !paint2Done) {
            gsm.newState(GameStateManager.CLICK_GAME);
            this.paint2Done = true;
        }else if(handler.getObject().get(3).getIsCollision() && handler.getObject().get(3).isShow() && paint2Done){
            gsm.setState(GameStateManager.CLICK_GAME);
        }
        
        //櫃子
        if (handler.getObject().get(4).getIsCollision()) {
            handler.getObject().get(4).setClicked(true);
            hasMagnifier = true;
        }
        //
        
        
        //第三幅畫
        if(handler.getObject().get(5).getIsCollision() && handler.getObject().get(5).isShow() && !hasMagnifier && !paint3Done){
            gsm.newState(GameStateManager.THIRD_PICTURE);
        }
        else if (handler.getObject().get(5).getIsCollision() && handler.getObject().get(5).isShow() && hasMagnifier && !paint3Done) {
            gsm.newState(GameStateManager.SCRATCH_GAME);
            this.hasJigsaw = true;
            this.paint3Done = true;
        }else if(handler.getObject().get(5).getIsCollision() && handler.getObject().get(5).isShow() && hasMagnifier && paint3Done){
            gsm.setState(GameStateManager.SCRATCH_GAME);
        }
        
        //第四幅畫
        if(handler.getObject().get(6).getIsCollision() && handler.getObject().get(5).isShow() && !hasJigsaw && !paint3Done){
            gsm.newState(GameStateManager.FORTH_PICTURE);
        }
        else if (handler.getObject().get(6).getIsCollision() && handler.getObject().get(6).isShow() && hasJigsaw && !paint4Done) {
            gsm.newState(GameStateManager.PUZZLE_GAME);
            this.paint4Done = true;
        }else if(handler.getObject().get(6).getIsCollision() && handler.getObject().get(6).isShow() && hasMagnifier && paint4Done){
            gsm.setState(GameStateManager.PUZZLE_GAME);
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
        this.eyes.setPosition(x, y);
    }
    
    private void showEyes(){
        eyeShow = false;
        if(handler.getObject().get(1).getIsCollision() && !handler.getObject().get(1).isClicked()){
            eyeShow = true;
        }
        for (int i = 2; i < handler.getObject().size(); i++) {
            if (handler.getObject().get(PLAYER).LevelOneCheckCollision(handler.getObject().get(i))) {
                if(handler.getObject().get(i).isShow()){
                    eyeShow = true;
                }
            }
        }
        
        
         if(handler.getObject().get(4).getIsCollision() && handler.getObject().get(4).isClicked()){
            eyeShow = false;
        }
    }
}
