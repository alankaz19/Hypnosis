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
    private BufferedImage []  imgResources;
    private String[] paths; //dict
    private int count; // dictCount

    private static ImageResource resourceManager;

    public static ImageResource getInstance(){
        if(resourceManager == null){
            resourceManager = new ImageResource();
        }
        return resourceManager;
    }

    private ImageResource(){
        imgResources = new BufferedImage[2];
        paths = new String[2];
        count = 0;
    }

    public BufferedImage getImage(String path){
        int index = findExisted(path);
        if(index == -1){
            return addImage(path);
        }
        return imgResources[index];
    }

    private BufferedImage addImage(String path){
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(path));
            if(count == imgResources.length){
                doubleArr();
            }
            imgResources[count] = img;
            paths[count++] = path;
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doubleArr(){
        BufferedImage[] tmpImg = new BufferedImage[imgResources.length*2];
        String[] tmpPath = new String[paths.length*2];
        for(int i = 0; i < imgResources.length; i ++){
            tmpImg [i] = imgResources[i];
            tmpPath [i] = paths[i];
        }
        imgResources = tmpImg;
        paths = tmpPath;
    }

    private int findExisted(String path){
        for (int i = 0; i < count; i ++){
            if(path.equals(paths[i])){
                return i;
            }
        }
        return -1;
    }
}
