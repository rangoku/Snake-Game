package core;

import java.io.Serializable;

public class Options implements Serializable {
    private int speed;
    private boolean isAudioMuted;

    public Options(int speed, boolean isAudioMuted) {
        this.speed = speed;
        this.isAudioMuted = isAudioMuted;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAudioMuted() {
        return isAudioMuted;
    }
}
