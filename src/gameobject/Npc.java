/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import scene.Animation;
import scene.Texture;

import java.awt.*;

/**
 *
 * @author Kai
 */
public class Npc extends GameObject {
    Texture tex = Texture.getInstance();
    private Animation npcMove;
    private Player player;


    public Npc(int x, int y, ObjectID id, int movement, Player player) {
        super(x, y, id);
        this.width = 80;
        this.height = 64;
        this.dir = 0;
        this.player = player;
        this.npcMove = new Animation(movement, tex.npc[0], tex.npc[1], tex.npc[2]);
    }



    @Override
    public void tick() {
        x += xVel;
//        if(this.y > player.y){
//            y -= 1;
//        }
//        if(this.y <= player.y){
//            y += 1;
//        }

        npcMove.runAnimation();
    }

    @Override
    public void render(Graphics g) {
            if(this.dir == 1){
                npcMove.renderAnimation(g, x, y, width, height);
//                g.drawRect(x, y, width, height);//畫判斷框
            }
            if(this.dir == 0){
                npcMove.renderAnimation(g, x + width, y, -width, height);
//                g.drawRect(x, y, width, height);//畫判斷框
            }
    }

    @Override
    public ObjectID getID() {
        return null;
    }
}
