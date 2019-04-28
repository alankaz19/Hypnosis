/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import scene.Texture;

/**
 *
 * @author Kai
 */
public class DialogBox extends UIObject{
    private String msg;
    private Color color;
    private int msgFrame;
    private int msgFrameCount;
    private int msgPosition;

    public DialogBox() {
    
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
            java.awt.Font font = g.getFont().deriveFont(20.0f);
            g.setFont(font);
            g.setColor(color);
            FontMetrics fm = g.getFontMetrics();
            int sa = fm.getAscent();
            g.drawImage(Texture.getInstance().ui[0], 410 + msgPosition, y + height / 2 - Texture.getInstance().ui[0].getHeight() -130, null);//先畫對話框
            drawString(g, msg, 430 + msgPosition,  y + height / 2 -sa/2 - 245);
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

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
}
