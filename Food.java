import java.util.*;
import java.awt.*;

/**
 * This class represents a Food object.
 * @author Andrae Cari
 * @version January 27 2021
 */

public class Food{

    private static Random r = new Random();
    private static int xPos;
    private static int yPos;
    private int size;
    
    /**
     * Constructor creates Food object with given unit size.
     * @param size Unit size of the object.
     */
    public Food(int size) {
        this.size = size;
    }
    
    /** 
     * Method that randomly generates an x and y position for the food object within the board.
     * @param width The width of the board.
     * @param height The height of the board.
     */
    public void spawn(int width, int height) {
        xPos = r.nextInt((int)(width/size))*size;
        yPos = r.nextInt((int)(height/size))*size;
    }
    
    /** 
     * Accessor method to get the x position of the food object.
     * @return X position of the food object.
     */
    public int getX() {
        return xPos;
    }
    
    /** 
     * Accessor method to get the y position of the food object.
     * @return Y position of the food object.
     */
    public int getY() {
        return yPos;
    }
    
    /** 
     * Method to be called by an object to draw itself on the board.
     * @param g Graphics object.
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(xPos, yPos, size, size);
    }

}//Food