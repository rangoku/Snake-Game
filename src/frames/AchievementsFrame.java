package frames;

import core.Globals;
import core.SwingRouter.Router;

import javax.swing.*;
import java.awt.*;

public class AchievementsFrame extends JPanel {

    public AchievementsFrame() {
        init();
    }

    private void init() {

        setLayout(new BorderLayout());

        setBackground(Color.BLACK);

        var label = new JLabel("Best score: " + Globals.Achievements.score);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("default", Font.PLAIN, 20));

        var confirm = new JButton("OK");
        confirm.setForeground(Color.BLACK);
        confirm.setFocusPainted(false);
        confirm.setFont(new Font("default", Font.PLAIN, 18));
        confirm.addActionListener(e -> Router.switchFrame(this, new MenuFrame()));

        add(label, BorderLayout.CENTER);
        add(confirm, BorderLayout.SOUTH);

    }

}
