package game;

import gameobject.Player;
import scene.Texture;
import gamestate.GameStateManager;
import scene.BackGround;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 1280, HEIGHT = 720 ;

    private Thread thread;
    private boolean running = false;
    private int keyPressed, mouseClick, count;

    //game object handler
    private Handler handler;
    //end

    
    //background pic
    private BackGround backGround1;
    //end
    
    //gameStateManager
    private GameStateManager gsm;

    //keyInput class
    class KeyInput extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            keyPressed = e.getKeyCode();
            if(keyPressed == KeyEvent.VK_T){
                gsm.setState(1);
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == keyPressed ){ // only assign -1 to pressedKey if no other key is pressed to chane the pressedKey value
                keyPressed = -1;
            }
        }
    }

    class MouseInput extends MouseAdapter{

        public void mouseClicked(MouseEvent e){
            mouseClick = e.getButton();
        }

        public void mouseReleased(MouseEvent e){
            if(e.getButton() == mouseClick){
                mouseClick = -1;
            }
        }
    }
    //end

    public void Event(){
        if(keyPressed == KeyEvent.VK_D){
            handler.getObject().get(0).setxVel(1);
            backGround1.setScrollX(5);
        }
        else if(keyPressed == KeyEvent.VK_A){
            handler.getObject().get(0).setxVel(-1);
            backGround1.setScrollX(-5);
        }
        else{
            handler.getObject().get(0).setxVel(0);
            backGround1.setScrollX(0);
        }
    }

    // initialize everything
    public void init(){
        gsm = new GameStateManager();
        //tex = new Texture();
        backGround1 = new BackGround(1);
        handler = new Handler();
    }


    //main
    public Game(){
        init();
        handler.addObject(new Player(0,HEIGHT/2));
        this.addKeyListener(new KeyInput());
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
        long lastTime = System.nanoTime();
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
        Event();
        backGround1.tick();
        handler.tick();
        gsm.tick();

    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        //render background
        backGround1.render(g);

        //render gameObject
        handler.render(g);
        
        //render state

        gsm.render(g);




        g.dispose();
        bs.show();

    }


    
    public static void main(String args[]){
        new Window(WIDTH, HEIGHT, "GameTest", new Game());

    }
}
