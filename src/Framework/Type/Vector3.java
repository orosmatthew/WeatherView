package Framework.Type;

public class Vector3 {

    public float x;
    public float y;
    public float z;

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float[] array) {
        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
    }

    public float[] toArray() {
        float[] array = {x, y, z};
        return array;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x, y, z);
    }
}