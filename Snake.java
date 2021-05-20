import java.awt.*;

/**
 * This class represents a Snake object.
 * @author Andrae Cari
 * @version January 27 2021
 */

public class Snake {

    private static final int WIDTH = BoardPanel.getBoardWidth();
    private static final int HEIGHT = BoardPanel.getBoardHeight();
    private static int[] x = new int[HEIGHT*WIDTH]; 
    private static int[] y = new int[HEIGHT*WIDTH];
    private static int bodyParts = 3;
    private char direction = 'R';
    private int size;

    /**
     * Constructor that creates snake object with the unit size of each bodypart.
     * @param size The unit size of the components to make up the snake.
     */
    public Snake(int size) {
        this.size = size;
    }

    /**
     * Method that is reiterated to move the snake. Moves the snake one unit everytime.
     */
    public void move() {
        //move consecutive body parts
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        //move head one unit depending on direction
        if (direction == 'R')
            x[0] = x[0] + size;
        if (direction == 'L')
            x[0] = x[0] - size;            
        if (direction == 'U')
            y[0] = y[0] - size;
        if (direction == 'D')
            y[0] = y[0] + size;
    }
    
    /** 
     * Method that checks whether a collision has taken place.
     * @return boolean
     */
    public boolean collision() {
        boolean collision = false;
        //if the head located at the same position as one of its bodyparts
        for (int i = bodyParts; i > 0; i--)
            if((x[0] == x[i]) && (y[0] == y[i]))
                collision = true;
        //if the head is located beyond the borders of the panel
        if (x[0] < 0 || x[0] >= WIDTH)
            collision = true;
        if (y[0] < 0 || y[0] >= HEIGHT)
            collision = true;
        return collision;
    }
    
    /** 
     * Method to check if the head of the snake is located at the same position as the food object.
     * @param foodX X position of food object.
     * @param foodY Y position of food object.
     * @return boolean
     */
    public boolean checkFood(int foodX, int foodY) {
        if (x[0] == foodX && y[0] == foodY)
            return true;
        else 
            return false;
    }
    
    /** 
     * Method to be called by a snake object to draw its body on the board.
     * @param g Graphics object.
     */
    public void draw(Graphics g) {
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) g.setColor(Color.green);
            else g.setColor(new Color(150, 0, 255));
            g.fillRect(x[i], y[i], size, size);
        }
    }
    
    /** 
     * Modifier method that sets the current direction of the snake.
     * @param direction Char value either 'R', 'L', 'D', or 'U', to set the new direction.
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }
    
    /** 
     * Modifier method that adds a certain amount(integer) to be added to the body of the snake.
     * @param i The amount of body parts to be added.
     */
    public void addBodyPart(int i) {
        bodyParts = bodyParts + i;
    }

    /**
     * Method that resets the snake to its default state.
     */
    public void resetBody() {
        bodyParts = 3;
        direction = 'R';
        x[0] = 0;
        y[0] = 0;
    }
    
    /** 
     * Method that checks to see if current direction of snake object is the same as the parameter passed.
     * @param direction Character to be compared to.
     * @return boolean
     */
    public boolean directionEquals(char direction) {
        if (this.direction == direction)
            return true;
        else
            return false;
    }
    
    /** 
     * Accessor method that gets the x position of a specific body part of the snake.
     * @param i Index value of x array/bodypart of snake.
     * @return Integer value of the x position of bodypart of index i.
     */
    public int getX(int i) {
        return x[i];
    }
    
    /** 
     * Accessor method that gets the y position of a specific body part of the snake.
     * @param i Index value of y array/bodypart of snake.
     * @return Integer value of the y position of bodypart of index i.
     */
    public int getY(int i) {
        return y[i];
    }

}//Snake