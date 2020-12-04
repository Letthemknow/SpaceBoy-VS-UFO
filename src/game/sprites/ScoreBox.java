package src.game.sprites;

import java.awt.*;
import java.io.File;

public class ScoreBox extends Sprite{
    public ScoreBox(int xPosition, int yPosition){
        super(xPosition, yPosition, new File("Textures/scorebox.png"));
        gameScore = Score.getInstance();
    }
    
    public void drawScore(Graphics g){
        g.drawImage(img,xPosition,yPosition,null);
        Font scoreFont=new Font("californian FB",Font.BOLD,15);
        g.setFont(scoreFont);
        g.drawString("Score : " + String.valueOf(gameScore.getScore()), xPosition+115, yPosition+40);
    }

    public int getScore(){
        return gameScore.getScore();
    }

    public void addScore(){
        gameScore.addScore();
    }

    public void resetScore(){
        gameScore.resetScore();
    }
    
    public Score gameScore;
}

class Score{
    private Score(){ 
        resetScore();
    }

    /**
     * get instance of score
     * @return score instance
     */
    static Score getInstance(){
        return scoreInstance != null ? scoreInstance : new Score();
    }

    /**
     *reset the game score;
     */
    void resetScore(){
        gameScore = 0;
    }

    /**
     * add score
     */

    void addScore(){
        gameScore += 10;
    }

    /**
     *
     * @return the game score
     */

    int getScore(){
        return gameScore;
    }

    /**
     * attributes of the score box
     */
    private static Score scoreInstance;
    private int gameScore;
}
