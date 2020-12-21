package frames;

import core.Globals;
import core.SwingRouter.Router;

import javax.swing.*;
import java.awt.*;

public class OptionsFrame extends JPanel {

    public OptionsFrame() { init(); }

    public void init() {

        setLayout(new BorderLayout());

        var help = new JLabel("Set speed");
        help.setForeground(Color.BLACK);
        help.setHorizontalAlignment(SwingConstants.CENTER);
        help.setFont(new Font("default", Font.PLAIN, 22));

        var speed = new JSlider(JSlider.HORIZONTAL, 1, 4, 2);
        speed.setMinorTickSpacing(1);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speed.setForeground(Color.WHITE);
        speed.setBackground(Color.BLACK);

        var confirm = new JButton("OK");
        confirm.setForeground(Color.BLACK);
        confirm.setFocusPainted(false);
        confirm.setFont(new Font("default", Font.PLAIN, 18));
        confirm.addActionListener(e -> {
            Globals.speed = speed.getValue();
            Router.switchFrame(this, new MenuFrame());
            System.out.println(Globals.speed);
        });

        add(help, BorderLayout.NORTH);
        add(speed, BorderLayout.CENTER);
        add(confirm, BorderLayout.SOUTH);
    }
}