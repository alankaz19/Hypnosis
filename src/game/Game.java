package game;

import gamestate.GameStateManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 1280, HEIGHT = 720 ;

    private Thread thread;
    private boolean running = false;
    private int keyPressed, mouseClick;

    //gameStateManager
    private GameStateManager gsm;

    //keyInput class
    class KeyInput extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            keyPressed = e.getKeyCode();
            gsm.keyPressed(keyPressed);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == keyPressed ){ // only assign -1 to pressedKey if no other key is pressed to chane the pressedKey value
                //keyPressed = -1;
                gsm.keyReleased(keyPressed);
            }
        }
    }

    class MouseInput extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            gsm.mousePressed(e.getX(), e.getY());
            System.out.println(e.getX() + " " + e.getY());
        }
        @Override
        public void mouseMoved(MouseEvent e){
            gsm.mouseDragged(e.getX(),e.getY());
        }
        public void setMousePoint(MouseEvent e){
            gsm.setMousePoiont(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e){
            gsm.mouseReleased(e.getButton());

        }

    }
    //end


    // initialize everything
    private void init(){
        gsm = new GameStateManager();
    }


    //main
    public Game(){
        init();
        this.addKeyListener(new KeyInput());
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());
        this.setFocusable(true);
    }
    //end


    public  synchronized void start(){
        thread = new Thread(this);
        running = true;
        thread.start();
    }
    public  synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();  //track time taken by one tick
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }



    private void tick(){
        gsm.tick();

    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        gsm.render(g);

        g.dispose();
        bs.show();
    }


    
    public static void main(String[] args){
        new Window(WIDTH, HEIGHT, "Hypnosis", new Game());
    }
}
