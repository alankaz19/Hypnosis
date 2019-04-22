package gamestate;

import game.Game;
import gameobject.GameObject;
import gameobject.ObjectID;
import resourcemanage.ImageResource;
import scene.BackGround;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class PuzzleGame extends GameState {
    private static final BufferedImage ORIGINAL = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
    private BackGround fakeBackground;
    private Pieces piece1, piece2, piece3, piece4, piece5, piece6, piece7, piece8, piece9;
    private int keyPressed, selection;

    protected PuzzleGame(GameStateManager gsm) {
        super(gsm);
        init();
    }


    class Pieces extends GameObject{
        BufferedImage img;

        public Pieces(int x, int y, ObjectID id, int r, int c) {
            super(x,y,id);
            img = ORIGINAL.getSubimage(100 * r ,100 * c,100,100);
        }

        @Override
        public void tick() {

        }

        public void render(Graphics g) {
            g.drawImage(img,x,y,100,100,null);

        }

        @Override
        public ObjectID getID() {
            return null;
        }


    }


    @Override
    public GameState getInstance() {
        return null;
    }

    @Override
    public void init() {
     piece1 = new Pieces(0,0,ObjectID.PAINT1,0,0);
     piece2 = new Pieces(0,100,ObjectID.PAINT1,0,1);
     piece3 = new Pieces(0,200,ObjectID.PAINT1,0,2);
     piece4 = new Pieces(100,0,ObjectID.PAINT1,1,0);
     piece5 = new Pieces(100,100,ObjectID.PAINT1,1,1);
     piece6 = new Pieces(100,200,ObjectID.PAINT1,1,2);
     piece7 = new Pieces(200,0,ObjectID.PAINT1,2,0);
     piece8 = new Pieces(200,100,ObjectID.PAINT1,2,1);
     piece9 = new Pieces(200,200,ObjectID.PAINT1,2,2);
     fakeBackground = new BackGround(1);

    }

    @Override
    public void tick() {
        event();

    }

    @Override
    public void event() {
        if(keyPressed == KeyEvent.VK_D){
            if(selection == KeyEvent.VK_1){
                piece1.tick();
            }
            if(selection == KeyEvent.VK_2){
                piece2.tick();
            }
        }

    }

    @Override
    public void render(Graphics g) {
        fakeBackground.render(g);
        piece1.render(g);
        piece2.render(g);
        piece3.render(g);
        piece4.render(g);
        piece5.render(g);
        piece6.render(g);
        piece7.render(g);
        piece8.render(g);
        piece9.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_1 || k == KeyEvent.VK_2
                || k == KeyEvent.VK_3 || k == KeyEvent.VK_4
                || k == KeyEvent.VK_5 || k == KeyEvent.VK_6
                || k == KeyEvent.VK_7 || k == KeyEvent.VK_8
                || k == KeyEvent.VK_9){
            selection = k;
        }
        keyPressed = k;
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }

    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mousePressed(int x, int y) {
        if(selection == KeyEvent.VK_1){
            piece1.x = x;
            piece1.y = y;
        }
        if(selection == KeyEvent.VK_2){
            piece2.x = x;
            piece2.y = y;
        }
        if(selection == KeyEvent.VK_3){
            piece3.x = x;
            piece3.y = y;
        }
        if(selection == KeyEvent.VK_4){
            piece4.x = x;
            piece4.y = y;
        }
        if(selection == KeyEvent.VK_5){
            piece5.x = x;
            piece5.y = y;
        }
        if(selection == KeyEvent.VK_6){
            piece6.x = x;
            piece6.y = y;
        }
        if(selection == KeyEvent.VK_7){
            piece7.x = x;
            piece7.y = y;
        }
        if(selection == KeyEvent.VK_8){
            piece8.x = x;
            piece8.y = y;
        }
        if(selection == KeyEvent.VK_9){
            piece9.x = x;
            piece9.y = y;
        }

//        piece1.screenX = piece1.getX();
//        piece1.screenY = piece1.getY();

    }

    @Override
    public void mouseDragged(int x, int y) {
//        int deltaX = x - piece1.x;
//        int deltaY = y - piece1.y;

        //piece1.setPosition(piece1.screenX + deltaX, piece1.screenY + deltaY);
    }
}
