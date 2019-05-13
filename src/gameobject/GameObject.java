/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;


import java.awt.*;

import game.Game;
import game.Updater;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import scene.Texture;
import uiobject.Message;
/**
 *
 * @author Kai
 */
public abstract class GameObject implements Updater{
    // 長寬
    protected int width;
    protected int height;
    protected int collisionWidth;
    protected int collisionHeight;
    
    // 座標 
    public int x;
    public int y;
    protected int collisionX;
    protected int collisionY;

    // 方向
    protected int dir;
    //加速度
    protected int xVel;
    protected int yVel;
    // 判定屬性

    protected int xDest;
    protected int yDest;

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    protected boolean jumping;
    protected boolean falling;
    // 判定相交
    protected boolean isCollision;
    
    // enum ID
    protected ObjectID id;
    
    // 對白相關
    protected Message msg;
    protected String word;
    protected Color color;
    protected int msgFrame;
    protected int msgFrameCount;
    protected int msgPosition;
    protected BufferedImage chatBubble;
    protected float alpha;
    
    //點擊效果
    protected boolean clicked;
    protected boolean show;
    protected boolean clickable;
    

    //需要給object 生成的位置
    public GameObject(int x, int y,ObjectID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public ObjectID getID(){
        return this.id;
    }

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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setDirection(int dir){
        this.dir = dir;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getCenterX(){
        return this.x + this.width /2;
    }
    
    public int getCenterY(){
        return this.y + this.height /2;
    }
    
    public boolean getIsCollision(){
        return this.isCollision;
    }

    public boolean checkBorder() {
        return xDest < 0 || xDest + width > Game.WIDTH|| yDest  < 0 || yDest + height > Game.HEIGHT ;
    }

    public void setChatBubble(BufferedImage chatBubble) {
        this.chatBubble = chatBubble;
    }
    
    public void setShow(boolean show) {
        this.show = show;
    }
    
    public boolean isShow() {
        return show;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    
    
    public boolean isClicked() {
        return clicked;
    }


    public Rectangle getBot(){
        return new Rectangle(x + 10,y + height/2,width - 30,height/2);
    }
    public Rectangle getTop(){
        return new Rectangle(x + 10,y,width - 30,height/2);
    }
    public Rectangle getRight(){
        return new Rectangle(x ,y + 5,5,height - 5);
    }
    public Rectangle getLeft(){
        return new Rectangle(x + width,y + 5,5,height - 5);
    }
    public Rectangle getBound(){
        return new Rectangle(x ,y - 3,width,height + 5);
    }

    public boolean checkFloor(GameObject o ){
        if(this.getBound().intersects(o.getBot())){
            return true;
        }
        if(this.getBound().intersects(o.getTop())){
            return true;
        }
        return false;
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
    
    public boolean LevelOneCheckCollision(GameObject o){
        int leftSide, oLeft;
        int rightSide, oRight;
        int top, oTop;
        int bottom, oBottom;

        leftSide = this.collisionX;  oLeft = o.collisionX;
        rightSide = this.collisionX + collisionWidth; oRight = o.collisionX + o.collisionWidth;
        top = this.collisionY ; oTop = o.collisionY;
        bottom = this.collisionY + collisionHeight; oBottom = o.collisionY + o.collisionHeight;

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

    public void setMsgPosition(int msgPosition) {
        this.msgPosition = msgPosition;
    }
    
    public void showMsg(String msg,int Duration,Color color,int MsgPosition,Font font){
        this.msg = new Message();
        this.color = color;
        this.word = msg;
        this.msg.setFont(font);
        this.msgFrame = Duration;
        this.msgPosition = MsgPosition;
        this.msgFrameCount = 0;
        this.alpha = 0;
    }
    
    public void renderMsg(Graphics g){
        
        if(msgFrameCount < msgFrame){
            if(this.alpha <= 0.99f && this.alpha >= 0){
            this.alpha += 0.005f;
            }
            else if(this.alpha >= 0.9f){
                this.alpha = 1.0f;
            }
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alpha));
            g.drawImage(Texture.getInstance().ui[0], x + 100 + msgPosition, y -100, null);//先畫對話框
            this.msg.showMsg( x + 127 + msgPosition,  y - 85, word, msgFrame, color);
            msg.render(g);
            msgFrameCount++;
        }
    }
}
