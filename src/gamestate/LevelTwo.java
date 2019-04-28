package gamestate;

import game.Game;
import gameobject.Npc;
import gameobject.ObjectID;
import gameobject.Player;

import java.awt.*;

import gameobject.items.Obstacle;
import scene.BackGround;

import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import scene.Camera;
import scene.ParallaxBackGround;
import scene.Snow;
import scene.Texture;

public class LevelTwo extends GameState {

    private final int BACKGROUND2 = 3;
    private final int FARMOUNTAIN = 4;
    private final int NEARMOUNTAINS = 5;
    private final int ROAD = 6;
    
    private BackGround background2;
    private ParallaxBackGround farMountain;
    private ParallaxBackGround nearMountain;
    private ParallaxBackGround road;
    private ParallaxBackGround grassGround;
    private Player player;
    private Npc doppelganger;
    private ArrayList<Obstacle> obstacleList;
    private int keyPressed;
    private Camera cam;
    private Snow[] snow;
    private int timeC, spawnT, camPos;

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
        timeC = 0;
        spawnT = 0;
        camPos = 400;
        snow  = new Snow[200];
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
        player = (new Player(0, Game.HEIGHT / 2+50, ObjectID.PLAYER,5));
        player.setxVel(1);
        player.setyVel(1);
        player.setHeight(128);
        player.setWidth(64);
        //npc
        doppelganger = new Npc(-400,Game.HEIGHT / 2+75,ObjectID.OBSTACLE,3, player);
        doppelganger.setxVel(1);
        //obstacle
        obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(1000,Game.HEIGHT / 2+140, ObjectID.OBSTACLE,"/Art/Game Material/obstacle.png"));
        //obstacle = new Obstacle(800,Game.HEIGHT / 2+140, ObjectID.DOOR,"/Art/Game Material/obstacle.png");

        cam = new Camera(0, 0,camPos);
    }

    @Override
    public void tick() {
        event();
        for (Snow snow1 : snow) {
            snow1.tick();
        }
        player.tick();
        doppelganger.tick();
        obstacleList.get(0).tick();
        background2.tick();
        nearMountain.tick();
        farMountain.tick();
        road.tick();
        cam.tick(player);
    }

    

    @Override
    public void render(Graphics g) {
        background2.render(g);
        farMountain.render(g);
        nearMountain.render(g);
        road.render(g);
        
        g.translate(cam.getX(), cam.getY()); //begin of cam
        player.render(g);
        doppelganger.render(g);

        if(!obstacleList.isEmpty())
        obstacleList.get(0).render(g);

        g.translate(-cam.getX(), -cam.getY());//end of cam
        for (Snow snow1 : snow) {
            snow1.redner(g);
        }
        grassGround.render(g);

    }

    @Override
    public void event(){
        timeC++;

        //NPC CHARGE
        if(timeC++ >=300 && (doppelganger.getX() <=doppelganger.getX() + 1000)){
            if(doppelganger.getxVel() <= 7){
                doppelganger.setxVel(doppelganger.getxVel()+1);
            }
        }
        //END

        //CollisionCheck
        if(((player.checkCollision(obstacleList.get(0))) || player.checkCollision(doppelganger)) && !player.getIsCollision()){
            player.setIsCollision(true);
            System.out.println("COLLAPSE");
        }

        //NPC RESPAWN
        if(doppelganger.getX() >= player.getX()+ 1000){
            doppelganger.setxVel(1);
            spawnT ++;
            if(spawnT > 100){
                doppelganger.setX(player.getX()-400);
                timeC = 0;
                spawnT = 0;
            }
        }
        //END

        if(obstacleList.get(0).getX() <= player.getX() - 400){
            obstacleList.remove(0);
        }
        if(obstacleList.isEmpty()){
            obstacleList.add(new Obstacle(player.getX()+ 1000,Game.HEIGHT / 2+140, ObjectID.DOOR,"/Art/Game Material/obstacle.png"));
            player.setIsCollision(false);
        }

        //Player Jump
        if(keyPressed == KeyEvent.VK_SPACE && player.getyVel() != 30 && !player.isJumping()){
            player.setyVel(-27);
            player.setJumping(true);
        }else if(player.getY() < 410 || player.getY() == 350){
            player.setyVel(player.getyVel() + 2);

        }else{
            player.setJumping(false);
        }
        //END

    }



    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.newState(GameStateManager.OPTION_STATE);
        }
        if(keyPressed == KeyEvent.VK_ENTER){
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
