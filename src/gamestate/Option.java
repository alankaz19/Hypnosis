package gamestate;

import game.Game;
import scene.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Option extends GameState {
    private BufferedImage img;

    protected Option(GameStateManager gsm) {
        super(gsm);
        this.img = Texture.getInstance().background[2];
    }

    @Override
    public Option getInstance() {
        return null;
    }

    @Override
    public void init() {
        
    }

    @Override
    public void tick() {

    }

    @Override
    public void event() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,408,84, 883,695, 378,64,913,695,null);

    }

    @Override
    public void keyPressed(int k) {
    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mousePressed(int x, int y) {
        //返回遊戲
        if(x >= 512 && x <= 822 && y >= 365 && y <= 410){
            gsm.setState(gsm.getPrevState());
        }
        //返回主選單
        if(x >= 512 && x <= 822 && y >= 640 && y <= 685){
            gsm.setState(gsm.MENU_STATE);
        }

    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
