package Framework.Type;

public class Vector2 {

    public float x;
    public float y;

    public Vector2() {
        this(0, 0);
    }

    public Vector2 add(Vector2 vector) {
        return new Vector2(x + vector.x, y + vector.y);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(float[] array) {
        this.x = array[0];
        this.y = array[1];
    }

    public float[] toArray() {
        float[] array = {x, y};
        return array;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}