import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * This class represents a ScoreBoard that reads and writes.
 * @author Andrae Cari
 * @version January 27 2021
 */

public class ScoreBoard implements Serializable{
    
    private static int highScore;
    private static int score = 0;
    private static ArrayList<Integer> scores = new ArrayList<Integer>();
    
    /**
     * Constructor that creates ScoreBoard object. When executed, text file containing the ArrayList with all scores is read.
     * @throws IOException When the file has not been created or cannot be read.
     * @throws ClassNotFoundException When the file reads something other than an object.
     */
    public ScoreBoard() {
        try {
            FileInputStream file = new FileInputStream("scoreList.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            scores = (ArrayList)in.readObject();
            in.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
            scores.add(0);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            scores.add(0);
        }
    }

    /**
     * Method that resets the current score to 0.
     */
    public void resetScore() {
        score = 0;
    }
    
    /** 
     * Modifier method to add a certain integer to the score each time it is executed.
     * @param i int to increment the current score by.
     */
    public void addScore(int i) {
        score = score + i;
    }
    
    /** 
     * Accessor method that gets the current score.
     * @return int value of the current score.
     */
    public int getScore() {
        return score;
    }
    
    /** 
     * Modifier method to add the current score to the ArrayList of scores.
     * @param score The current score.
     */
    public void addToScoreList(int score) {
        scores.add(score);
    }
    
    /**
     * Accessor method that gets the highscore that is stored.
     * @return int of the highest value that is stored in the scores list.
     */
    public int getHighScore() {
        Collections.sort(scores);
        highScore = scores.get(scores.size() - 1);
        return highScore;
    }
    
    /** 
     * Method that creates the graphics that appears at the end of a run. Displays "GAMEOVER", the current and the highscore.
     * @param g Graphics object.
     */
    public void draw(Graphics g) {
        if (score >= getHighScore()) {
            g.setColor(Color.white);
            g.fillRect(171, 269, 255, 40);
            g.setColor(Color.black);
            g.setFont(new Font("Gotham Light", Font.PLAIN, 30));
            g.drawString("New High Score!", 175, 300);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Gotham Bold", Font.PLAIN, 75));
        g.drawString("GAME OVER", 60, 240);

        g.setFont(new Font("Gotham Medium", Font.PLAIN, 30));
        g.drawString("Your Score: " + score, 193, 350);
        g.drawString("High Score: " + getHighScore(), 194, 385);

        g.setFont(new Font("Gotham Light", Font.PLAIN, 17));
        g.drawString("Please exit the game or press the Enter key to restart!", 80, 500);
    }

    /** 
     * Method that creates the score that appears at the top right of the game while running.
     * @param g Graphics object.
     */
    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Gotham Bold", Font.PLAIN, 30));
        g.drawString(Integer.toString(score), 555, 29);
        g.setFont(new Font("Gotham Light", Font.PLAIN, 15));
        g.drawString("SCORE:", 480, 25);
    }

    /**
     * Method that saves the ArrayList of scores to a text file.
     * @throws IOException
     */
    public void save() {
        try {
            FileOutputStream file = new FileOutputStream("scoreList.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(scores);
            out.close();
            file.close();
            System.out.println("Scoreboard has been saved.");

        } catch (IOException ex) {
            System.out.println("IOException is caught.");
        }
    }

}//ScoreBoard