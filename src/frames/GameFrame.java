package frames;

import core.Board;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        initFrame();
    }

    private void initFrame() {

        add(new MenuFrame());



//        add(new Board());

        setResizable(false);
        pack();
        
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
