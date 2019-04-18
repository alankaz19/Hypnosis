/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;


import java.awt.Graphics;
import game.Game;
/**
 *
 * @author Kai
 */
public abstract class GameObject {
    // 長寬
    protected int width;
    protected int height;
    // 座標 
    protected int x;
    protected int y;
    // 方向
    protected int dir;
    //加速度
    protected int xVel;
    // 判定屬性
    protected int currentX;
    protected int currentY;
    protected int xDest;
    protected int yDest;
    protected int xTemp;
    protected int yTemp;
    // 判定相交
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    
    // enum ID
    protected ObjectID id;
    


    //需要給object 生成的位置
    public GameObject(int x, int y,ObjectID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    
    public abstract ObjectID getID();

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }
    
    public int getxVel(){
        return this.xVel;
    }   

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setID(ObjectID id){
        this.id = id;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setDirection(int dir){
        this.dir = dir;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean checkBorder() {
        return xDest < 0 || xDest + width > Game.WIDTH || yDest  < 0 || yDest + height > Game.HEIGHT ;
    }
    

    
}
