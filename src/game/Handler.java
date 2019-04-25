package game;

import gameobject.GameObject;
import java.awt.*;
import java.util.ArrayList;

public class Handler implements Updater{

    ArrayList<GameObject> object = new ArrayList<>();
    
    @Override
    public void tick(){
//        for(int i = 0; i < object.size(); i++){
//            GameObject tempObject = object.get(i);
//            
//            tempObject.tick();
//            
//        }
        
        //foreach 語法
        for (GameObject element : object) {
            element.tick();
        }
    }
    
    @Override
    public void render(Graphics g){
//        for(int i = 0; i < object.size(); i++){
//            GameObject tempObject = object.get(i);
//            tempObject.render(g);
//        }
        
        //foreach 語法
        for (GameObject element : object) { 
            element.render(g);
        }
    }

    public ArrayList<GameObject> getObject() {
        return object;
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
