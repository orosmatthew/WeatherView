package Framework;

import Framework.Object.CanvasObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JComponent {

    private ArrayList<CanvasObject> canvasObjects = new ArrayList<>();

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
}