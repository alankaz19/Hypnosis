/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import game.Updater;
import gameobject.GameObject;

/**
 *
 * @author Kai
 */
public abstract class UIObject implements Updater{
    //圖像
    protected int width;
    protected int height;
    
    //生成位置
    protected int x;
    protected int y;
    
    //點擊狀態
    protected boolean Hovered;
    protected boolean Clicked;
   
    public boolean checkCollision(GameObject o){
      int leftSide, oLeft;
      int rightSide, oRight;
      int top, oTop;
      int bottom, oBottom;

      leftSide = this.x;  oLeft = o.x;
      rightSide = this.x + width; oRight = o.x + o.getWidth();
      top = this.y ; oTop = o.y;
      bottom = this.y + height; oBottom = o.y + o.getHeight();

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
    
    public boolean checkCollision(UIObject o){
      int leftSide, oLeft;
      int rightSide, oRight;
      int top, oTop;
      int bottom, oBottom;

      leftSide = this.x;  oLeft = o.x;
      rightSide = this.x + width; oRight = o.x + o.getWidth();
      top = this.y ; oTop = o.y;
      bottom = this.y + height; oBottom = o.y + o.getHeight();

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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setClicked(boolean Clicked) {
        this.Clicked = Clicked;
    }

    public void setHovered(boolean Hovered) {
        this.Hovered = Hovered;
    }

    public boolean isClicked() {
        return Clicked;
    }

    public boolean isHovered() {
        return Hovered;
    }
    
    
    
    
}
