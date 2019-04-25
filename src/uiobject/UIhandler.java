/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Kai
 */
public class UIhandler {
    ArrayList<UIObject> object = new ArrayList<>();

    public void tick(){ 
        //foreach 語法
        for (UIObject element : object) {
            element.tick();
        }
    }
    public void render(Graphics g){
        //foreach 語法
        for (UIObject element : object) { 
            element.render(g);
        }
    }

    public ArrayList<UIObject> getObject() {
        return object;
    }

    public void addObject(UIObject object){
        this.object.add(object);
    }

    public void removeObject(UIObject object){
        this.object.remove(object);
    }
}
