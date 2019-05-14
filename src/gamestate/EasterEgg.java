package gamestate;

import game.Game;
import game.Updater;
import gameobject.*;
import resourcemanage.ImageResource;
import scene.AudioManager;
import scene.ParallaxBackGround;
import scene.Texture;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import scene.Animation;
import uiobject.HintBox;

public class EasterEgg extends GameState {
    private final int BACKGROUND2 = 3;
    private final int FARMOUNTAIN = 4;
    private final int NEARMOUNTAINS = 5;
    private Keys key;
    private BufferedImage background2, restart, heartHUD;
    private ParallaxBackGround farMountain;
    private ParallaxBackGround nearMountain;
    private Snow[] snow;
    private AudioClip collect;
    private ArrayList<Floor> floor;
    private ArrayList<NostaligaItem> itemList;
    private ActionPlayer player;
    private Npc doppelganger;
    private int keyPressed, npcDowntime;
    
    //操作提示
    private HintBox help;
    private int helpTimer;
    

    public static EasterEgg EasterEgg;


    protected EasterEgg(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public EasterEgg getInstance() {
        if(EasterEgg == null){
            EasterEgg = new EasterEgg(GameStateManager.getInstance());
        }
        return EasterEgg;
    }
    private class NostaligaItem extends GameObject{
        Texture tex = Texture.getInstance();
        private BufferedImage img;
        private Animation shining;
        private boolean falling, collectable;
        private int gravity;

        public NostaligaItem(int x, int y, ObjectID id, BufferedImage img) {
            super(x, y, id);
            this.img = img;
            width = 60;
            height = 60;
            gravity = 1;
            collectable = false;
            falling = true;
            shining = new Animation(8,tex.hearts[0] ,tex.hearts[1] ,tex.hearts[2] ,tex.hearts[3] ,tex.hearts[4] ,tex.hearts[5] ,tex.hearts[4] ,tex.hearts[3] ,tex.hearts[2] ,tex.hearts[1] ,tex.hearts[0]);
        }

        @Override
        public void setFalling(boolean falling) {
            this.falling = falling;
        }

        public boolean isCollectable() {
            return collectable;
        }

        public void setCollectable(boolean collectable) {
            this.collectable = collectable;
        }

        @Override
        public void tick() {
            x += xVel;
            y += yVel;
            if(falling || jumping){
                yVel += gravity;
                if(yVel > 5){
                    yVel = 5;
                }
            }
            this.shining.runAnimation();
        }

        @Override
        public void render(Graphics g) {
            this.shining.renderAnimation(g, x, y, width, height);
        }

        public Rectangle getBound(){
            return new Rectangle(x,y,width,height);
        }
    }
    private class Floor extends GameObject {
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
    private class Snow implements Updater{
        double x = Math.random() * Game.WIDTH;
        double y = (Math.random() *-500) +50;
        double yVel = Math.random() * 2.5 + 0.5 ;
        double xVel = Math.random() * 2 ;

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

    @Override
    public void init() {
        npcDowntime = 0;
        snow  = new Snow[200];
        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Snow();
        }
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_TWO_BACKGROUND].loop();
        //HUD
        heartHUD = ImageResource.getInstance().getImage("/Art/Game Material/heartHUD.png");
        //inputMethod
        key = new Keys();
        //background
        background2 = Texture.getInstance().background[BACKGROUND2];
        restart = ImageResource.getInstance().getImage("/Art/backGround/restart.png");
        farMountain = new ParallaxBackGround(Texture.getInstance().background[FARMOUNTAIN],1);
        nearMountain = new ParallaxBackGround(Texture.getInstance().background[NEARMOUNTAINS],1);
        collect = AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_TWO_COLLECT];
        //player
        player = new ActionPlayer(276,450,ObjectID.PLAYER,5);
        player.setHeight(player.getHeight()/2);
        player.setWidth(player.getWidth()/2);
        //enemy
        doppelganger = new Npc(0,50,ObjectID.OBSTACLE,3, player);
        //steps
        floor = new ArrayList<>();
        floor.add(new Floor(276,450,ObjectID.OBSTACLE));
        floor.add(new Floor(576,300,ObjectID.OBSTACLE));
        floor.add(new Floor(896,450,ObjectID.OBSTACLE));
        for(int i = 0; i < Game.WIDTH + 128; i+=128){ // add 128 because the lower right corner issue// Cannot jump
            floor.add(new Floor(i,666,ObjectID.OBSTACLE));
        }
        //item
        itemList = new ArrayList<>();
        itemList.add(new NostaligaItem(30,0,ObjectID.HEART,ImageResource.getInstance().getImage("/Art/Game Material/heart.png")));
        
        //hint
        help = new HintBox(9,Game.WIDTH /2 , 80, 500, 583);
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
        for (NostaligaItem item : itemList){
            item.tick();
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
                player.setX(floor.get(i).getX() - player.getWidth() + 33);
            }
            //LEFT COLLISION
            if(player.getLeft().intersects(floor.get(i).getBound())){
                player.setX(floor.get(i).getX() + floor.get(i).getWidth() - 23);
            }
            //NPC COLLISION with FLOOR
            if(doppelganger.npcExhausted() && doppelganger.getBot().intersects(floor.get(i).getBound())){
                doppelganger.setY(floor.get(i).getY() - doppelganger.getHeight()+ 15);
                doppelganger.setyVel(0);
                doppelganger.setFalling(false);
            }
            if(!player.playerDead() && player.getBot().intersects(floor.get(i).getBound())){
                if((key.keyState[key.SPACE] && !player.isJumping())||(key.keyState[key.UP] && !player.isJumping())){
                    player.setyVel(-24);
                    player.setJumping(true);
                    //player.showMsg("654654654", 1000, Color.red, 0,Fonts.getHorrorFont(30));
                }
            }

        }
        //Player/Enemy/Item COLLISION
        if(player.getBound().intersects(doppelganger.getBound()) && !doppelganger.getIsCollision()){
           player.setLifeC(player.getLifeC()-1);
        }
        //Item COLLISION
        if(!itemList.isEmpty()){
            for(int i = 0; i < itemList.size(); i ++){
                for(Floor floor: floor){
                    if(itemList.get(i).getBound().intersects(floor.getBound())){
                        itemList.get(i).setyVel(0);
                        itemList.get(i).setFalling(false);
                        itemList.get(i).setCollectable(true);
                    }
                }
                if(player.getBound().intersects(itemList.get(i).getBound())){
                    if(itemList.get(i).isCollectable()){
                        collect.play();
                        itemList.remove(i);
                        break;
                    }
                }
            }
        }
        //Item Generation && Enemy heart reduction
        if(itemList.isEmpty() && !doppelganger.npcExhausted()){
            doppelganger.getHeart().remove(doppelganger.getHeart().size() - 1);
            if(!doppelganger.npcExhausted()){ // Will not drop items after npc dead
                int randomLeft = (int)(Math.random()*Game.WIDTH/2);
                int randomRight = Game.WIDTH/2 + (int)((Math.random()*(Game.WIDTH - Game.WIDTH/2 - 50))); // minus 50 so the heart wont spawn at 1280
                if(player.getX() <= Game.WIDTH/2){
                    itemList.add(new NostaligaItem(randomRight,0,ObjectID.HEART,ImageResource.getInstance().getImage("/Art/Game Material/heart.png")));
                }else{
                    itemList.add(new NostaligaItem(randomLeft,0,ObjectID.HEART,ImageResource.getInstance().getImage("/Art/Game Material/heart.png")));
                }
            }
        }

        //KEYLISTENER
        if(!player.playerDead()){
            if(key.keyState[key.LEFT]){
                player.setxVel(-5);
            }else if(key.keyState[key.RIGHT]){
                player.setxVel(5);
            }else{
                player.setxVel(0);
            }

        }
        //END
        doppelganger.checkBorder();
        if(!doppelganger.npcExhausted() && !player.playerDead()){
            doppelganger.dive();
        }else if(doppelganger.npcExhausted()){
            npcDowntime ++;
            if(npcDowntime == 10){
                doppelganger.getMsg().showMsg(doppelganger.x, doppelganger.y, "OH SNAP!", 300, Color.red);
            }
            if(npcDowntime == 400){
                gsm.newState(GameStateManager.LEVEL3_STATE);
            }
        }
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        if(player.isDead() && this.alpha == 0){
            g.setColor(Color.black);
            g.drawImage(restart,0,0,null);
        }
        if(player.isDead()){
            this.fadeOut(g);
        }else{
            this.fadeIn(g);
        }
        g.drawImage(background2, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        farMountain.render(g);
        nearMountain.render(g);
        g.drawImage(heartHUD, Game.WIDTH/2+370, 10, null);
        doppelganger.render(g);
        player.render(g);

        for(Floor floor : floor){
            floor.render(g);
        }
        for (Snow snow1 : snow) {
            snow1.render(g);
        }
        
        for( NostaligaItem item : itemList){
            item.render(g);
        }
        
        //遊戲說明
        if(!help.isShowed() && helpTimer < 200){
                helpTimer++;
                help.fadeIn(g, 0.01f);
            }
        if(helpTimer == 200){
            help.fadeOut(g, 0.01f);
        }
        if(helpTimer == 250){
            helpTimer = 0;
            help.setShowed(true);
        }
        
        //debug
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.GREEN);
//        for(int i = 0; i< floor.size(); i++){
//            g2d.draw(floor.get(i).getBound());
//        }
//        for(int i = 0; i< itemList.size(); i++){
//            g2d.draw(itemList.get(i).getBound());
//        }
//        g2d.draw(doppelganger.getBot());
//        g2d.draw(player.getBound());
//        g2d.draw(player.getTop());
//        g2d.draw(player.getBot());
//        g2d.draw(player.getRight());
//        g2d.draw(player.getLeft());
//        player.renderMsg(g);
       
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        key.keySet(k, true);
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.newState(GameStateManager.OPTION_STATE);
        }
        if(keyPressed == KeyEvent.VK_ENTER && this.alpha == 0){
            gsm.newState(GameStateManager.EASTER_EGG);
        }
    }

    @Override
    public void keyReleased(int k) {
        key.keySet(k,false);
        if(k == keyPressed){
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

    private class Keys {
        static final int NUM_KEYS = 16;

        boolean[] keyState = new boolean[NUM_KEYS];
        boolean[] prevKeyState = new boolean[NUM_KEYS];


        final int LEFT = 0;
        final int RIGHT = 1;
        final int ENTER = 2;
        final int ESCAPE = 3;
        final int ONE = 4;
        final int TWO = 5;
        final int SPACE = 6;
        final int UP = 6;


        public  void keySet(int i, boolean b) {
            if(i == KeyEvent.VK_A) keyState[LEFT] = b;
            if(i == KeyEvent.VK_D) keyState[RIGHT] = b;
            if(i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
            if(i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
            if(i == KeyEvent.VK_1) keyState[ONE] = b;
            if(i == KeyEvent.VK_2) keyState[TWO] = b;
            if(i == KeyEvent.VK_SPACE) keyState[SPACE] = b;
            if(i == KeyEvent.VK_W) keyState[UP] = b;
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
