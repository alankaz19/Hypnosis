/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import scene.BackGround;
import scene.Texture;
import uiobject.DialogBox;
import uiobject.Button;

/**
 *
 * @author Kai
 */
public class MiniScratchOffGame extends GameState {

    public static MiniScratchOffGame MINI_SCRATCH_GAME;
    
    
    private class Mask extends GameObject {

        BufferedImage img;
        private boolean cleared;
        private float alpha;

        public Mask(int x, int y, ObjectID id) {
            super(x, y, id);
            img = Texture.getInstance().paint[4];
            cleared = false;
            this.width = 300;
            this.height = 400;
            this.alpha = 1f;
        }

        public void renderMask(Graphics2D g2d) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
            
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            this.renderMask((Graphics2D) g);
            g.drawImage(Texture.getInstance().paint[4], x, y, null);
            
            
            
        }

        public void setCleared(boolean cleared) {
            this.cleared = cleared;
        }

    }

    private class Paint extends GameObject {

        private boolean Visible;

        public Paint(int x, int y, ObjectID id) {
            super(x, y, id);
            Visible = false;
            this.width = 300;
            this.height = 400;
        }

        @Override
        public void tick() {
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(Texture.getInstance().paint[5], x, y, null);
        }

        public void setVisible(boolean Visible) {
            this.Visible = Visible;
        }
    }

    private Paint paint;
    private Mask mask;
    private DialogBox hint;
    private Button exitButton;
    private boolean isDone;
    private BackGround fakeBackground;
    private int keyPressed;

    public MiniScratchOffGame(GameStateManager gsm) {
        super(gsm);
        this.init();
    }

    @Override
    public MiniScratchOffGame getInstance() {
        if (MINI_SCRATCH_GAME == null) {
            MINI_SCRATCH_GAME = new MiniScratchOffGame(GameStateManager.getInstance());
        }
        return MINI_SCRATCH_GAME;
    }

    @Override
    public void init() {
        mask = new Mask(600, 202, ObjectID.FRAME);
        paint = new Paint(600, 200, ObjectID.PICTURE_IN_PUZZLE2);
        hint = new DialogBox();
        exitButton = new Button();
        isDone = false;
        fakeBackground = new BackGround(1);

    }

    @Override
    public void tick() {
        mask.tick();

    }

    @Override
    public void event() {
    }

    @Override
    public void render(Graphics g) {
        fakeBackground.render(g);
        paint.render(g);
        mask.render(g);
    //        hint.render();
    //        exitButton.render();
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if (keyPressed == KeyEvent.VK_ESCAPE) {
            gsm.newState(GameStateManager.LEVEL1_STATE);
        }

    }

    @Override
    public void keyReleased(int k) {
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
