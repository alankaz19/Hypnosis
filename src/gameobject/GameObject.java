/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;


import java.awt.Graphics;
import game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
/**
 *
 * @author Kai
 */
public abstract class GameObject {
    // 長寬
    protected int width;
    protected int height;
    // 座標 
    public int x;
    public int y;
    // 方向
    protected int dir;
    //加速度
    protected int xVel;
    protected int yVel;
    // 判定屬性
    protected int currentX;
    protected int currentY;
    protected int xDest;
    protected int yDest;
    protected int xTemp;
    protected int yTemp;
    // 判定相交
    protected boolean isCollision;
    
    // enum ID
    protected ObjectID id;
    
    // 對白相關
    protected String msg;
    protected Color color;
    protected int msgFrame;
    protected int msgFrameCount;


    //需要給object 生成的位置
    public GameObject(int x, int y,ObjectID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    
    public abstract ObjectID getID();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxVel() {
        return xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
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
    
    public boolean getIsCollision(){
        return this.isCollision;
    }

    public boolean checkBorder() {
        return xDest < 0 || xDest + width > Game.WIDTH || yDest  < 0 || yDest + height > Game.HEIGHT ;
    }
    
    public boolean checkCollision(GameObject o){
        int leftSide, oLeft;
        int rightSide, oRight;
        int top, oTop;
        int bottom, oBottom;
        
        leftSide = this.x;  oLeft = o.x;
        rightSide = this.x + width; oRight = o.x + o.width;
        top = this.y ; oTop = o.y;
        bottom = this.y + height; oBottom = o.y + o.height;
        
        if(leftSide > oRight){
            return false;
        }
        if(rightSide < oLeft){
            return false;
        }
        if(top > oBottom){
            return false;
        }
        if(bottom < oTop){
            return false;
        }
        return true;
       
    }

    public void setIsCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }
    
    public void showMsg(String msg,int Duration,Color color){
        this.msg = msg;
        this.color = color;
        this.msgFrame = Duration;
        this.msgFrameCount = 0;
    }
    
    public void renderMsg(Graphics g,int MsgPosition){
        if(msgFrameCount < msgFrame){
            Font font = g.getFont().deriveFont(20.0f);
            g.setFont(font);
            g.setColor(color);
            FontMetrics fm = g.getFontMetrics();
            int sw = fm.stringWidth(msg);
            int sa = fm.getAscent();
//            g.drawImage(null, x, y, null);//先畫對話框
            g.drawString(msg , 400 , y + height/2 -sa/2 - 150);//再畫字
            msgFrameCount++;
        }
    }
    
    
}
