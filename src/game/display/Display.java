package src.game.display;
// imported display from the src.game package

import src.game.gamelogic.*;
import src.game.sprites.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.BufferStrategy;

public class Display extends Canvas{
    /**
     * constructor
     */
    Display(){
        super();
        this.gameBackground = new File("Images");
        this.spaceship = new Spaceships((int)(screenWidth*.9), (int)(screenHeight*.5));
        this.scoring = new ScoreBox(0, 0);
        this.back = new FrameBackground(gameBackground);
        this.death = new KillerAsteroids[5];
        this.spaceBoy = new AbstractSpaceBoy[10];
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UFO SpaceBoy");
        Display canvas = new Display();
        canvas.createFrame(frame, canvas);
        canvas.wait(canvas);
    }
    /**
     * creates frame and canvas on which 
     * the game will be displayed
     * @param frame
     * @param canvas
     */
    public void createFrame(JFrame frame, Display canvas){
        canvas.setSize(screenWidth, screenHeight);
        game=new Game(canvas, back, spaceship, death, spaceBoy);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setBackground(Color.white);
        frame.add(canvas);
        canvas.addKeyListener(spaceship);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        canvas.setFocusable(true);
    }
    /**
     * 
     * @param g BufferStrategy graphics
     */
    @Override
    public void paint(Graphics g) {
        createBuffer();
        g = bs.getDrawGraphics();
        drawWithGraphics(g);
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }
    /**
     * will control frame refresh of screen
     * and repaint with new buffer image
     * @param canvas
     */
    public void wait(Canvas canvas){
        while(true){
            try{
                Thread.sleep(50);
            }
            catch (Exception exc) {
            }
            canvas.repaint();
        }
    }
/**
 * test for buffer instance and if 
 * the buffer is null 
 * create a buffer and set it to 
 * the BufferStrategy attribute 
 */
    private void createBuffer(){
        if(bs == null){
            createBufferStrategy(3);
        }
        bs = getBufferStrategy();
    }
/**
 * this will draw all graphics with 
 * BufferStrategy graphics
 * @param g buffer graphics
 */
    private void drawWithGraphics(Graphics g){
        game.drawGame(g);
        gameReset();
        g.dispose();
        bs.show();
    }
/**
 * will reset the game back to 
 * level 1.
 */
    public void gameReset(){
        if(game.spaceship.isDead){
            addKeyListener(game);
            if(game.keyCode==32){
               this.game.spaceship=new Spaceships((int)(screenWidth*.9), (int)(screenHeight*.5));
               this.game.spaceship.boundariesSetter(screenWidth,screenHeight);
               addKeyListener(this.game.spaceship);
                game.resetLevel();
            }
        }
    }

    private final int screenWidth=1240;
    private final int screenHeight=720;
    private Game game;
    private BufferStrategy bs;
    public File gameBackground;
    public AbstractSpaceShips spaceship;
    public ScoreBox scoring;
    public FrameBackground back;
    public KillerAsteroids[] death;
    public AbstractSpaceBoy[] spaceBoy;
}
