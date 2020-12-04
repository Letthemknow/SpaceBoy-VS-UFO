package src.game.sprites;

import java.awt.*;
import java.io.File;

public class DeadSpaceShip extends AbstractSpaceShips {
    public DeadSpaceShip() {
    }

    public DeadSpaceShip(int xPosition, int yPosition) {
        super(xPosition, yPosition, new File("Textures/Flame.png"));
        boundary=new Rectangle(xPosition+14,yPosition+40,
                (int)(img.getWidth(null)*.77),(int)(img.getHeight(null)*.4));
        isDead=true;
    }

    @Override
    public void drawShapes(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);
        if(visualization){
            g.drawRect((int)boundary.getX(),(int)boundary.getY(),(boundary.width), (boundary.height));
        }
    }

}
