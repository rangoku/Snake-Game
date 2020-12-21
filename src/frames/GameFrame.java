package frames;

import core.Game;
import core.SwingRouter.Router;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GameFrame extends JFrame {

    public GameFrame() {
        initFrame();
    }

    private void initFrame() {

       Router.setMainFrame(this);

       add(new MenuFrame());

        setResizable(false);
        pack();
        
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
