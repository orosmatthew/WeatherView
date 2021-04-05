import Framework.Canvas;
import Framework.Engine;
import Framework.Object.CanvasObject;
import Framework.Type.Vector2;
import Framework.Window;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        Window window = new Window(canvas, new Vector2(500, 500), false, Color.BLACK);
        Engine.setWindow(window);

        CanvasObject map = new Map();
        Engine.getInstance().addCanvasObject(map);

        Engine.getInstance().startLoop();
    }
}
