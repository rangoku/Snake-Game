package core;

public class Globals {

    public static class Options {
        public static int speed = 2; // 1 - Slow; 2 - Medium; 3 - Fast; 4 - Extremal
        public static boolean isAudioMuted = false;

        public static void setOptions(serializable.Options o) {
            speed = o == null ? speed : o.getSpeed();
            isAudioMuted = o == null ? isAudioMuted : o.isAudioMuted();
        }
    }

    public static class Achievements {
        public static int score;

        public static void setAchievements(serializable.Achievements a) {
            score = a == null ? 0 : a.getScore();
        }
    }

    public static class Config {
        public static final String optionsFile = "src/data/options.dat";
        public static final String audioFile = "src/resources/music/music.wav";
        public static final String achievementsFile = "src/data/achievements.dat";
    }



}
