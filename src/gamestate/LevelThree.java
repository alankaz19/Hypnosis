/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import game.Game;
import game.Handler;
import gameobject.ObjectID;
import gameobject.Player;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import resourcemanage.SoundResource;
import scene.AudioManager;
import scene.BackGround;
import scene.Texture;
import uiobject.Fonts;
import uiobject.HintBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class LevelThree extends GameState{
    
    private final int PLAYER = 0;
    private final int NPC = 1;
    private Handler handler;
    private int keyPressed;
    private ArrayList<String> playerScript;
    private ArrayList<String> npcScript;
    private int msgC, npcC;  //script line;
    private volatile int time;
    private BufferedReader playerScriptReader, npcScriptReader;
    private volatile  boolean sceneFinished;
    private AudioClip bgm;
    private BufferedImage background;
    
    private int velocity;
    private int velocity2;
    private int timer;
    private int timer2;


    public static LevelThree LEVEL_THREE;
    
    @Override
    public  LevelThree getInstance(){
        if(LEVEL_THREE == null){
            LEVEL_THREE = new LevelThree(GameStateManager.getInstance());
        }
        return LEVEL_THREE;
    }

    public LevelThree(GameStateManager gsm){
        super(gsm);
        init();
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_TWO_BACKGROUND].stop();
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_THREE_BACKGROUND].loop();
    }

    @Override
    public void init(){
        handler = new Handler();
        playerScript = new ArrayList<>();
        npcScript = new ArrayList<>();
        sceneFinished = false;
        background = Texture.getInstance().background[14];
        //read script
        try {
            playerScriptReader = new BufferedReader(new InputStreamReader( new FileInputStream("PlayerScene3.txt"), StandardCharsets.UTF_16));
            npcScriptReader = new BufferedReader(new InputStreamReader( new FileInputStream("NpcScene3.txt"), StandardCharsets.UTF_16));
            while(playerScriptReader.ready()){
                playerScript.add(playerScriptReader.readLine());
            }
            while(npcScriptReader.ready()){
                npcScript.add(npcScriptReader.readLine());
            }
            playerScriptReader.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        //end
        bgm = SoundResource.getInstance().getClip("/Art/Sound Effect/Level3.wav");
        bgm.loop();
        
        handler.addObject(new Player((((Game.WIDTH / 5) * 1) - 64), (Game.HEIGHT * 50 / 110), ObjectID.ENDGAMEPLAYER,5));
        handler.addObject(new Player((((Game.WIDTH / 5) * 3) + 64), (Game.HEIGHT * 50 / 110), ObjectID.ENDGAMENPC,5));
    }

    @Override
    public void tick() {
        event();
        handler.tick();
        
    }

    

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        this.fadeIn(g ,0.007f);
        g.drawImage(background, 0 , 0, 1280, 720, null);
        handler.render(g);
        g.drawImage(Texture.getInstance().background[12], 0 , 0, 1280, 720, null);
            
        handler.getObject().get(PLAYER).renderMsg(g);
        handler.getObject().get(NPC).renderMsg(g);
        
        
    }

    @Override
    public void event(){
        
        //talking event;
        time++;
        //System.out.println(npcScript.size());
        if(time == 140 &&  npcC < npcScript.size() && !sceneFinished){ //PLAYER message timer
            if(!npcScript.get(npcC).equals("")){
                System.out.println("npc Line " + npcC);
                handler.getObject().get(NPC).showMsg(npcScript.get(npcC), 580, Color.BLACK,-70,Fonts.getBitFont(18));// npc message
            }
            if(npcC < npcScript.size()){
                npcC ++;
                System.out.println(npcC);
            }
        }
        //System.out.println(playerScript.size());
        if(time == 140 && msgC < playerScript.size() && !sceneFinished){ //NPC message timer
            System.out.println("player Line " + msgC);
            if(!playerScript.get(msgC).equals("")){
                handler.getObject().get(PLAYER).showMsg(playerScript.get(msgC), 580, Color.BLACK,-70,Fonts.getBitFont(18)); //player message
            }
           
            
            if(msgC < playerScript.size()){
                msgC ++;
                System.out.println(msgC);
            }
            time = 0;
            
        
        }
        
       
            
            if(msgC == 6 || msgC == 13 || msgC == 19){
                timer++;
                timer2++;
                if(timer2 > 3 ) {
                    velocity2 = 1;
                    handler.getObject().get(PLAYER).setX(handler.getObject().get(PLAYER).getX() - velocity2);
                    handler.getObject().get(NPC).setX(handler.getObject().get(NPC).getX() + velocity2);

                    System.out.println(handler.getObject().get(PLAYER).getX());
                    timer2 = 0;
                }
                if(timer < 100 ) {
                    velocity =1;
                    
                    handler.getObject().get(PLAYER).setX(handler.getObject().get(PLAYER).getX() + velocity);
                    handler.getObject().get(NPC).setX(handler.getObject().get(NPC).getX() - velocity);
                    System.out.println(handler.getObject().get(PLAYER).getX());
                    timer = 0;
                }
            }else{
                velocity = 0;
            }
        
        if(npcC == npcScript.size() && msgC == playerScript.size()){  //finished with animated scene
            sceneFinished = true;
        }

        if(time >= 145 && sceneFinished){ //move one to next stage
            bgm.stop();
            gsm.newState(GameStateManager.LEVEL2_STATE);
        }
        //finished talking event
        
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
        if(k == keyPressed ){ // only assign -1 to pressedKey if no other key is pressed to change the pressedKey value
            keyPressed = -1;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
//        gsm.newState(GameStateManager.HYPNOSIS_TRANSITION);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        this.setMousePos(x, y);
    }
}

