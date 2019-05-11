/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import game.Handler;
import gameobject.GameObject;
import gameobject.ObjectID;
import gameobject.items.Light;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import scene.Texture;

/**
 *
 * @author alank
 */
public class LevelOneScene extends GameState{
    public static LevelOneScene LEVEL1_SCENE;
    Handler handler;
    Handler lightHandler;
    int keyPressed;

    int timeC;
    
    
    //private BufferedImage background;

    public class Paint extends GameObject {
        BufferedImage img;
        protected float alpha;

        public Paint(int x, int y, ObjectID id,BufferedImage img,float alpha) {
            super(x, y, id);
            this.img = img;
            this.width = 120;
            this.height = 120;
            this.alpha = alpha;
        }    
        
        public void setOpacity(Graphics2D g2d){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
        }
        
        @Override
        public void tick() {
            if(this.alpha <= 0.99f && this.alpha >= 0){
                this.alpha += 0.016f;
            }
            else if(this.alpha >= 0.99f){
                this.alpha = 1.0f;
                this.setShow(true);
            }
        }
        
        @Override
        public void render(Graphics g) {
            setOpacity((Graphics2D)g);
            g.drawImage(img, x, y, width, height, null);
        }
    }
    
    public LevelOneScene(GameStateManager gsm) {
        super(gsm);
    }
    
    @Override
    public GameState getInstance() {
        if(LEVEL1_SCENE == null){
            LEVEL1_SCENE = new LevelOneScene(GameStateManager.getInstance());
        }
        return LEVEL1_SCENE;
    }

    @Override
    public void init() {
        handler = new Handler();
        lightHandler = new Handler();
        //background = Texture.getInstance().background[8];
        timeC = 0;
        
        //背景
        handler.addObject(new Paint(0 ,0 ,ObjectID.PICTURE ,Texture.getInstance().background[8], 0){
            
            @Override
            public void tick() {
                if(this.alpha <= 0.99f && this.alpha >= 0){
                    this.alpha += 0.01;
                }
                else if(this.alpha >= 0.99f){
                    this.alpha = 1.0f;
                    this.setShow(true);
                }
            }
            
            @Override
            public void setWidth(int width) {
                this.width = 1280;
            }
            
            @Override
            public void setHeight(int height) {
                this.height = 720;
            }
        });
        handler.getObject().get(0).setWidth(1280);
        handler.getObject().get(0).setHeight(720);
        //結束背景設定
        
        handler.addObject(new Paint(120 ,280 ,ObjectID.PICTURE ,Texture.getInstance().paintThumbnail[0], 0));
        handler.addObject(new Paint(440 ,280 ,ObjectID.PICTURE ,Texture.getInstance().paintThumbnail[1], 0));
        handler.addObject(new Paint(760 ,280 ,ObjectID.PICTURE ,Texture.getInstance().paintThumbnail[2], 0));
        handler.addObject(new Paint(1080 ,280 ,ObjectID.PICTURE ,Texture.getInstance().paintThumbnail[3], 0));
        handler.getObject().get(0).setShow(true);
        
        //add lights
        lightHandler.addObject(new Light(121 ,200, ObjectID.LIGHT));
        lightHandler.addObject(new Light(441 ,200, ObjectID.LIGHT));
        lightHandler.addObject(new Light(761 ,200, ObjectID.LIGHT));
        lightHandler.addObject(new Light(1081 ,200, ObjectID.LIGHT));
    }

    @Override
    public void tick() {
        handler.getObject().get(0).tick();
        lightHandler.tick();
        event();
    }

    @Override
    public void event() {
        for (int i = 0; i < handler.getObject().size() - 1; i++) {
            if(handler.getObject().get(i).isShow()){
                handler.getObject().get(i + 1).tick();
            }
        }
        if(handler.getObject().get(1).isShow() && handler.getObject().get(2).isShow() && handler.getObject().get(3).isShow()){
            timeC ++;
            if(timeC >= 180)
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        
        
        //第一幅畫 沙發 櫃子
        handler.getObject().get(0).render(g);
        g.drawImage(Texture.getInstance().closet[0], 900, 400,121,121, null);
        
        //暗角
        g.drawImage(Texture.getInstance().background[10], 0, 0,Game.WIDTH,Game.HEIGHT, null);
        
        //lights
        lightHandler.render(g);
        
        //畫
        for (int i = 0; i < handler.getObject().size() - 1; i++) {
            if(handler.getObject().get(i).isShow()){
                handler.getObject().get(i + 1).render(g);
            }
            
        }
        
        
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.setState(gsm.getPrevState());
        }
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
            gsm.setState(gsm.getPrevState());
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {

    }

}
