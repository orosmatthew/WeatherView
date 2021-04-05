package Framework;

import Framework.Type.Vector2;

import javax.swing.*;
import java.awt.*;

public class Window {

    private JFrame window = new JFrame("");

    private Vector2 windowSize;
    private boolean isResizable;
    private Color backgroundColor;
    private Canvas canvas;

    public Window(Canvas canvas, Vector2 windowSize, boolean isResizable, Color backgroundColor) {
        this.windowSize = windowSize;
        this.isResizable = isResizable;
        this.backgroundColor = backgroundColor;
        this.canvas = canvas;
    }

    public Vector2 getWindowSize() {
        return windowSize;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isResizable() {
        return isResizable;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void createWindow() {
        SwingUtilities.updateComponentTreeUI(window);
        window.getContentPane().add(canvas);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setPreferredSize(new Dimension((int) windowSize.x, (int) windowSize.y));
        window.pack();
        window.setResizable(isResizable);
        window.setVisible(true);
        window.getContentPane().setBackground(backgroundColor);
    }

}