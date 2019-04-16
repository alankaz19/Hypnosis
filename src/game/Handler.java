package game;

import gameobject.GameObject;
import java.awt.*;
import java.util.ArrayList;

public class Handler {

    ArrayList<GameObject> object = new ArrayList<>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
            
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
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
