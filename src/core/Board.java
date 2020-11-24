package core;

import frames.GameOverFrame;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO -> menu & mega-ball
// TODO.menu : 1 - Start game | 2 - Settings | 3 - Exit |


public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH  = 600;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = B_WIDTH * B_HEIGHT - 1;

    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection  = false;
    private boolean rightDirection = true;
    private boolean upDirection    = false;
    private boolean downDirection  = false;

    private boolean inGame = true;

    private Timer timer;

    private Image body;
    private Image apple;
    private Image head;

    /**
     * speed of snake
     */
    private static final int MEDIUM = 120;
    private static final int SLOW = MEDIUM * 2;
    private static final int FAST = MEDIUM / 2;
    private static final int EXTREMAL = FAST - MEDIUM / 6;



    public Board() {
        initBoard();
    }

    private void initBoard() {

        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        addKeyListener(new KeyboardHandler());

        loadImages();
        initGame();
    }

    private void loadImages() {
        head  = ImageLoader.loadImage("src/resources/snake/head.png");
        body = ImageLoader.loadImage("src/resources/snake/body.png");
        apple = ImageLoader.loadImage("src/resources/apple.png");
    }

    private void initGame() {

        dots = 3;

        locateApple();

        int DELAY = MEDIUM;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing((Graphics2D) g);
    }

    private void doDrawing(Graphics2D g2) {


        if (inGame) {

            g2.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g2.drawImage(head, x[z], y[z], this);
                } else {
                    g2.drawImage(body, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameOver(g2);
        }
    }

    private void gameOver(Graphics2D g2) {
        GameOverFrame.gameOver(this, g2);
    }

    /**
     * checks if apple was eaten
     * and locate new apple
     */
    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        else if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        else if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        else /* DownDirection */ {
            y[0] += DOT_SIZE;
        }
    }

    /**
     * checks collision
     */
    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            // check self-collision
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        // check collision with board
        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        // if (collision) => game stops
        if (!inGame) {
            timer.stop();
        }
    }

    /**
     * locate apple on rand position
     */
    private void locateApple() {

        int RAND_POS = 29;
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            if (inGame) move();
        }

        repaint();
    }

    private class KeyboardHandler extends KeyAdapter {

        /**
         * set direction to move
         * @param e - KeyEvent
         */
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
