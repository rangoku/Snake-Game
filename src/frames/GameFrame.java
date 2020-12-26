package frames;

import core.Globals;
import serializable.Options;
import core.SwingRouter.Router;
import utils.AudioPlayer;
import utils.OptionsFile;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {

    public GameFrame() {
        initFrame();
    }

    private void initFrame() {

        // set current frame as main to work with it later
        Router.setMainFrame(this);

        // init audio player
        AudioPlayer.init();

        // get saved options
        Globals.Options.setOptions(OptionsFile.deserialize());

        if (!Globals.Options.isAudioMuted) {
            AudioPlayer.play();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                OptionsFile.serialize(new Options(Globals.Options.speed, Globals.Options.isAudioMuted));
            }
        });

        add(new MenuFrame()); // starts from menu

        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
