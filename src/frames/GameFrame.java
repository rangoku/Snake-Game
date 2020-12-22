package frames;

import core.SwingRouter.Router;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        initFrame();
    }

    private void initFrame() {

        Router.setMainFrame(this); // set current frame as main to work with him in future

        add(new MenuFrame()); // starts from menu

        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
