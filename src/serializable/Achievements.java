package serializable;

import java.io.Serializable;

public class Achievements implements Serializable {

    private int score;

    public Achievements(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
