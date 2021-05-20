import java.io.*;
import javax.sound.sampled.*;
import java.util.*;

/**
 * This class represents an object that plays sound.
 * @author Andrae Cari
 * @version January 27 2021
 */

public class SoundPlayer {

    private static Random r = new Random();

    /** 
     * Method that plays audio clips.
     * @param filename The link/file directory of a sound(.wav) file.
     * @throws Exception When program cannot locate given filename.
     */
    public void play(String filename) {
        try {
            File musicPath = new File(filename);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception ex) {
            System.out.println("Can't find file");
        }
    }
    
    /** 
     * Accessor method that randomly selects and gets a link to a collision sound(.wav) file.
     * @return Link of a sound(.wav) file.
     */
    public String getColSounds() {
        String link = "";
        int num = r.nextInt(4) + 1;
        switch (num) {
            case 1:
                link = "Sound Effects\\collision(DEEP OUCH).wav";
                break;
            case 2:
                link = "Sound Effects\\collision(NI CHAN).wav";
                break;
            case 3:
                link = "Sound Effects\\collision(ONI CHANN).wav";
                break;
            case 4:
                link = "Sound Effects\\collision(YAMETE).wav";
                break;
        }
        return link;
    }
    
    /** 
     * Accessor method that randomly selects and gets a link to a eating sound(.wav) file.
     * @return Link of a sound(.wav) file.
     */
    public String getEatSounds() {
        String link = "";
        int num = r.nextInt(3) + 1;
        switch (num) {
            case 1:
                link = "Sound Effects\\eat(DEEP NOM).wav";
                break;
            case 2:
                link = "Sound Effects\\eat(UWU NOM).wav";
                break;
            case 3:
                link = "Sound Effects\\eat(UWU NOM2).wav";
                break;
        }
        return link;
    }

}//SoundPlayer
