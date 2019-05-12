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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import resourcemanage.SoundResource;
import scene.BackGround;
import scene.Texture;
import uiobject.Fonts;
import uiobject.HintBox;

/**
 *
 * @author Kai
 */
public class Intro extends GameState{
    
    private final int PLAYER = 0;
    private final int HYPNOTIST = 1;
    private final int BACKGROUND = 9;
    private BackGround background;
    private Handler handler;
    private int keyPressed;
    private ArrayList<String> playerScript;
    private ArrayList<String> npcScript;
    private int msgC, npcC;  //script line;
    private volatile int time;
    private BufferedReader playerScriptReader, npcScriptReader;
    private volatile  boolean sceneFinished;
    private AudioClip bgm;
    private HintBox file;


    public static Intro INTRO;
    
    @Override
    public  Intro getInstance(){
        if(INTRO == null){
            INTRO = new Intro(GameStateManager.getInstance());
        }
        return INTRO;
    }

    public Intro(GameStateManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init(){
        handler = new Handler();
        playerScript = new ArrayList<>();
        npcScript = new ArrayList<>();
        sceneFinished = false;
        file = new HintBox(6,50,0,500,700);
        //read script
        try {
            playerScriptReader = new BufferedReader(new InputStreamReader( new FileInputStream("PlayerScene1.txt"), StandardCharsets.UTF_16));
            npcScriptReader = new BufferedReader(new InputStreamReader( new FileInputStream("NpcScene1.txt"), StandardCharsets.UTF_16));
            while(playerScriptReader.ready()){
                playerScript.add(playerScriptReader.readLine());
            }
            while(npcScriptReader.ready()){
                npcScript.add(npcScriptReader.readLine());
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        //end
        bgm = SoundResource.getInstance().getClip("/Art/Sound Effect/IntroMusic.wav");
        bgm.loop();
        background = new BackGround(BACKGROUND){
            public void render(Graphics g) {
                g.drawImage(Texture.getInstance().background[BACKGROUND],0,0,Game.WIDTH, Game.HEIGHT, null);
            }
        };
        handler.addObject(new Player((Game.WIDTH * 45 / 100 ), (Game.HEIGHT * 50 / 100), ObjectID.INTROPLAYER,5));
        handler.addObject(new Player((Game.WIDTH * 66 / 100 ), (Game.HEIGHT * 50 / 100), ObjectID.SHRINK,5));
    }

    @Override
    public void tick() {
        event();
        handler.tick();
        background.tick();
    }

    

    @Override
    public void render(Graphics g) {
        this.fadeIn(g ,0.007f);
        background.render(g);
        
        handler.render(g);

        handler.getObject().get(PLAYER).renderMsg(g);
        handler.getObject().get(HYPNOTIST).renderMsg(g);
        
        file.fadeIn(g ,0.007f);
    }

    @Override
    public void event(){
        time++;
        //System.out.println(npcScript.size());
        if(time == 150 &&  npcC < npcScript.size() && !sceneFinished){ //PLAYER message timer
            if(!npcScript.get(npcC).equals("")){
                System.out.println("npc Line " + npcC);
                handler.getObject().get(HYPNOTIST).showMsg(npcScript.get(npcC), 800, Color.BLACK,0,Fonts.getBitFont(20));// npc message
            }
            if(npcC < npcScript.size()){
                npcC ++;
                System.out.println(npcC);
            }
        }
        //System.out.println(playerScript.size());
        if(time == 150 && msgC < playerScript.size() && !sceneFinished){ //NPC message timer
            System.out.println("player Line " + msgC);
            if(!playerScript.get(msgC).equals(""))
            handler.getObject().get(PLAYER).showMsg(playerScript.get(msgC), 800, Color.BLACK,0,Fonts.getBitFont(20)); //player message
            if(msgC < playerScript.size()){
                msgC ++;
                System.out.println(msgC);
            }
            time = 0;
        }

        if(npcC == npcScript.size() && msgC == playerScript.size()){  //finished with animated scene
            sceneFinished = true;
        }

        if(time >= 50 && sceneFinished){ //move one to next stage
            bgm.stop();
            gsm.newState(GameStateManager.HYPNOSIS_TRANSITION);
        }
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

