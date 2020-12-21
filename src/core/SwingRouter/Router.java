package core.SwingRouter;

import javax.swing.*;

public class Router {

    private static JFrame mainFrame;

    public static void setMainFrame(JFrame mainFrame) {
        Router.mainFrame = mainFrame;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static void switchFrame(JPanel switchFrom, JPanel switchOn)  {
        switchFrom.removeAll();
        switchFrom.setVisible(false);
        mainFrame.add(switchOn);
        switchFrom.transferFocus();
    }
}
