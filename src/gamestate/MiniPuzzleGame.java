package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;
import resourcemanage.ImageResource;
import scene.BackGround;
import scene.PaintUtil;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class MiniPuzzleGame extends GameState {
    private static final BufferedImage ORIGINAL = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
    private BackGround fakeBackground;
    Pieces[] pieces;
    Board gameBoard;
    private int keyPressed, selection;

    //constructor
    protected MiniPuzzleGame(GameStateManager gsm) {
        super(gsm);
        init();
    }
    //end

    // class
    class Pieces extends GameObject{
        private BufferedImage img;
        protected boolean correctPos;
        public Pieces(int x, int y, ObjectID id, int r, int c) {
            super(x,y,id);
            img = ORIGINAL.getSubimage(100 * r ,100 * c,100,100);
            correctPos = false;
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
    //end

    // Board Class
    class Board extends GameObject{
        BufferedImage img;
        Pieces[] pieces;
        boolean [] checkMap;
        public Board(int x, int y, ObjectID id, Pieces[] pieces) {
            super(x, y, id);
            img = ImageResource.getInstance().getImage("/Art/Game Material/puzzleBoard.png");
            this.pieces = pieces;
            checkMap = new boolean[9];


        }

        private boolean checkBoard(){
            int count = 0;
            for(int i = 0; i < 9; i++){
                if(pieces[i].correctPos){
                    count ++;
                }
                //error check
                System.out.println(i + "  " + pieces[i].correctPos);
            }
           if(count == 9){
               return true;
           }
           //error check
           System.out.println(count);
           return false;
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.drawImage(img,x,y,300,300,null);

        }

        @Override
        public ObjectID getID() {
            return null;
        }
    }
    //end

    @Override
    public GameState getInstance() {
        return null;
    }

    @Override
    public void init() {
        selection = 0;
        fakeBackground = new BackGround(1);
        pieces = new Pieces[9];
        //int random = (int)(Math.random()*9);
        int x = 0,y = 0, r = 0, c = 0, count = 1;
        for(int i = 0; i < 9; i++){
            int random = (int)(Math.random()*9);
            if(count % 3 == 1){
                c = 0;
            }
            if(count % 3 == 2){
                c = 1;
            }
            if(count % 3 == 0){
                c = 2;
            }
            pieces[i] = new Pieces(random * 75,random * 50,ObjectID.PUZZLE,r,c);
            if(count % 3 == 0){
                r++;
            }
            count++;
            x +=30;
            y +=30;
        }
        gameBoard = new Board(700,240,ObjectID.BOARD, pieces);

    }

    @Override
    public void tick() {
        event();

    }

    @Override
    public void event() {

    }

    @Override
    public void render(Graphics g) {
        fakeBackground.render(g);
        gameBoard.render(g);
        for( int i = 0; i < 9; i ++){
            pieces[i].render(g);
        }
        PaintUtil.paintFocus((Graphics2D) g, new Rectangle(pieces[selection].x,pieces[selection].y,100,100),6);
        //g.setColor(Color.black);
        //g.drawRect(pieces[selection].x,pieces[selection].y,100,100);
    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        if( keyPressed == KeyEvent.VK_A){
            if(selection != 0){
                selection -= 1;
            }
        }
        if( keyPressed == KeyEvent.VK_D){
            if(selection != 8){
                selection += 1;
            }
        }
        if(keyPressed == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
        if(keyPressed == KeyEvent.VK_ENTER){
            System.out.println(gameBoard.checkBoard());
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
        int r = 100, c = 100;
        if(x >= gameBoard.x && x <= gameBoard.x + r && y >= gameBoard.y && y <= gameBoard.y + c){
            if(selection == 0){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x;
            pieces[selection].y = gameBoard.y;

        }else if(x >= gameBoard.x + r && x <= gameBoard.x + (2 * r) && y >= gameBoard.y && y <= gameBoard.y + c){
            if(selection == 3){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x + r;
            pieces[selection].y = gameBoard.y;

        }else if(x >= gameBoard.x + (2 * r) && x <= gameBoard.x + (3 * r) && y >= gameBoard.y && y <= gameBoard.y + c){
            if(selection == 6){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x + (2 * r);
            pieces[selection].y = gameBoard.y;

        }else if(x >= gameBoard.x && x <= gameBoard.x + r && y >= gameBoard.y + c && y <= gameBoard.y + (2 * c)){
            if(selection == 1){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x;
            pieces[selection].y = gameBoard.y + c;

        }else if(x >= gameBoard.x + r && x <= gameBoard.x + (2 * r) && y >= gameBoard.y + c && y <= gameBoard.y + (2 * c)){
            if(selection == 4){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x + r;
            pieces[selection].y = gameBoard.y + c;

        }else if(x >= gameBoard.x + (2 * r) && x <= gameBoard.x + (3 * r) && y >= gameBoard.y + c && y <= gameBoard.y + (2 * c)){
            if(selection == 7){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x + (2 * r);
            pieces[selection].y = gameBoard.y + c;

        }else if(x >= gameBoard.x && x <= gameBoard.x + r && y >= gameBoard.y + (2 * c) && y <= gameBoard.y + (3 * c)){
            if(selection == 2){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x;
            pieces[selection].y = gameBoard.y + (2 * c);

        }else if(x >= gameBoard.x + r && x <= gameBoard.x + (2 * r) && y >= gameBoard.y + (2 * c) && y <= gameBoard.y + (3 * c)){
            if(selection == 5){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x + r;
            pieces[selection].y = gameBoard.y + (2 * c);

        }else if(x >= gameBoard.x + (2 * r) && x <= gameBoard.x + (3 * r) && y >= gameBoard.y + (2 * c) && y <= gameBoard.y + (3 * c)){
            if(selection == 8){
                pieces[selection].correctPos = true;
            }else{
                pieces[selection].correctPos = false;
            }
            pieces[selection].x = gameBoard.x + (2 * r);
            pieces[selection].y = gameBoard.y + (2 * c);

        }else{
            pieces[selection].x = x;
            pieces[selection].y = y;
        }

    }

    @Override
    public void mouseDragged(int x, int y) {
//        int deltaX = x - piece1.x;
//        int deltaY = y - piece1.y;

        //piece1.setPosition(piece1.screenX + deltaX, piece1.screenY + deltaY);
    }
}
