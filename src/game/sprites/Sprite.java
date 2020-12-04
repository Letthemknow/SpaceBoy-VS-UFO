package src.game.sprites;

import src.game.interfaces.ISprite;
import java.io.*;
import java.awt.*;
import javax.imageio.*;

public abstract class Sprite implements ISprite {
    /**
     * sprite constructor
     */
    Sprite(){}
    /**
     *
     * @param xPosition x position of the sprite
     * @param yPosition y position of the sprite
     * @param size size of the sprite
     */
    protected Sprite(int xPosition, int yPosition, int size){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
    }

    /**
     *
     * @param xPosition x position of the sprite
     * @param yPosition y position of the sprite
     * @param texture   texture of the sprite
     *                  calls in the imageSetter function;
     */
    protected Sprite(int xPosition, int yPosition, File texture){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.texture = texture;
        imageSetter();
    }

    /**
     * read the image texture
     */
    private void imageSetter(){
        try{
            img=ImageIO.read(texture);

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param g take in graphics g as parameter
     */
    public void drawShapes(Graphics g){
        return;
    }

    //attributes of the class
    public int size;
    public int xPosition,yPosition;
    public Canvas screen;
    protected File texture;
    public Image img;
    public Rectangle boundary;
}
