/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kai
 */
public class Animation {

    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;
    private boolean playedOnce;

    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        this.images = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            images[i] = args[i];
        }
        frames = args.length;
        playedOnce = false;
    }

    public Animation(int speed, BufferedImage[] imageArr, int x) {
        this.speed = speed;
        this.images = new BufferedImage[imageArr.length];
        for (int i = 0; i < imageArr.length; i++) {
            images[i] = imageArr[i];
        }
        frames = imageArr.length;
        playedOnce = false;
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        currentImg = images[count % frames];
        count++;
        if (count == frames - 1) {
            playedOnce = true;
        }
    }

    public void renderAnimation(Graphics g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }

    public void renderAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }

    public void renderOnce(Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }

}
