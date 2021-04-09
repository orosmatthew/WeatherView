package Framework;

import Framework.Object.CanvasObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JComponent {

    private final ArrayList<CanvasObject> canvasObjects = new ArrayList<>();
    private Input input;

    public Canvas() {

    }

    public Canvas(Input input) {
        this.input = input;
    }

    public void addCanvasObject(CanvasObject canvasObject) {
        if (!canvasObjects.contains(canvasObject)) {
            canvasObjects.add(canvasObject);
        }
    }

    @Override
    public void paint(Graphics g) {
        for (CanvasObject o : canvasObjects) {
            o.drawObject(g);
        }
    }

    public Input getInput() {
        return input;
    }
}