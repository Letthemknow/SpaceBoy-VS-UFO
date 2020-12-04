package src.game.display;
//imports the src.game display package.
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;

public class FrameBackground {
    /**
     * constructor
     * 
     * @param imageDir
     */
    public FrameBackground(File imageDir) {
        path = new ImageDirectory(imageDir);
        _imageSetter(path.dir.get(0));
    }
    /**
     * resets image list
     * use this to reset levels in Game.java
     */
   public void resetDirectory(){
        path.imageArray();
        _imageSetter(path.dir.get(0));
   }
   /**
    * reads file path of image to
    * be displayed
    * @param imageName
    */
    private void _imageSetter (File imageName){
        try {
            img = ImageIO.read(imageName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * finds the next image in the list
     * then calls imageSetter to diplay that
     * image for the new level
     */
    public void nextImage(){
        try{
            path.dir.remove(0);
            _imageSetter(path.dir.get(0));
        }catch(Exception e){

        }
    }
    /**
     * Will draw the image to the background
     * of the canvas
     * @param g Graphics
     */
    public void drawBackground(Graphics g){
        g.drawImage(getImage(), 0, 0, null);
    }
 
    public Image getImage () {
        return img;
    }

    private Image img;
    public ImageDirectory path;
}

class ImageDirectory{

    ImageDirectory(File path){
        this.path = path;
        imageArray();
    }
    /**
     * clears the list if we are already playing 
     * the game; used to reset levels
     * Populates the dir attribute with 
     * a list of files
     */
    protected void imageArray(){
        if(!dir.isEmpty()){
            dir.clear();
        }
        for(String images : path.list()){
            for(final String ext : EXTENSIONS){
                if(images.endsWith(ext)){
                    dir.add(new File(path.getName() + "/" + images));
                }
            }
        }
    }

    private static final String[] EXTENSIONS = new String[]{".jpg", ".gif", ".png", ".jpeg"};
    protected ArrayList<File> dir =  new ArrayList<File>();
    public File path;
}