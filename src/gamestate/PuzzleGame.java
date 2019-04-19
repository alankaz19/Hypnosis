package gamestate;

import resourcemanage.ImageResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PuzzleGame extends GameState {
    Puzzle pieces;

    protected PuzzleGame(GameStateManager gsm) {
        super(gsm);
        init();
    }

    private class Puzzle extends JComponent {
        private static final int WIDTH = 300, HEIGHT = 300;
        private BufferedImage original;
        private BufferedImage [] puzzleBoard;
        private volatile int screenX = 0;
        private volatile int screenY = 0;
        private volatile int myX = 0;
        private volatile int myY = 0;


        private Puzzle(){
            init();

        }

        private void init(){
            original = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
            puzzleBoard = new BufferedImage[9];
            puzzleBoard[0] = original.getSubimage(0,0,100,100);
            puzzleBoard[1] = original.getSubimage(100,0,100,100);
            puzzleBoard[2] = original.getSubimage(200,0,100,100);
            puzzleBoard[3] = original.getSubimage(0,100,100,100);
            puzzleBoard[4] = original.getSubimage(100,100,100,100);
            puzzleBoard[5] = original.getSubimage(200,100,100,100);
            puzzleBoard[6] = original.getSubimage(0,200,100,100);
            puzzleBoard[7] = original.getSubimage(100,200,100,100);
            puzzleBoard[8] = original.getSubimage(200,200,100,100);
        }

        public void render(Graphics g){
            int random = (int)(Math.random()*5);
            g.drawImage(puzzleBoard[0], 0,0,100,100,null);
            g.drawImage(puzzleBoard[1], 100,0,100,100,null);
            g.drawImage(puzzleBoard[2], 200,100,100,100,null);
            g.drawImage(puzzleBoard[3], 0,200,100,100,null);
            g.drawImage(puzzleBoard[4], 500,100,100,100,null);
            g.drawImage(puzzleBoard[5], 200,300,100,100,null);
            g.drawImage(puzzleBoard[6], 0,200,100,100,null);
            g.drawImage(puzzleBoard[7], 100,200,100,100,null);
            g.drawImage(puzzleBoard[8], 300,200,100,100,null);
        }

    }

    @Override
    public GameState getInstance() {
        return null;
    }

    @Override
    public void init() {
        pieces = new Puzzle();
    }

    @Override
    public void tick() {

    }

    @Override
    public void event() {

    }

    @Override
    public void render(Graphics g) {
        pieces.render(g);

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
