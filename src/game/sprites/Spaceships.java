package src.game.sprites;

import java.awt.*;
import java.io.File;

public class Spaceships extends AbstractSpaceShips  {
    /**
     * constructor
     */
    public Spaceships() {}

    /**
     * creates the boundaries for the collision test and import the texture for the spaceship
     * @param xPosition x position of the spaceship
     * @param yPosition y position of the spaceship
     */
    public Spaceships(int xPosition, int yPosition) {
        super(xPosition, yPosition, new File("Textures/spaceship.png"));
        boundary=new Rectangle(xPosition+15,yPosition+28,
                (int)(img.getWidth(null)*.60),(int)(img.getHeight(null)*.25));
    }

    /**
     * this method draw out the spaceship
     * @param g take in graphics g as parameter
     */
    @Override
    public void drawShapes(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);
        if(visualization){
            g.drawRect((int)boundary.getX(),(int)boundary.getY(),(boundary.width),
                (boundary.height));
        }
    }

    /**
     * this method sets the movement of the method
     */
    @Override
    public void movement() {
        int xBoundary=(int)boundary.getX();
        int yBoundary=(int)boundary.getY();

        switch (keyCode) {
            case RIGHT:
            case D:
                xPosition += SPEED;
                boundary.setLocation(xBoundary+SPEED,yBoundary);
                break;
            case LEFT:
            case A:
                xPosition -= SPEED;
                boundary.setLocation(xBoundary-SPEED,yBoundary);
                break;
            case UP:
            case W:
                yPosition += SPEED;
                boundary.setLocation(xBoundary,yBoundary-SPEED);
                break;
            case DOWN:
            case S:
                yPosition -= SPEED;
                boundary.setLocation(xBoundary,yBoundary+SPEED);
                break;
        }
        
        boundaries();
    }

    /**
     * this method sets the boundaries for the spaceship
     */
    @Override
    public void boundaries() {
        double percent=0.79;
        if(xPosition<0){
            xPosition=0;
        }
        else if(xPosition>width-img.getWidth(null)*percent){
            xPosition=(int)(width-img.getWidth(null)*percent);
        }
        
        if(yPosition<-20){
            yPosition=-20;
        }
        else if(yPosition>height-img.getHeight(null)*percent){
            yPosition=(int)(height-img.getHeight(null)*percent);
        }
        boundary.setLocation(xPosition+15,yPosition+28);
    }

    /**
     * this method checks for collison for the spaceship
     * @param r another sprite's boundary
     */
    @Override
    public void collision(Rectangle r){
        if(boundary.intersects(r)){
            isDead=true;

        }
    }
//    @Override
//    public void drawShapes(Graphics g){

//        g.setColor(Color.gray);

//        g.setColor(Color.white);
//        g.drawLine(xPosition+135, yPosition-60, xPosition+135, yPosition-20);

//        g.setColor(Color.gray);
//        g.fillOval(xPosition+110,yPosition-50,50,35);

//        g.setColor(Color.lightGray);
//        g.fillRoundRect(xPosition+93,yPosition-35,85,9,10,10);
//        g.setColor(Color.white);
//        g.fillOval(xPosition+109,yPosition-35,8,8);
//        g.fillOval(xPosition+124,yPosition-35,8,8);
//        g.fillOval(xPosition+139,yPosition-35,8,8);
//        g.fillOval(xPosition+154,yPosition-35,8,8);
//        //g.setColor(Color.blue);
//        //g.fillArc(xPosition+100, yPosition-50, xPosition-230, xPosition-270,
//         //       xPosition+60, yPosition-120);
//        int[] xTrianglePositions = {xPosition+130, xPosition+140,xPosition+135};
//        int[] yTrianglePositions = {yPosition-60, yPosition-60, yPosition-70};
//        g.fillPolygon(xTrianglePositions, yTrianglePositions, 3);
//    }
    /**
     * attributes of the spaceship
     */
        private final int SPEED = 50;
        private final int LEFT = 37;
        private final int RIGHT  = 39;
        private final int UP = 40;
        private final int DOWN = 38;
        private final int D = 68;
        private final int A = 65;
        private final int S = 87;
        private final int W = 83;
    }

