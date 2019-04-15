    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcemanage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Kai
 */
public class ImageResource {
    private BufferedImage[] res;
    private String[] paths;
    private int count;
    
    private static ImageResource resourceManager;
    
    public ImageResource getInstance(){
        if(resourceManager == null){
            resourceManager = new ImageResource();
        }
        return resourceManager;
    }
    
    private ImageResource(){
        res = new BufferedImage[2];
        paths = new String[2];
        count = 0;
    }
    
    public BufferedImage tryGetImage(String path){
        int index = findExisted(path);
        if(index == -1){
            return addImg(path);
        }
        return res[index];
    }
    
    private BufferedImage addImg(String path){
        try{
            BufferedImage img = ImageIO.read(getClass().getResource("/resources/" + path + ".png"));
            if(count == res.length){
                doubleArr();
            }
            res[count] = img;
            paths[count++] = path;
            return img;
        }catch(IOException e){
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
        BufferedImage[] tempImg = new BufferedImage[res.length * 2];
        String[] tempString = new String[paths.length * 2];
        for(int i = 0; i < res.length; i++){
            tempImg[i] = res[i];
        }
        for(int i = 0; i < paths.length; i++){
            tempString[i] = paths[i];
        }
        res = tempImg;
        paths = tempString;
    }
}
