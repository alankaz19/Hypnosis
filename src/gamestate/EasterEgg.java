package gamestate;

import game.Game;
import gameobject.*;
import resourcemanage.SoundResource;
import scene.BackGround;
import scene.ParallaxBackGround;
import scene.Snow;
import scene.Texture;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EasterEgg extends GameState {
    private final int BACKGROUND2 = 3;
    private final int FARMOUNTAIN = 4;
    private final int NEARMOUNTAINS = 5;
    private final int ROAD = 6;
    private Keys key;
    private BackGround background2;
    private ParallaxBackGround farMountain;
    private ParallaxBackGround nearMountain;
    private ParallaxBackGround road;
    private Snow[] snow;
    private AudioClip bgm;
    private ArrayList<Floor> floor;
    private ActionPlayer player;
    private Npc doppelganger;
    public static EasterEgg EasterEgg;


    protected EasterEgg(GameStateManager gsm) {
        super(gsm);
        init();
        //bgm.loop();
    }

    @Override
    public EasterEgg getInstance() {
        if(EasterEgg == null){
            EasterEgg = new EasterEgg(GameStateManager.getInstance());
        }
        return EasterEgg;
    }
    public class Floor extends GameObject {
        private BufferedImage img;
        public Floor(int x, int y, ObjectID id) {
            super(x, y, id);
            img = Texture.getInstance().floor[0];
            width = 128;
            height = 32;
        }
        @Override
        public void tick() {

        }
        @Override
        public void render(Graphics g) {
            g.drawImage(img,x,y,128,32,null);
        }
    }

    @Override
    public void init() {
        snow  = new Snow[200];
        //bgm = SoundResource.getInstance().getClip("/Art/BackGround/Level2.wav");
        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Snow();
        }
        background2 = new BackGround(BACKGROUND2){
            @Override
            public void render(Graphics g) {
                g.drawImage(Texture.getInstance().background[BACKGROUND2],0,0, Game.WIDTH, Game.HEIGHT, null);
            }
        };
        //inputMethod
        key = new Keys();
        //background
        farMountain = new ParallaxBackGround(Texture.getInstance().background[FARMOUNTAIN],1);
        nearMountain = new ParallaxBackGround(Texture.getInstance().background[NEARMOUNTAINS],1);
        road  = new ParallaxBackGround(Texture.getInstance().background[ROAD],0);
        floor = new ArrayList<>();
        //player
        player = new ActionPlayer(276,450,ObjectID.PLAYER,5);
        player.setHeight(player.getHeight()/2);
        player.setWidth(player.getWidth()/2);
        //enemy
        doppelganger = new Npc(0,50,ObjectID.OBSTACLE,3, player);
        //steps
        floor.add(new Floor(276,450,ObjectID.OBSTACLE));
        floor.add(new Floor(576,300,ObjectID.OBSTACLE));
        floor.add(new Floor(896,450,ObjectID.OBSTACLE));
        for(int i = 0; i < Game.WIDTH; i+=128){
            floor.add(new Floor(i,666,ObjectID.OBSTACLE));
        }
    }

    @Override
    public void tick() {
        event();
        key.update();
        player.tick();
        doppelganger.tick();
        for(int i = 0; i < floor.size(); i++){
            floor.get(i).tick();
        }
        for (Snow snow1 : snow) {
            snow1.tick();
        }
    }

    @Override
    public void event() {
        player.checkBorder();
        for(int i = 0 ; i < floor.size(); i ++){
            //TOP COLLISION
            if(player.getTop().intersects(floor.get(i).getBound())){
                player.setY(floor.get(i).getY() +34);
                player.setyVel(0);
            }
            //BOT COLLISION
            if(player.getBot().intersects(floor.get(i).getBound())){
                player.setY(floor.get(i).getY() - player.getHeight());
                player.setyVel(0);
                player.setFalling(false);
                player.setJumping(false);
            }else{
                player.setFalling(true);
            }
            //RIGHT COLLISION
            if(player.getRight().intersects(floor.get(i).getBound())){
                player.setX(floor.get(i).getX() - player.getWidth());;
            }
            //LEFT COLLISION
            if(player.getLeft().intersects(floor.get(i).getBound())){
                player.setX(floor.get(i).getX() + floor.get(i).getWidth());
            }
        }
        //ENEMY COLLISION
        if(player.getBound().intersects(doppelganger.getBound())){
            player.setIsCollision(true);
        }
        //KEYLISTENER
        if(key.keyState[key.LEFT]){
            player.setxVel(-5);
        }else if(key.keyState[key.RIGHT]){
            player.setxVel(5);
        }else{
            player.setxVel(0);
        }
        if(key.keyState[key.SPACE] && !player.isJumping()){
            if(player.isFalling()){
                player.setyVel(-25);
                player.setJumping(true);
            }
        }
        //END
        doppelganger.dive();
    }
    @Override
    public void render(Graphics g) {
        background2.render(g);
        farMountain.render(g);
        nearMountain.render(g);
        //road.render(g);
        doppelganger.render(g);
        player.render(g);
        for(int i = 0; i < floor.size(); i++){
            floor.get(i).render(g);
        }
        for (Snow snow1 : snow) {
            snow1.redner(g);
        }
        //debug
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
//        for(int i = 0; i< floor.size(); i++){
//            g2d.draw(floor.get(i).getBound());
//        }
        g2d.draw(doppelganger.getBound());
        g2d.draw(player.getTop());
        g2d.draw(player.getBot());
        g2d.draw(player.getRight());
        g2d.draw(player.getLeft());

    }

    @Override
    public void keyPressed(int k) {
        key.keySet(k, true);

    }

    @Override
    public void keyReleased(int k) {
        key.keySet(k,false);


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
    private class Keys {

        public static final int NUM_KEYS = 16;

        public boolean[] keyState = new boolean[NUM_KEYS];
        public boolean[] prevKeyState = new boolean[NUM_KEYS];


        public  final int LEFT = 0;
        public  final int RIGHT = 1;
        public  final int ENTER = 2;
        public  final int ESCAPE = 3;
        public  final int ONE = 4;
        public  final int TWO = 5;
        public  final int SPACE = 6;


        public  void keySet(int i, boolean b) {
            if(i == KeyEvent.VK_A) keyState[LEFT] = b;
            if(i == KeyEvent.VK_D) keyState[RIGHT] = b;
            if(i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
            if(i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
            if(i == KeyEvent.VK_1) keyState[ONE] = b;
            if(i == KeyEvent.VK_2) keyState[TWO] = b;
            if(i == KeyEvent.VK_SPACE) keyState[SPACE] = b;
        }

        public  void update() {
            for(int i = 0; i < NUM_KEYS; i++) {
                prevKeyState[i] = keyState[i];
            }
        }
        public  boolean isPressed(int i) {
            return keyState[i] && !prevKeyState[i];
        }

        public  boolean anyKeyPress() {
            for(int i = 0; i < NUM_KEYS; i++) {
                if(keyState[i]) return true;
            }
            return false;
        }

    }
}
