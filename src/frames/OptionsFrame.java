package frames;

import core.Globals;
import core.SwingRouter.Router;
import utils.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class OptionsFrame extends JPanel {

    public OptionsFrame() { init(); }

    public void init() {

        setLayout(new BorderLayout());

        var help = new JLabel("Options");
        help.setForeground(Color.BLACK);
        help.setHorizontalAlignment(SwingConstants.CENTER);
        help.setFont(new Font("default", Font.PLAIN, 22));

        var labels = new Hashtable<Integer, JLabel>();
        var label = new JLabel("Speed");
        label.setForeground(Color.WHITE);
        labels.put(1, label);

        var speed = new JSlider(JSlider.HORIZONTAL, 1, 4, 2);
        speed.setValue(Globals.Options.speed);
        speed.setLabelTable(labels);
        speed.setMinorTickSpacing(1);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speed.setForeground(Color.WHITE);
        speed.setBackground(Color.BLACK);

        var mute = new JCheckBox("Mute ");
        boolean initVal = Globals.Options.isAudioMuted;
        mute.setSelected(initVal);
        mute.setBackground(Color.BLACK);
        mute.setForeground(Color.WHITE);
        mute.setFocusable(false);

        var confirm = new JButton("OK");
        confirm.setForeground(Color.BLACK);
        confirm.setFocusPainted(false);
        confirm.setFont(new Font("default", Font.PLAIN, 18));
        confirm.addActionListener(e -> {
            Globals.Options.speed = speed.getValue();
            Globals.Options.isAudioMuted = mute.isSelected();

            if (initVal != Globals.Options.isAudioMuted) {
                if (Globals.Options.isAudioMuted) {
                    AudioPlayer.stop();
                }
                else {
                    AudioPlayer.play();
                }
            }

            Router.switchFrame(this, new MenuFrame());
        });

        add(help, BorderLayout.NORTH);
        add(speed, BorderLayout.CENTER);
        add(mute, BorderLayout.BEFORE_LINE_BEGINS);
        add(confirm, BorderLayout.SOUTH);
    }
}