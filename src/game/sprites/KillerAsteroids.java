package src.game.sprites;

import java.awt.*;
import java.io.File;
import java.util.Random;

public class KillerAsteroids extends Characters {

    public KillerAsteroids() {

    }

    //constructor details inside of Sprite.java, inherited constructor
    public KillerAsteroids(int xPosition, int yPosition, int size) {
        super(xPosition, yPosition, size);
    }

    public KillerAsteroids(int xPosition, int yPosition) {
        super(xPosition, yPosition, new File("Textures/rsz_asteroid.png"));
        boundary=new Rectangle(xPosition+13,yPosition+10,
                (int)(img.getWidth(null)*.5),(int)(img.getHeight(null)*.6));
    }
    
    @Override
    public void drawShapes(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);
        if(visualization){
            g.drawRect((int)boundary.getX(),(int)boundary.getY(),(boundary.width), (boundary.height));
        }
    }

    @Override
    public void movement() {
        double percent=0.95;
        double percent2=0.93;
        if(xPosition > screen.getWidth()*percent || xPosition < 0){
            xSpeed *= -1;
        }
        
        if(yPosition > screen.getHeight()*percent2 || yPosition < 0){
            ySpeed *= -1;
        }
        xPosition+=xSpeed;
        yPosition+=ySpeed;
        boundary.setLocation((int)boundary.getX()+xSpeed,(int)boundary.getY()+ySpeed);
    }

    private int randomNum(){
        Random rand=new Random();
        return (rand.nextBoolean())?1:-1;
    }

    private int xSpeed = 15*randomNum();
    private int ySpeed = 15*randomNum();
}









