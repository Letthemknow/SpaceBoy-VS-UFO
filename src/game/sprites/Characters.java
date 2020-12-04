package src.game.sprites;

import src.game.interfaces.*;
import java.awt.*;
import java.io.File;

public abstract class Characters extends Sprite implements IFeatures{

    public Characters(){}

    public Characters(int xPosition, int yPosition, int size){
        super(xPosition, yPosition, size);
        isDead = false;
    }

    public Characters(int xPosition, int yPosition, File texture){
        super(xPosition, yPosition, texture);
        isDead = false;
    }

    public void screenSetter(Canvas canvas){
        this.screen=canvas;
    }
    
    @Override
    public void movement(){
        return;
    }

    @Override
    public void collision(Rectangle r){
        return;
    }

    @Override
    public void boundaries(){
        return;
    }

    public Boolean isDead;
    protected int speed;
    protected Boolean visualization = false;
}
