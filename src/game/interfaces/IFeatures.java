package src.game.interfaces;
import java.awt.*;

/**
 * This is the interface for features that will have to be 
 * drawn using javas native Graphics lib
 * and will have to have movements; implementation on movement and drawShapes
 * will be delegated to abstract and concrete classes
 * */
public interface IFeatures{

    public void movement();

    public void collision(Rectangle r);

    public void boundaries();
}