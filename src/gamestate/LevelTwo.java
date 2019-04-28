package gamestate;

import game.Game;
import game.Handler;
import gameobject.ObjectID;
import gameobject.Player;
import java.awt.Graphics;
import scene.BackGround;

import java.awt.event.KeyEvent;
import scene.Camera;
import scene.ParallaxBackGround;
import scene.Snow;
import scene.Texture;

public class LevelTwo extends GameState {

    private final int PLAYER = 0;
    private final int BACKGROUND2 = 3;
    private final int FARMOUNTAIN = 4;
    private final int NEARMOUNTAINS = 5;
    private final int ROAD = 6;
    
    private BackGround background2;
    private ParallaxBackGround farMountain;
    private ParallaxBackGround nearMountain;
    private ParallaxBackGround road;
    private Handler handler;
    private int keyPressed;
    private Camera cam;
    private Snow[] snow;

    public static LevelTwo LevelTwo;

    public  LevelTwo getInstance(){
        if(LevelTwo == null){
            LevelTwo = new LevelTwo(GameStateManager.getInstance());
        }
        return LevelTwo;
    }

    public LevelTwo(GameStateManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init() {
        snow  = new Snow[200];
        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Snow();
        }
        handler = new Handler();
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

        handler.addObject(new Player(0, Game.HEIGHT / 2+50, ObjectID.PLAYER,5));
        handler.addObject(new Player(-200, Game.HEIGHT / 2+50, ObjectID.PLAYER,5));
        
        cam = new Camera(0, 0,400);
    }

    @Override
    public void tick() {
        event();
        for (int i = 0; i < snow.length; i++) {
            snow[i].tick();
        }
        handler.tick();
        background2.tick();
        nearMountain.tick();
        farMountain.tick();
        road.tick();
        cam.tick(handler.getObject().get(PLAYER));
    }

    

    @Override
    public void render(Graphics g) {
        
        background2.render(g);
        farMountain.render(g);
        nearMountain.render(g);
        road.render(g);
        
        g.translate(cam.getX(), cam.getY()); //begin of cam
        handler.render(g);
        
        g.translate(-cam.getX(), -cam.getY());//end of cam
        for (int i = 0; i < snow.length; i++) {
            snow[i].redner(g);
        }

        
    }

    @Override
    public void event(){
//        System.out.println("Player x: " +handler.getObject().get(PLAYER).getX());//pirnt 角色x
        handler.getObject().get(PLAYER).setxVel(1);
        handler.getObject().get(1).setxVel(1);
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
      
        
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void mouseReleased(int x) {

    }
}
