/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import gameobject.GameObject;


/**
 *
 * @author Kai
 */
public class Camera {
    private int x;
    private int y;
    private int position;
    
    public  Camera(int x, int y, int posititon){
        this.x = x;
        this.y = y;
        this.position = posititon;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void tick(GameObject o){
        x = - o.getX() + this.position;
//        System.out.println("c X:"+this.x);
//        System.out.println("C Y:"+this.y);
    }
}
