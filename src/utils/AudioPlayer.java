package utils;

import core.Globals;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {

    private static Clip clip;

    private static void init() {
        try {
            var audioInputStream =
                    AudioSystem.getAudioInputStream(new File(Globals.Config.audioFile).getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void play() {
        init();
//        clip.start();
    }

    public static void stop() {
        clip.stop();
        clip.close();

    }
}
