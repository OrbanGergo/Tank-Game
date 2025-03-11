import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer extends JFrame {
    private Clip clip;

    public SoundPlayer() {
    }

    public void PlayClick() {
        try {
            //File URL relative to project folder
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/click.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }

    public void PlayBGMusic() {
        try {
            //File URL relative to project folder
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/bgmusic.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(300);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
            System.out.println(e);
        }
    }

    public void StopBGMusic() {
        clip.stop();
    }
}