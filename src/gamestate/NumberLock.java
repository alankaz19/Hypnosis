/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import resourcemanage.ImageResource;
import scene.Texture;
import uiobject.Button;

/**
 *
 * @author alank
 */
public class NumberLock extends GameState {
    
    private class Slot extends GameObject{
        private Button up;
        private Button down;
        private int currentNumber;
        
        private Slot(int x, int y, ObjectID id){
            super(x,y,id);
            this.up = new Button(this.x + 30, 37, 64, 64, Texture.getInstance().arrowButton[0],Texture.getInstance().arrowButton[1],4);
            this.down = new Button(this.x + 30, 388, 64, 64, Texture.getInstance().arrowButton[2],Texture.getInstance().arrowButton[3],4);
            this.currentNumber = 1;
            this.up.setTouchable(true);
            this.down.setTouchable(true);
        }

        public int getCurrentNumber() {
            return currentNumber;
        }

        @Override
        public void tick() {
            if(up.isClicked() && currentNumber < 9){
                currentNumber++;
            }
            if(down.isClicked() && currentNumber > 1){
                currentNumber--;
            }
            up.tick();
            down.tick();
        }

        @Override
        public void render(Graphics g) {
            up.render(g);
            down.render(g);
            g.drawImage(Texture.getInstance().lockNumber[currentNumber], x, y,64*5,64*5, null);
        }
    }
    
    private int keyPressed;
    public static NumberLock NUMBER_LOCK;
    private BufferedImage fakeBackground;
    private BufferedImage lockFrame;
    private boolean isDone;
    private Button exit;
    private Slot[] slots;
    

    public NumberLock(GameStateManager gsm) {
        super(gsm);
        this.init();
    }
    
    @Override
    public GameState getInstance() {
        if(NUMBER_LOCK == null){
            NUMBER_LOCK = new NumberLock(GameStateManager.getInstance());
        }
        return NUMBER_LOCK;
    }

    @Override
    public void init() {
        fakeBackground = Texture.getInstance().background[7];
        lockFrame = ImageResource.getInstance().getImage("/Art/Game Material/LockFrame.png");
        slots = new Slot[3];
        slots[0] = new Slot(230,180,ObjectID.SLOT);
        slots[1] = new Slot(472,180,ObjectID.SLOT);
        slots[2] = new Slot(710,180,ObjectID.SLOT);
        exit = new Button(1025,523,200,100,Texture.getInstance().button[1],Texture.getInstance().button[0],1);
    }

    @Override
    public void tick() {
        for (Slot slot : slots) {
            slot.tick();
        }
        exit.tick();
        event();
    }

    @Override
    public void event() {
        if(slots[0].getCurrentNumber() == 7 && slots[1].getCurrentNumber() == 2 && slots[2].getCurrentNumber() == 5){
            this.isDone = true;
        }
        if (this.mouseX >= exit.getX()&& this.mouseX <= exit.getX() + exit.getWidth() && this.mouseY >= exit.getY() && this.mouseY <= exit.getY() + exit.getHeight()) {
            exit.setHovered(true);
        }else{
            exit.setHovered(false);
        }
    }

    @Override
    public void render(Graphics g) {
        this.fadeIn(g);
        g.drawImage(fakeBackground, 0, 0, null);
        g.drawImage(lockFrame, 250, 200,256*3,96*3, null);
        
        for (Slot slot : slots) {
            slot.render(g);
        }
        if(this.isDone){
            exit.render(g);
        }
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k == keyPressed){
            keyPressed = -1;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(!isDone){
            if(x > 318 && x < 318+64*3 && y > 116 && y < 116 + 64 * 2){
            slots[0].up.setClicked(true);
            }
            if(x > 548 && x < 548+64*3 && y > 116 && y < 116 + 64 * 2){
                slots[1].up.setClicked(true);
            }
            if(x > 798 && x < 798+64*3 && y > 116 && y < 116 + 64 * 2){
                slots[2].up.setClicked(true);
            }

            if(x > 318 && x < 318+64*3 && y > 470 && y < 470 + 64 * 2){
                slots[0].down.setClicked(true);
            }
            if(x > 548 && x < 548+64*3 && y > 470 && y < 470 + 64 * 2){
                slots[1].down.setClicked(true);
            }
            if(x > 798 && x < 798+64*3 && y > 470 && y < 470 + 64 * 2){
                slots[2].down.setClicked(true);
            }
        }
        
        if(exit.isHovered()){
            gsm.newState(GameStateManager.TRANSITION_2);
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x,int y) {
        for (int i = 0; i < 3; i++) {
            slots[i].up.setReleased(true);
            slots[i].down.setReleased(true);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        this.setMousePos(x, y);
    }

}
