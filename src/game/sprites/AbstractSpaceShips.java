package src.game.sprites;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public abstract class AbstractSpaceShips extends Characters implements KeyListener {
    public AbstractSpaceShips() {
    }
    
    public AbstractSpaceShips(int xPosition, int yPosition,File texture){
        super(xPosition,yPosition,texture);
    }

    public void boundariesSetter(int width,int height){
        this.width=width;
        this.height=height;

    }

    @Override
    public void keyTyped (KeyEvent e){
        keyPressed = e.getKeyChar();
    }

    @Override
    public void keyPressed (KeyEvent e){
        keyCode = e.getKeyCode();
        movement();
    }

    @Override
    public void keyReleased (KeyEvent e){
        return;
    }

    public char keyPressed;
    public int keyCode;
    protected int width;
    protected int height;
}



