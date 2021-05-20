import javax.swing.*;

/**
 * Class in which a Frame is created for a game.
 * @author Andrae Cari
 * @version January 27 2021
 */

public class BoardFrame extends JFrame {
    /**
     * Constructor that creates a BoardFrame object. A panel is set on frame.
     * @param title The name of the application.
     */
    public BoardFrame(String title) {
        ImageIcon icon = new ImageIcon("Images\\icon2.png");
        JOptionPane.showMessageDialog(null, "Welcome to Andrae's Snake Game!\nUse the WASD or the arrow keys to control the snake. Your objective is to eat as many pieces as you can!",
                                      title, JOptionPane.INFORMATION_MESSAGE, icon); //opening dialogue
        BoardPanel panel = new BoardPanel(title);
        this.setContentPane(panel);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}//BoardFrame
