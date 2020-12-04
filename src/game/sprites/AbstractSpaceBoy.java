package src.game.sprites;

import java.awt.*;
import java.io.File;

public abstract class AbstractSpaceBoy extends Characters{
    public AbstractSpaceBoy(){
    }

   public AbstractSpaceBoy(int xPosition, int yPosition, int size){
        super(xPosition, yPosition, size);
    }

    public AbstractSpaceBoy(int xPosition, int yPosition){
        super(xPosition, yPosition, new File("Textures/spaceboy.png"));
        boundary=new Rectangle(xPosition+10,yPosition-8,
                (int)(img.getWidth(null)*.55),(int)(img.getHeight(null)*1.4));
    }
}
