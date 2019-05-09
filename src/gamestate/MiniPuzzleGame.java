package gamestate;

import gameobject.GameObject;
import gameobject.ObjectID;
import resourcemanage.ImageResource;
import scene.BackGround;
import scene.PaintUtil;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import scene.Texture;
import uiobject.HintBox;


public class MiniPuzzleGame extends GameState {
    private static final BufferedImage ORIGINAL = ImageResource.getInstance().getImage("/Art/Game Material/SliderTest.png");
    private BufferedImage check;
    private BufferedImage exit;

    private BufferedImage fakeBackground;
    private BufferedImage frame;
    Pieces[] pieces;
    private Board gameBoard;
    private Pieces currentPiece;
    private int keyPressed;
    private HintBox hint;

    //constructor
    protected MiniPuzzleGame(GameStateManager gsm) {
        super(gsm);
        init();
    }
    //end

    // class
    class Pieces extends GameObject{
        private BufferedImage img;
        private boolean pos;
        private int order;
        private double centerX;
        private double centerY;
        protected Pieces(int x, int y, ObjectID id, int r, int c, int order) {
            super(x,y,id);
            img = ORIGINAL.getSubimage(100 * r ,100 * c,100,100);
            pos = false;
            this.order = order;
        }

        private boolean getPos(){
            return pos;
        }
        private void getCenter(){
            this.centerX = (this.x + this.width)/2;
            this.centerY = (this.y + this.height)/2;
        }
//        private int getCenterX(){
//            return this.centerX;
//        }
//        private int getCenterY(){
//            return this.centerY;
//        }
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
        boolean finished;
        public Board(int x, int y, ObjectID id, Pieces[] pieces) {
            super(x, y, id);
            img = ImageResource.getInstance().getImage("/Art/Game Material/puzzleBoard.png");
            this.pieces = pieces;
            finished = false;
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


    private boolean checkBoard(){
        int count = 0;
        for(int i = 0; i < 9; i++){
            if(pieces[i].pos){
                count ++;
            }
        }
        if(count == 9){
            gameBoard.finished = true;
            return true;
        }

        return false;
    }
    @Override
    public GameState getInstance() {
        return null;
    }

    @Override
    public void init() {
        fakeBackground = Texture.getInstance().background[7];
        frame = Texture.getInstance().paint[5];
        pieces = new Pieces[9];
        exit = Texture.getInstance().ui[1];
        hint = new HintBox();
        int x = 200,y = 200, r = 0, c = 0, count = 1;
        for(int i = 0; i < 9; i++){
            if(count % 3 == 1){
                c = 0;
            }
            if(count % 3 == 2){
                c = 1;
            }
            if(count % 3 == 0){
                c = 2;
            }
            pieces[i] = new Pieces(x,y,ObjectID.PUZZLE,r,c,i);
            x += 120;
            if(count % 3 == 0){
                r++;
                x = 200;
                y += 120;
            }
            count++;
        }
        gameBoard = new Board(700,240,ObjectID.BOARD, pieces);
        currentPiece = pieces[0];
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
        this.fadeIn(g);
        g.drawImage(fakeBackground, 0, 0, null);
        g.drawImage(frame, 635, 80, null);
        gameBoard.render(g);
        for( int i = 0; i < 9; i ++){
            pieces[i].render(g);
        }
        if(!gameBoard.finished){
            PaintUtil.paintFocus((Graphics2D) g, new Rectangle(currentPiece.x, currentPiece.y,100,100),6);
        }else{
            PaintUtil.paintFocus((Graphics2D) g, new Rectangle(gameBoard.getX(), gameBoard.getY(),300,300),6);
            g.drawImage(exit,1000,440,100,100,null);
            hint.showMsg(100, 200, 2000, "提示或劇情");
            hint.render(g);
        }


    }

    @Override
    public void keyPressed(int k) {
        keyPressed = k;
        getClosest(keyPressed);
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
        int r = 100, c = 100;
        for(int i = 0; i < 9; i ++){
            if (x >= pieces[i].x && x <= pieces[i].x + r && y >= pieces[i].y && y <= pieces[i].y + c) {
                currentPiece = pieces[i];
            }
        }
        if (checkBoard() && x >= 1000 && x <= 1240 && y >= 440 && y <= 540) {
            gsm.setState(GameStateManager.LEVEL1_STATE);
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(!currentPiece.pos){
            currentPiece.x = x - 50;
            currentPiece.y = y - 50;
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        int r = 100, c = 100;
        if(x >= gameBoard.getX() && x <= gameBoard.getX() + 300 && y >= gameBoard.getY() && y < gameBoard.getY() + 300) {
            if (x >= gameBoard.x && x <= gameBoard.x + r && y >= gameBoard.y && y <= gameBoard.y + c && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 0;
                currentPiece.x = gameBoard.x;
                currentPiece.y = gameBoard.y;
            }
            if (x >= gameBoard.x + r && x <= gameBoard.x + (2 * r) && y >= gameBoard.y && y <= gameBoard.y + c && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 3;
                currentPiece.x = gameBoard.x + r;
                currentPiece.y = gameBoard.y;
            }
            if (x >= gameBoard.x + (2 * r) && x <= gameBoard.x + (3 * r) && y >= gameBoard.y && y <= gameBoard.y + c && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 6;
                currentPiece.x = gameBoard.x + (2 * r);
                currentPiece.y = gameBoard.y;
            }
            if (x >= gameBoard.x && x <= gameBoard.x + r && y >= gameBoard.y + c && y <= gameBoard.y + (2 * c) && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 1;
                currentPiece.x = gameBoard.x;
                currentPiece.y = gameBoard.y + c;
            }
            if (x >= gameBoard.x + r && x <= gameBoard.x + (2 * r) && y >= gameBoard.y + c && y <= gameBoard.y + (2 * c) && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 4;
                currentPiece.x = gameBoard.x + r;
                currentPiece.y = gameBoard.y + c;
            }
            if (x >= gameBoard.x + (2 * r) && x <= gameBoard.x + (3 * r) && y >= gameBoard.y + c && y <= gameBoard.y + (2 * c) && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 7;
                currentPiece.x = gameBoard.x + (2 * r);
                currentPiece.y = gameBoard.y + c;
            }
            if (x >= gameBoard.x && x <= gameBoard.x + r && y >= gameBoard.y + (2 * c) && y <= gameBoard.y + (3 * c) && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 2;
                currentPiece.x = gameBoard.x;
                currentPiece.y = gameBoard.y + (2 * c);
            }
            if (x >= gameBoard.x + r && x <= gameBoard.x + (2 * r) && y >= gameBoard.y + (2 * c) && y <= gameBoard.y + (3 * c) && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 5;
                currentPiece.x = gameBoard.x + r;
                currentPiece.y = gameBoard.y + (2 * c);
            }
            if (x >= gameBoard.x + (2 * r) && x <= gameBoard.x + (3 * r) && y >= gameBoard.y + (2 * c) && y <= gameBoard.y + (3 * c) && !currentPiece.getPos()) {
                currentPiece.pos = currentPiece.order == 8;
                currentPiece.x = gameBoard.x + (2 * r);
                currentPiece.y = gameBoard.y + (2 * c);
            }
            checkBoard();
        }



    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    //distance between two puzzles
    private double getDistance(Pieces a, Pieces b){
        double distance;
        a.getCenter();
        b.getCenter();

        distance = Math.sqrt( Math.pow(Math.abs(a.getCenterX() - b.getCenterX()),2) +  Math.pow(Math.abs(a.getCenterY() - b.getCenterY()),2) - b.getCenterY());

        return distance;
    }

    //choose the closest puzzle
    private void getClosest(int keyPressed){
        int count = 0;
        Pieces[] tmpPieces = new Pieces[8];
        Pieces tmp;
        currentPiece.getCenter();
        //Get pieces on the right
        if(keyPressed == KeyEvent.VK_D) {
//            System.out.println(currentPiece.order + "  1");
            for (int i = 0; i < 9; i++) {
                if (i == currentPiece.order) {
                    continue;
                }
                pieces[i].getCenter();
//                System.out.println(pieces[i].getCenterX() +"  " + currentPiece.getCenterX());
                if (pieces[i].getCenterX() > currentPiece.getCenterX()) {
                    tmpPieces[count++] = pieces[i];
                }
            }
            if(count != 0){
                tmp = tmpPieces[0];
                for(int i = 1; i < count; i ++){
                    if(!(getDistance(tmp, currentPiece) <= getDistance(tmpPieces[i], currentPiece))){
//                        System.out.println(getDistance(tmpPieces[i], currentPiece));
                        tmp = tmpPieces[i];
                        tmp.getCenter();

                    }
                }
                currentPiece = tmp;
            }
        }
        //Get pieces on the left
        if(keyPressed == KeyEvent.VK_A) {
            for (int i = 0; i < 9; i++) {
                if (i == currentPiece.order) {
                    continue;
                }
                pieces[i].getCenter();
                if (pieces[i].getCenterX() < currentPiece.getCenterX()) {
                    tmpPieces[count++] = pieces[i];
                }
            }
            if(count != 0){
                tmp = tmpPieces[0];
                for(int i = 1; i < count; i ++){
                    if(!(getDistance(tmp, currentPiece) <= getDistance(tmpPieces[i], currentPiece))){
//                        System.out.println(getDistance(tmpPieces[i], currentPiece));
                        tmp = tmpPieces[i];
                        tmp.getCenter();

                    }
                }
                currentPiece = tmp;
            }
        }
        //Get pieces on the top side
        if(keyPressed == KeyEvent.VK_W) {
            for (int i = 0; i < 9; i++) {
                if (i == currentPiece.order) {
                    continue;
                }
                pieces[i].getCenter();
                if (pieces[i].getCenterY() < currentPiece.getCenterY()) {
                    tmpPieces[count++] = pieces[i];
                }
            }
            if(count != 0){
                tmp = tmpPieces[0];
                for(int i = 1; i < count; i ++){
                    if(!(getDistance(tmp, currentPiece) <= getDistance(tmpPieces[i], currentPiece))){
//                        System.out.println(getDistance(tmpPieces[i], currentPiece));
                        tmp = tmpPieces[i];
                        tmp.getCenter();
                    }
                }
                currentPiece = tmp;
            }
        }

        //Get pieces on the down side
        if(keyPressed == KeyEvent.VK_S) {
            for (int i = 0; i < 9; i++) {
                if (i == currentPiece.order) {
                    continue;
                }
                pieces[i].getCenter();
                if (pieces[i].getCenterY() > currentPiece.getCenterY()) {
                    tmpPieces[count++] = pieces[i];
                }
            }
            if(count != 0){
                tmp = tmpPieces[0];
                for(int i = 0; i < count; i ++){
                    if(!(getDistance(tmp, currentPiece) <= getDistance(tmpPieces[i], currentPiece))){
//                        System.out.println(getDistance(tmpPieces[i], currentPiece));
                        tmp = tmpPieces[i];
                        tmp.getCenter();
                    }
                }
                currentPiece = tmp;
            }
        }

    }

}
