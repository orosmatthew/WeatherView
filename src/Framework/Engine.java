package Framework;

import Framework.Object.CanvasObject;
import Framework.Object.EngineObject;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Engine extends Thread {

    private static Window window;
    private static Canvas canvas;
    private static Input input;
    private static Engine instance;
    private final int FPS = 144;
    private final int UPS = 60;
    private final ArrayList<EngineObject> engineObjects;

    public ArrayList<EngineObject> getEngineObjects() {
        return engineObjects;
    }

    private boolean isRunning;
    private boolean isPrintingTimings;

    public Engine(Window window, Canvas canvas) {
        Engine.window = window;
        Engine.canvas = canvas;
        this.engineObjects = new ArrayList<>();
    }

    public static void setWindow(Window win) {
        window = win;
    }

    public static Window getWindow() {
        return window;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static Input getInput() {
        return input;
    }

    public static Engine getInstance() {
        if (instance == null) {
            canvas = window.getCanvas();
            input = canvas.getInput();
            window.createWindow();
            canvas.addKeyListener(window.getCanvas().getInput());
            canvas.setFocusable(true);
            instance = new Engine(window, canvas);
        }
        return instance;
    }

    public final void addEngineObject(EngineObject engineObject) {
        if (!engineObjects.contains(engineObject)) {
            engineObjects.add(engineObject);
        }
        engineObject.init();
    }

    public final void addCanvasObject(CanvasObject canvasObject) {
        canvas.addCanvasObject(canvasObject);
        addEngineObject(canvasObject);
    }

    public final void stopLoop() {
        isRunning = false;
    }

    private final void startLoop() {

        isRunning = true;

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (isRunning) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                update(deltaU);
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                render(deltaF);
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (isPrintingTimings) {
                    System.out.println(String.format("Update Rate: %s, Framerate: %s", ticks, frames));
                }
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    private final void update(double delta) {

        for (EngineObject o : engineObjects) {
            o.process(delta);
        }

        input.resetJustPressed();
    }

    private final void render(double delta) {
        canvas.paintImmediately(0, 0, (int) window.getWindowSize().x, (int) window.getWindowSize().y);
    }

    @Override
    public void run() {
        instance.startLoop();
    }

}