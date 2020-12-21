package frames;

import core.Game;
import core.SwingRouter.Router;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;


public class MenuFrame extends JPanel {

        private List<String> menuItems;
        private String selectMenuItem;
        private String focusedItem;

        private MenuItemPainter painter;
        private Map<String, Rectangle> menuBounds;

        public MenuFrame() {
            setBackground(Color.BLACK);
            painter = new SimpleMenuItemPainter();
            menuItems = new ArrayList<>(25);
            menuItems.add("Start Game");
            menuItems.add("Options");
            menuItems.add("Exit");
            selectMenuItem = menuItems.get(0);

            MouseAdapter ma = new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    String newItem = null;
                    for (String text : menuItems) {
                        Rectangle bounds = menuBounds.get(text);
                        if (bounds.contains(e.getPoint())) {
                            newItem = text;
                            break;
                        }
                    }

                    if (newItem != null) {
                        selectMenuItem = newItem;
                        System.out.println("smi: " + selectMenuItem);
                        switch (selectMenuItem) {
                            case "Start Game":
                                Router.switchFrame(MenuFrame.this, new Game());
                                break;

                            case "Options":
                                Router.switchFrame(MenuFrame.this, new OptionsFrame());
                                break;

                            case "Exit":
                                System.exit(0);

                            default:
                                repaint();
                                break;
                        }
                    }
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    focusedItem = null;
                    for (String text : menuItems) {
                        Rectangle bounds = menuBounds.get(text);
                        if (bounds.contains(e.getPoint())) {
                            focusedItem = text;
                            repaint();
                            break;
                        }
                    }
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma);

            InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "arrowDown");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "arrowUp");

            am.put("arrowDown", new MenuAction(1));
            am.put("arrowUp", new MenuAction(-1));

        }

        @Override
        public void invalidate() {
            menuBounds = null;
            super.invalidate();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600, 600);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (menuBounds == null) {
                menuBounds = new HashMap<>(menuItems.size());
                int width = 0;
                int height = 0;
                for (String text : menuItems) {
                    Dimension dim = painter.getPreferredSize(g2d, text);
                    width = Math.max(width, dim.width);
                    height = Math.max(height, dim.height);
                }

                int x = (getWidth() - (width + 10)) / 2;

                int totalHeight = (height + 10) * menuItems.size();
                totalHeight += 5 * (menuItems.size() - 1);

                int y = (getHeight() - totalHeight) / 2;

                for (String text : menuItems) {
                    menuBounds.put(text, new Rectangle(x, y, width + 10, height + 10));
                    y += height + 10 + 5;
                }

            }
            for (String text : menuItems) {
                Rectangle bounds = menuBounds.get(text);
                boolean isSelected = text.equals(selectMenuItem);
                boolean isFocused = text.equals(focusedItem);
                painter.paint(g2d, text, bounds, isSelected, isFocused);
            }
            g2d.dispose();
        }

        public class MenuAction extends AbstractAction {

            private final int delta;

            public MenuAction(int delta) {
                this.delta = delta;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = menuItems.indexOf(selectMenuItem);
                if (index < 0) {
                    selectMenuItem = menuItems.get(0);
                }
                index += delta;
                if (index < 0) {
                    selectMenuItem = menuItems.get(menuItems.size() - 1);
                } else if (index >= menuItems.size()) {
                    selectMenuItem = menuItems.get(0);
                } else {
                    selectMenuItem = menuItems.get(index);
                }
                repaint();
            }

        }

    private interface MenuItemPainter {

        void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused);

        Dimension getPreferredSize(Graphics2D g2d, String text);

    }

    private static class SimpleMenuItemPainter implements MenuItemPainter {
        public Dimension getPreferredSize(Graphics2D g2d, String text) {
            return g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().getSize();
        }

        @Override
        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused) {
            FontMetrics fm = g2d.getFontMetrics();
            if (isSelected) {
                paintBackground(g2d, bounds, Color.BLUE, Color.WHITE);
            } else if (isFocused) {
                paintBackground(g2d, bounds, Color.MAGENTA, Color.BLACK);
            } else {
                paintBackground(g2d, bounds, Color.DARK_GRAY, Color.LIGHT_GRAY);
            }
            int x = bounds.x + ((bounds.width - fm.stringWidth(text)) / 2);
            int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
            g2d.drawString(text, x, y);
        }

        protected void paintBackground(Graphics2D g2d, Rectangle bounds, Color background, Color foreground) {
            g2d.setColor(background);
            g2d.fill(bounds);
            g2d.setColor(foreground);
            g2d.draw(bounds);
        }

    }

}

