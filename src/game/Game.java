package game;

import gameobject.Handler;
import gameobject.Player;
import scene.BackGround;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 1280, HEIGHT = 720 ;

    private Thread thread;
    private boolean running = false;

    //game object handler
    private Handler handler;
    //end

    //background pic
    private BackGround backGround1;
    //end


    public Game(){
        backGround1 = new BackGround();
        handler = new Handler();
        new Window(WIDTH, HEIGHT, "GameTest", this);
        handler.addObject(new Player(0,240));
    }

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
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }


    //tick == move??
    private void tick(){
        handler.tick();
        backGround1.tick();

    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
//        g.setColor(Color.black);
//        g.fillRect(0,0, WIDTH, HEIGHT);

        //render gameObject
        handler.render(g);

        //render background
        backGround1.render(g);

        g.dispose();
        bs.show();

    }



    public static void main(String args[]){
        new Game();

    }
}
