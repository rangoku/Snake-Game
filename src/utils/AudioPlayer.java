package utils;

import core.Globals;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private static Clip clip;
    private static AudioInputStream audioInputStream;

    public static void init() {
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(Globals.Config.audioFile).getAbsoluteFile());

            clip = AudioSystem.getClip();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void play() {
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stop() {
        clip.stop();
        clip.close();

    }
}
