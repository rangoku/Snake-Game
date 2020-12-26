package frames;

import core.Game;
import core.SwingRouter.Router;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOverFrame {

    /**
     * @param game - frame to change
     * @param g2   - Graphics2D instance to draw in new frame
     */
    public static void gameOver(Game game, Graphics2D g2) {
        game.removeAll();

        String msg = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 16);
        FontMetrics metrics = game.getFontMetrics(font);

        g2.setColor(Color.RED);
        g2.setFont(font);
        g2.drawString(msg, (game.getWidth() - metrics.stringWidth(msg)) / 2, game.getHeight() / 2);

        game.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Router.switchFrame(game, new MenuFrame());
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }
}
