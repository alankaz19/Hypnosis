/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import game.Game;
import scene.Animation;
import scene.Texture;

import java.awt.*;

/**
 *
 * @author Kai
 */
public class Npc extends GameObject {
    private Texture tex = Texture.getInstance();
    private Animation npcMove;
    private ActionPlayer player;
    private int speed = 3;
    private int maxSpeed = 13;
    private int diveCoolDown;
    private boolean diving;

    public Npc(int x, int y, ObjectID id, int movement, ActionPlayer player) {
        super(x, y, id);
        this.width = 80;
        this.height = 64;
        this.dir = 0;
        this.player = player;
        this.npcMove = new Animation(movement, tex.npc[0], tex.npc[1], tex.npc[2]);
        this.diving = true;
        diveCoolDown = 100;
    }
    @Override
    public void tick() {
        x += xVel;
        if(y < player.getY()){
            y += 3;
        }
        if (y > player.getY()){
            y -= 3;
        }
        npcMove.runAnimation();
    }
    public boolean checkBorder() {
        this.xDest = x + xVel;
        this.yDest = y + yVel;

        if(super.checkBorder()){
            if(xDest + width > Game.WIDTH ){
                this.xVel = 0;
                return true;
            }else if(xDest < 0){
                this.xVel = 0;
                return true;
            }else if(yDest < 0){
                this.yVel = 0;
                return true;
            }
        }
        return false;
    }

    public void dive(){
        checkBorder();
        if(this.x < Game.WIDTH/2 && !diving){
            xVel += speed;
            if(xVel >= maxSpeed){
                xVel = maxSpeed;
                diving = true;
            }
        }
        else if(x + width >= Game.WIDTH - 20){
            diveCoolDown --;
            if(diveCoolDown == 0){
                diving = false;
                diveCoolDown = 100;
            }

        }
        if(this.x > Game.WIDTH/2 && !diving){
            xVel -= speed;
            if( xVel <= -maxSpeed){
                xVel = -maxSpeed;
                diving = true;
            }
        }
        else if(x <= 20){
            diveCoolDown --;
            if(diveCoolDown == 0){
                diving = false;
                diveCoolDown = 100;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(this.xVel < 0){
            this.dir = 0;
            npcMove.renderAnimation(g, x, y, width, height);
        }else if(this.xVel > 0){
            this.dir = 1;
            npcMove.renderAnimation(g, x + width, y, -width, height);
        }
        else{
            if(this.dir == 1){
                npcMove.renderAnimation(g, x, y, width, height);
                //g.drawRect(x, y, width, height);//畫判斷框
            }
            if(this.dir == 0){
                npcMove.renderAnimation(g, x + width, y, -width, height);
                //g.drawRect(x, y, width, height);//畫判斷框
            }
        }
    }
    @Override
    public ObjectID getID() {
        return null;
    }
}
