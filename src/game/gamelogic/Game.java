package src.game.gamelogic;

import src.game.display.*;
import src.game.sprites.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Game implements KeyListener {
    /**
     * @TODO Implementation of Game Logic and rules that
     * will govern construction and destruction of objects,
     * levels, visuals and score 
     */
    public Game(){}

    public Game(Canvas canvas, FrameBackground background, AbstractSpaceShips spaceship,KillerAsteroids[] gameKillerAsteroids,
                AbstractSpaceBoy[] spaceboys){
        this.canvas = canvas;
        this.background = background;
        this.spaceship = spaceship;
        this.gameKillerAsteroids = gameKillerAsteroids;
        this.gameSpaceBoy = spaceboys;
        killerAsteroidsSetter();
        spaceBoySetter();
        spaceship.boundariesSetter(canvas.getWidth(),canvas.getHeight());

        this.levels = new Level(canvas.getWidth()/2,0);

        score = new ScoreBox(canvas.getWidth()-200,canvas.getHeight()-130 );
    }
    /**
     * draw the game
     * @param g
     */
    public void drawGame(Graphics g){

       background.drawBackground(g);
       drawArrayOfNPC(g, gameSpaceBoy);
       drawArrayOfNPC(g, gameKillerAsteroids);;
       spaceship.drawShapes(g);
       testCollisions();
       gameScore(g);
       changeLevel(g);
       levels.drawLevel(g);
        endGame(g);
    }
    /**
     * changes the level if
     * all the spaceboys/ astrounauts are
     * collected
     * @param g
     */
    public void changeLevel(Graphics g){
        if(allCollectedSpaceBoys()) {
            gameWait(g);
        }
    }
    /**
     * will redraw screen if all
     * the spaceboys/ astrounauts are collected
     * 
     * @param g
     */
    public void gameWait(Graphics g){
            drawArrayOfNPC(g,gameSpaceBoy);
            spaceBoySetter();
            levels.nextLevel();
            gameKillerAsteroids=levels.levelAsteroids();
            killerAsteroidsSetter();
            background.nextImage();
            score.resetScore();
            this.spaceship.xPosition=(int)(canvas.getWidth()*.9);
            this.spaceship.yPosition=(int)(canvas.getHeight()*.5);
    }
    /**
     * draw the game score
     * @param g
     */
    public void gameScore(Graphics g){
        score.drawShapes(g);
        score.drawScore(g);
    }

    /**
     * if the spaceship "Explodes" load
     * the endgame screen
     * if the final level is completed
     * load the end game screen
     * @param g
     */
    public void endGame(Graphics g){
        if(spaceship.isDead){
            EndGame end=new EndGame(0,0,new File("Textures/GameOverScreen.png"));
            end.drawShapes(g);
            Font scoreFont=new Font("monospaced Bold",Font.ITALIC,70);
            g.setFont(scoreFont);
            g.setColor(Color.green);
            g.drawString("Press SpaceBar To Reset",canvas.getWidth()/5,canvas.getHeight()/3);
        }
        if(levels.currentLevel == 6){
            EndGame end=new EndGame(0,0,new File("Textures/Victory.jpg"));
            end.drawShapes(g);
            Font scoreFont=new Font("monospaced",Font.BOLD,130);
            g.setFont(scoreFont);
            g.setColor(Color.pink);
            g.drawString("You Won",canvas.getWidth()/3,canvas.getHeight()/2);
        }
    }
    /**
     * resets the level of the game
     */
    public void resetLevel(){
        background.resetDirectory();
        levels.resetLevel();
        spaceBoySetter();
        gameKillerAsteroids=levels.levelAsteroids();
        killerAsteroidsSetter();
        score.resetScore();
    }
    /**
     * set array of KillerAsteroids for the level
     */
    public void killerAsteroidsSetter(){
        int width=((int)(canvas.getWidth()/2) % canvas.getWidth())-canvas.getWidth();
        int height=((int)(canvas.getHeight()/2) % canvas.getHeight())-canvas.getHeight();
        for(int i =0; i<gameKillerAsteroids.length;i++){
            gameKillerAsteroids[i]=new KillerAsteroids((int)(Math.random()*(Math.abs(width))),
                    (int)(Math.random()*(Math.abs(height))));
                    gameKillerAsteroids[i].screenSetter(canvas);
        }
    }
    /**
     * set array of Spaceboys for the level
     */
    public void spaceBoySetter(){
        for(int i =0; i<gameSpaceBoy.length;i++){
            gameSpaceBoy[i]=new SpaceBoy((int)(Math.random()*(canvas.getWidth()-100)),
                    (int)(Math.random()*(canvas.getHeight()-150)));
        }
    }

    @Override
    public void keyTyped (KeyEvent e){
        keyPressed = e.getKeyChar();
    }

    @Override
    public void keyPressed (KeyEvent e){
        keyCode = e.getKeyCode();
    }

    @Override
    public void keyReleased (KeyEvent e){
        return;
    }


    /**
     * if all astronauts/ spaceboys are collected
     * changeLevel, otherwise we continue 
     * on the same level
     * @return
     */
    private boolean allCollectedSpaceBoys(){
        boolean changeLevel = true;
        for(AbstractSpaceBoy spaceBoys : gameSpaceBoy){
            if(spaceBoys.getClass() == SpaceBoy.class){
                changeLevel = false;
            }
        }
        return changeLevel;
    }

    private void testCollisions(){
        testAsteroidCollision();
        testSpaceBoyCapture();
    }
    /**
     * test for collision and 
     * make spaceship "Explode"
     * if there is a collision with the spaceship
     */
    private void testAsteroidCollision(){
        for(int i=0; i<gameKillerAsteroids.length;i++){
            gameKillerAsteroids[i].movement();
            spaceship.collision(gameKillerAsteroids[i].boundary);
            if(spaceship.isDead){
                spaceship=new DeadSpaceShip(spaceship.xPosition, spaceship.yPosition) ;
            }
        }
    }
    /**
     * test for collision and 
     * make astronauts "disapper"
     * if there is a collision with the spaceship
     */
    private void testSpaceBoyCapture(){
        for(int i=0; i<gameSpaceBoy.length;i++){
            gameSpaceBoy[i].collision(spaceship.boundary);
            if(gameSpaceBoy[i].isDead){
                gameSpaceBoy[i]=new DeadSpaceBoy(gameSpaceBoy[i].xPosition,gameSpaceBoy[i].yPosition);
                score.addScore();
            }
        }
    }
    /**
     * calls the draw function of any array of 
     * characters we pass to it 
     * @param g
     * @param characters
     */
    private void drawArrayOfNPC(Graphics g, Characters[] characters){
        for(Characters NPC : characters){
            NPC.drawShapes(g);
        } 
    }
    
    public Canvas canvas;
    public FrameBackground background;
    public AbstractSpaceBoy[] gameSpaceBoy;
    public KillerAsteroids[] gameKillerAsteroids;
    public AbstractSpaceShips spaceship; 
    public char keyPressed;
    public int keyCode;
    protected Level levels;
    private ScoreBox score;
}
 class Level extends Characters{
    public Level(int xPosition,int yPosition){
        super(xPosition, yPosition, new File("Textures/Bar.jpg"));

        currentLevel = 1;
    }

    public void nextLevel(){
        currentLevel += 1;

    }

    public void resetLevel(){
        currentLevel=1;
    }

    protected KillerAsteroids[] levelAsteroids(){
        return new KillerAsteroids[5*currentLevel];
    }
    /**
     * draws the level title card
     * top center of the screen
     * @param g
     */
    public void drawLevel(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);
        Font scoreFont=new Font("californian FB",Font.BOLD,15);
        g.setFont(scoreFont);
        g.drawString("level: "+String.valueOf(currentLevel),xPosition+img.getWidth(null)/4,
                (int)(yPosition+img.getHeight(null)*.75));
    }

    protected int currentLevel;
}

class EndGame extends Sprite{

    EndGame(int xPosition,int yPosition,File file){
        super(xPosition,yPosition,file);
    }

    /**draws the end game title card
     * image
     */
    @Override
    public void drawShapes(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);

    }
}

