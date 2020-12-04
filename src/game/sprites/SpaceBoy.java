package src.game.sprites;

import java.awt.*;


public class SpaceBoy extends AbstractSpaceBoy{
    public SpaceBoy(){
    }

    /**
     * constructor
     * @param xPosition x position of the space boy
     * @param yPosition y position of the space boy
     * @param size size of the space boy
     */
    public SpaceBoy(int xPosition, int yPosition, int size){
        super(xPosition, yPosition, size);
    }

    /**
     * constructor
     * @param xPosition x position of the space boy
     * @param yPosition y position of the space boy
     */
    public SpaceBoy(int xPosition, int yPosition){
        super(xPosition, yPosition);
    }

    /**
     * draw the space boy out
     * @param g take in graphics g as parameter
     */
    @Override
    public void drawShapes(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);
        if(visualization){
            g.drawRect((int)boundary.getX(),(int)boundary.getY(),(boundary.width), (boundary.height));
        }
    }

    /**
     * check for collision of space boy and space ship
     * @param r another sprite's boundary
     */
    @Override
    public void collision(Rectangle r){
        if(boundary.intersects(r)){
            isDead=true;
        }
    }
}