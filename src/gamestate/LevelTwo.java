package gamestate;

import game.Game;
import gameobject.*;

import java.applet.AudioClip;
import game.Updater;
import gameobject.Npc;
import gameobject.ObjectID;
import gameobject.Player;
import java.awt.*;
import gameobject.items.Obstacle;
import resourcemanage.SoundResource;
import scene.BackGround;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import scene.Camera;
import scene.ParallaxBackGround;
import scene.Texture;
public class LevelTwo extends GameState {

    private final int BACKGROUND2 = 3;
    private final int FARMOUNTAIN = 4;
    private final int NEARMOUNTAINS = 5;
    private final int ROAD = 6;
    
    private BackGround background2;
    private BufferedImage endScene;
    private ParallaxBackGround farMountain;
    private ParallaxBackGround nearMountain;
    private ParallaxBackGround road;
    private ActionPlayer player;
    private int keyPressed;
    private Camera cam;
    private Snow[] snow;
    private int timeC, spawnT, camPos, lifeCount;

    public static LevelTwo LevelTwo;
    
    private class Snow implements Updater{
    double x = Math.random() * Game.WIDTH;
    double y = (Math.random() *-500) +50;
    double yVel = Math.random() * 2.5 ;
    double xVel = Math.random() * 1 ;
    
    @Override
    public void tick(){
        y += yVel;
        x -= xVel;
        if( y > Game.HEIGHT){
            y = Math.random() * -400;
        }
        if(x < 0){
            x = Game.WIDTH;
        }
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(java.awt.Color.white);
        g.drawLine((int)x, (int)y, (int)x, (int)y+3);
    }
}


    public  LevelTwo getInstance(){
        if(LevelTwo == null){
            LevelTwo = new LevelTwo(GameStateManager.getInstance());
        }
        return LevelTwo;
    }

    public LevelTwo(GameStateManager gsm){
        super(gsm);
        init();
//        bgm.loop();
    }

    @Override
    public void init() {
        timeC = 0;
        spawnT = 0;
        camPos = 400;
        lifeCount = 5;
        endScene = Texture.getInstance().background[13];
        snow  = new Snow[200];
//        bgm = SoundResource.getInstance().getClip("/Art/BackGround/Level2.wav");
        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Snow();
        }
        background2 = new BackGround(BACKGROUND2){
             @Override
            public void render(Graphics g) {
                g.drawImage(Texture.getInstance().background[BACKGROUND2],0,0,Game.WIDTH, Game.HEIGHT, null);
            }
        };
        farMountain = new ParallaxBackGround(Texture.getInstance().background[FARMOUNTAIN],1);
        farMountain.setVector(-0.1, 0);
        nearMountain = new ParallaxBackGround(Texture.getInstance().background[NEARMOUNTAINS],1);
        nearMountain.setVector(-0.2, 0);
        road  = new ParallaxBackGround(Texture.getInstance().background[ROAD],0);
        road.setVector(-4, 0);

        //player
        player = (new ActionPlayer(0, Game.HEIGHT-170, ObjectID.ENDSCENE,5));
        player.setxVel(1);
        player.setHeight(128);
        player.setWidth(128);
        player.setFalling(false);
        cam = new Camera(0, 0,camPos);
    }


    @Override
    public void tick() {
        event();
        for (Snow snow1 : snow) {
            snow1.tick();
        }
        player.tick();
        background2.tick();
        nearMountain.tick();
        farMountain.tick();
        road.tick();
        cam.tick(player);
    }

    

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g);
        background2.render(g);
        farMountain.render(g);
        nearMountain.render(g);
        road.render(g);
        
        g.translate(cam.getX(), cam.getY()); //begin of cam
        player.render(g);
        g.setColor(Color.WHITE);
        g.translate(-cam.getX(), -cam.getY());//end of cam
        for (Snow snow1 : snow) {
            snow1.render(g);
        }
        g.drawImage(endScene, 0,0,null);


    }

    @Override
    public void event(){
        timeC++;

        //END

    }


    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.newState(GameStateManager.OPTION_STATE);
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
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

    }
}
