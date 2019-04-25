/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.awt.Graphics;

/**
 *
 * @author Kai
 */
public class Npc extends GameObject{

    public Npc(int x, int y, ObjectID id) {
        super(x, y, id);
    }
    

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
    
}
