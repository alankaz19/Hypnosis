package gameobject.items;

import gameobject.GameObject;
import gameobject.ObjectID;
import resourcemanage.ImageResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends GameObject {

    private BufferedImage img;

    public Obstacle(int x, int y, ObjectID id, String path) {
        super(x, y, id);
        this.img = ImageResource.getInstance().getImage(path);
        xVel = 7;
    }

    @Override
    public void tick() {
        x -= xVel;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,x,y,35,35,null);

    }

    @Override
    public ObjectID getID() {
        return null;
    }
}
