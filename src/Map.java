import Framework.Engine;
import Framework.Object.CanvasObject;
import Framework.Type.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Map extends CanvasObject {

    private final BufferedImage[] images = new BufferedImage[9];
    private final Vector2[] imagePositions = {new Vector2(-1, 1), new Vector2(0, 1), new Vector2(1, 1),
        new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0),
        new Vector2(-1, -1), new Vector2(0, -1), new Vector2(1, -1)};
    private TileServer tileServer;
    private String MapboxAccessToken = "pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ";
    private int tileX;
    private int tileY;
    private int zoom;
    private float speed = 10;

    Vector2 position;

    @Override
    public void init() {

        tileServer = new CachedTileServer();

        position = new Vector2();

        zoom = 7;
        double lat = 41.347881d;
        double lon = -81.808503d;

        int[] tiles = SlippyMapHelper.getTileNumbers(lat, lon, zoom);

        tileX = tiles[0];
        tileY = tiles[1];

        displayTile();
    }

    private void displayTile() {
        for (int i = 0; i < 9; i++) {
            MapTile tile = new MapboxTile(tileX + (int)imagePositions[i].x, tileY + (int)imagePositions[i].y, zoom, MapboxAccessToken, true);
            images[i] = tileServer.getTile(tile);
        }
    }

    @Override
    public void process(double delta) {
        if (Engine.getInput().isKeyPressed(KeyEvent.VK_W)) {
            position.y += speed;
        }
        if (Engine.getInput().isKeyPressed(KeyEvent.VK_S)) {
            position.y -= speed;
        }
        if (Engine.getInput().isKeyPressed(KeyEvent.VK_A)) {
            position.x += speed;
        }
        if (Engine.getInput().isKeyPressed(KeyEvent.VK_D)) {
            position.x -= speed;
        }

        if (position.x > 500) {
            position.x = 0;
            tileX--;
            displayTile();
        }
        if (position.x < -500) {
            position.x = 0;
            tileX++;
            displayTile();
        }
        if (position.y > 500) {
            position.y = 0;
            tileY--;
            displayTile();
        }
        if (position.y < -500) {
            position.y = 0;
            tileY++;
            displayTile();
        }

    }

    @Override
    public void drawObject(Graphics g) {
        for (int i = 0; i < 9; i++) {
            g.drawImage(images[i], (int)(position.x + (500 * imagePositions[i].x)), (int)(position.y + (500 * imagePositions[i].y)), 500, 500, null);
        }
    }


}
