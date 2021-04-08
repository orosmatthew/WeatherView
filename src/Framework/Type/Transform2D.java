package Framework.Type;

import java.awt.*;

public class Transform2D {

    private final float[][] matrix = new float[2][3];

    public Transform2D() {
        this(new Vector2(1, 0), new Vector2(0, 1), new Vector2(0, 0));
    }

    public Transform2D(Vector2 x, Vector2 y, Vector2 origin) {
        setX(x);
        setY(y);
        setOrigin(origin);
    }

    public Vector2 getOrigin() {
        Vector2 origin = new Vector2(matrix[0][2], matrix[1][2]);
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        matrix[0][2] = origin.x;
        matrix[1][2] = origin.y;
    }

    public Vector2 getX() {
        Vector2 x = new Vector2(matrix[0][0], matrix[1][0]);
        return x;
    }

    public void setX(Vector2 x) {
        matrix[0][0] = x.x;
        matrix[0][1] = x.y;
    }

    public Vector2 getY() {
        Vector2 y = new Vector2(matrix[0][1], matrix[1][1]);
        return y;
    }

    public void setY(Vector2 y) {
        matrix[0][1] = y.x;
        matrix[1][1] = y.y;
    }

    public Vector3 transform(Vector3 vector) {
        float[] b = vector.toArray();
        float[] product = new float[3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                product[i] += matrix[i][j] * b[j];
            }
        }
        return new Vector3(product);
    }

    public Vector2 transformPosition(Vector2 position) {
        Vector3 vector = transform(new Vector3(position.x, position.y, 1));
        return new Vector2(vector.x, vector.y);
    }

    public Vector2 transformDirection(Vector2 direction) {
        Vector3 vector = transform(new Vector3(direction.x, direction.y, 0));
        return new Vector2(vector.x, vector.y);
    }

    public Polygon transformPolygon(Polygon polygon) {
        int[] xPointsTransformed = new int[polygon.npoints];
        int[] yPointsTransformed = new int[polygon.npoints];
        for (int i = 0; i < polygon.npoints; i++) {
            Vector2 transformedPoint = transformPosition(new Vector2(polygon.xpoints[i], polygon.ypoints[i]));
            xPointsTransformed[i] = (int) transformedPoint.x;
            yPointsTransformed[i] = (int) transformedPoint.y;
        }
        return new Polygon(xPointsTransformed, yPointsTransformed, polygon.npoints);
    }

    public void translate(Vector2 vector) {
        Vector2 transformedVector = transformDirection(vector);
        setOrigin(getOrigin().add(transformedVector));
    }
}