package Framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Input implements KeyListener {

    private final ArrayList<Integer> keysPressed = new ArrayList<>();
    private final ArrayList<Integer> keysJustPressed = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keysJustPressed.contains(e.getKeyCode()) & !keysPressed.contains(e.getKeyCode())) {
            keysJustPressed.add(e.getKeyCode());
        }
        if (!keysPressed.contains(e.getKeyCode())) {
            keysPressed.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keysPressed.contains(e.getKeyCode())) {
            keysPressed.remove(Integer.valueOf(e.getKeyCode()));
        }
    }

    public void resetJustPressed() {
        keysJustPressed.clear();
    }

    public boolean isKeyPressed(int key) {
        return keysPressed.contains(key);
    }

    public boolean isKeyJustPressed(int key) {
        return keysJustPressed.contains(key);
    }
}
