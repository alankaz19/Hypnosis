/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcemanage;

import java.io.BufferedReader;
import java.io.IOException;



/**
 *
 * @author Kai
 */
public class StringResource {
    private String[] res;
    private int[] paths;
    private int count;
    BufferedReader bw;
    
    private static StringResource resourceManager;
    
    public StringResource getInstance(){
        if(resourceManager == null){
            resourceManager = new StringResource();
        }
        return resourceManager;
    }
    
    private StringResource(){
        res = new String[2];
        paths = new int[2];
        count = 0;
    }
    
    public String tryGetImage(int path) throws IOException{
        int index = findExisted(path);
        if(index == -1){
            return addString(path);
        }
        return res[index];
    }
    
    private String addString(int path) throws IOException{
        try{
            String str = bw.readLine();
            if(count == res.length){
                doubleArr();
            }
            res[count] = str ;
            paths[count++] = path;
            return str;
        }catch(IllegalArgumentException e){
            System.out.println(path + "not found");
        }
        return null;
    }
    
    private int findExisted(int path){
        for(int i = 0; i < count ; i++){
            if(path == paths[i]){
                return i;
            }
        }
        return -1;
    }
    
    private void doubleArr(){
        String[] tempString = new String[res.length * 2];
        int[] tempInt = new int[paths.length * 2];
        for(int i = 0; i < res.length; i++){
            tempString[i] = res[i];
        }
        for(int i = 0; i < paths.length; i++){
            tempInt[i] = paths[i];
        }
        res = tempString;
        paths = tempInt;
    }
}
