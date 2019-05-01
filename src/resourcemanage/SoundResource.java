/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcemanage;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Kai
 */
public class SoundResource {
    private AudioClip[] res;
    private String[] paths;
    private int count;
    
    private static SoundResource resourceManager;
    
    public  static SoundResource getInstance(){
        if(resourceManager == null){
            resourceManager = new SoundResource();
        }
        return resourceManager;
    }
    
    private SoundResource(){
        res = new AudioClip[2];
        paths = new String[2];
        count = 0;
    }
    
    public AudioClip getClip(String path){
        int index = findExisted(path);
        if(index == -1){
            return addSound(path);
        }
        return res[index];
    }
    
    private AudioClip addSound(String path){
        try{
            AudioClip clip = Applet.newAudioClip(getClass().getResource(path));
            if(count == res.length){
                doubleArr();
            }
            res[count] = clip ;
            paths[count++] = path;
            return clip;
        }catch(IllegalArgumentException e){
            System.out.println(path + "not found");
        }
        return null;
    }
    
    private int findExisted(String path){
        for(int i = 0; i < count ; i++){
            if(path.equals(paths[i])){
                return i;
            }
            
        }
        return -1;
    }
    
    private void doubleArr(){
        AudioClip[] tempClip = new AudioClip[res.length * 2];
        String[] tempString = new String[paths.length * 2];
        for(int i = 0; i < res.length; i++){
            tempClip[i] = res[i];
        }
        for(int i = 0; i < paths.length; i++){
            tempString[i] = paths[i];
        }
        res = tempClip;
        paths = tempString;
    }
}
