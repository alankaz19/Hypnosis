/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;


import java.awt.Graphics;
import game.Game;
import game.Updater;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import scene.Texture;
/**
 *
 * @author Kai
 */
public abstract class GameObject implements Updater{
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
    protected int msgPosition;
    protected BufferedImage chatBubble;
    

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

    public void setMsgPosition(int msgPosition) {
        this.msgPosition = msgPosition;
    }
    
    public void showMsg(String msg,int Duration,Color color,int MsgPosition){
        this.msg = msg;
        this.color = color;
        this.msgFrame = Duration;
        this.msgFrameCount = 0;
        this.msgPosition = MsgPosition;
    }
    
    public void renderMsg(Graphics g){
        if(msgFrameCount < msgFrame){
            //Font font = g.getFont().deriveFont(20.0f);
            g.setFont(new Font("Helvetica", Font.BOLD, 20));
            g.setColor(color);
            FontMetrics fm = g.getFontMetrics();
            int sa = fm.getAscent();
            chatBubble = Texture.getInstance().ui[0];
            g.drawImage(chatBubble, x + msgPosition, y + height / 2 - chatBubble.getHeight() -130, null);//先畫對話框
            drawString(g, msg, x+20 + msgPosition,  y + height / 2 -sa / 2 - 245);
            msgFrameCount++;
        }
    }
    
    private void drawString(Graphics g, String text, int x, int y) {
        int bubbleWidth = 11;
        int count = 1;
        for (int i = 0; i < i + bubbleWidth; i += bubbleWidth){
            if(text.length() - i > bubbleWidth){
                String line = text.substring(i, bubbleWidth * count++);
                g.drawString(line, x, y += g.getFontMetrics().getHeight());
            }
            else if(text.length() <= bubbleWidth){
                g.drawString(text, x, y += g.getFontMetrics().getHeight() * 2);
                break;
            }
            else if(text.length() - i <= bubbleWidth){
                String line = text.substring(i);
                g.drawString(line, x, y += g.getFontMetrics().getHeight());
                break;
            }
        }
    }
}
