package core;

public class Globals {

    public static class Options {
        public static int speed = 2; // 1 - Slow; 2 - Medium; 3 - Fast; 4 - Extremal
        public static boolean isAudioMuted = false;
    }

    public static class Config {
        public static final String optionsFile = "src/data/options.dat";
        public static final String audioFile = "src/resources/music.wav";
    }

}
