package core;

import java.io.Serializable;

public class Options implements Serializable {
    private int speed;

    public Options(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
