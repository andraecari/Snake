import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Class where all the objects are put together and runs the game. Graphics are executed and contained within the panel created.
 * @author Andrae Cari
 * @version January 27 2021
 */

public class BoardPanel extends JPanel implements ActionListener {
    
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int SIZE = 25;
    private static final int DELAY = 75;
    private static boolean running;
    private static Timer timer;
    private Food apple = new Food(SIZE);
    private Snake snake = new Snake(SIZE);
    private ScoreBoard scores = new ScoreBoard();
    private SoundPlayer sound = new SoundPlayer();

    /**
     * Contructor creates panel component that will be contained within frame.
     * @param title The name of the application.
     */
    public BoardPanel(String title) {
        this.setOpaque(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        start(); //start game when object is istantiated
    }

    /** 
     * Over ridden message that works along the Timer class. Method contains code that gets executed repeatedly according to the DELAY.
     * @param event An ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent event) { //tasks the timer executes
        if (running) {
            snake.move();
            if (snake.collision()) {
                running = false;
                sound.play(sound.getColSounds());
            }
            if (snake.checkFood(apple.getX(),apple.getY())) {
                snake.addBodyPart(1);
                apple.spawn(WIDTH, HEIGHT);
                scores.addScore(1);
                sound.play(sound.getEatSounds());
            }
        } else {
            timer.stop();
            scores.addToScoreList(scores.getScore());
            scores.save();
        }
        repaint();
    }

    /** 
     * Overridden method that handles the visual/graphical components of the game.
     * @param g Graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            apple.draw(g);
            snake.draw(g);
            scores.drawScore(g);
        } else {
            scores.draw(g);
        }
    }

    /**
     * Function that runs the required parts in order begin the game.
     */
    private void start() {
        System.out.println("Starting...");
        timer = new Timer(DELAY, this);
        running = true;
        apple.spawn(WIDTH, HEIGHT);
        timer.start();
    }

    /**
     * Function that resets the score and body of the snake. The start() function is re-executed here.
     */
    private void restart() {
        System.out.println("Resetting game...");
        scores.resetScore();
        snake.resetBody();
        start();
    }
    
    /** 
     * Accessor method to get the width of the board.
     * @return int. The width of the board.
     */
    public static int getBoardWidth() {
        return WIDTH;
    }
    
    /** 
     * Accessor method to get the height of the board.
     * @return int. The height of the board.
     */
    public static int getBoardHeight() {
        return HEIGHT;
    }
    
    /**
     * Class that contains the keyPressed method.
     */
    public class MyKeyAdapter extends KeyAdapter{
        /**
         * Overridden method that responds according to a specific key press.
         * @param event KeyEvent object.
         */
		@Override
		public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();
            if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !snake.directionEquals('L')) 
                snake.setDirection('R');
            if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !snake.directionEquals('R')) 
                snake.setDirection('L');
            if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !snake.directionEquals('D')) 
                snake.setDirection('U');
            if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !snake.directionEquals('U')) 
                snake.setDirection('D');
            if (key == KeyEvent.VK_ENTER && running == false)
                restart();
        }
    }//MyKeyAdapter
    
}//BoardPanel