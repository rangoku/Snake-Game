package frames;

import core.Board;

import java.awt.*;

public class GameOverFrame {

    /**
     * @param game - frame to change
     * @param g2   - Graphics2D instance to draw in new frame
     */
    public static void gameOver(Board game, Graphics2D g2) {
        game.removeAll();

        String msg = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 16);
        FontMetrics metrics = game.getFontMetrics(font);

        g2.setColor(Color.RED);
        g2.setFont(font);
        g2.drawString(msg, (game.getWidth() - metrics.stringWidth(msg)) / 2, game.getHeight() / 2);

    }
}
