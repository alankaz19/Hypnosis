/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.awt.*;

/**
 *
 * @author Kai
 */
public class Player extends GameObject {



    public Player(int x, int y) {
        super(x, y);
    }


    @Override //move method
    public void tick() {

    }

    @Override   //testing
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, 70,200);

    }
}
