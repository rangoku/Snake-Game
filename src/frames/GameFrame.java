package frames;

import core.Globals;
import core.Options;
import core.SwingRouter.Router;
import utils.OptionsFile;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {

    public GameFrame() {
        initFrame();
    }

    private void initFrame() {

        Router.setMainFrame(this); // set current frame as main to work with him in future

        Options fromFile = OptionsFile.deserialize();
        Globals.speed = fromFile == null ? Globals.speed : fromFile.getSpeed();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                OptionsFile.serialize(new Options(Globals.speed));
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
