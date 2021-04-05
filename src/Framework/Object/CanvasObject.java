package Framework.Object;

import Framework.Type.Transform2D;

import java.awt.*;

public abstract class CanvasObject extends EngineObject {

    public Transform2D transform = new Transform2D();

    public Transform2D getTransform2D() {
        return transform;
    }

    public void setTransform2D(Transform2D transform) {
        this.transform = transform;
    }

    public void drawObject(Graphics g) {

    }

}