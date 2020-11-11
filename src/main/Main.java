package main;

import frames.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });

    }
}
