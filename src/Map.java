import Framework.Engine;
import Framework.Object.CanvasObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Map extends CanvasObject {

    BufferedImage image;
    TileServer tileServer;
    String MapboxAccessToken = "pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ";
    int tileX;
    int tileY;
    int zoom;

    @Override
    public void init() {

        tileServer = new CachedTileServer();

        zoom = 10;
        double lat = 41.347881d;
        double lon = -81.808503d;

        int[] tiles = SlippyMapHelper.getTileNumbers(lat, lon, zoom);

        tileX = tiles[0];
        tileY = tiles[1];

        displayTile();
    }

    private void displayTile() {
        TileServerRequest request = new MapboxRequest(tileX, tileY, zoom, MapboxAccessToken, true);
        image = tileServer.getTile(request);
    }

    @Override
    public void process(double delta) {
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_UP)) {
            tileY--;
            displayTile();
        }
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_DOWN)) {
            tileY++;
            displayTile();
        }
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_LEFT)) {
            tileX--;
            displayTile();
        }
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_RIGHT)) {
            tileX++;
            displayTile();
        }
    }

    @Override
    public void drawObject(Graphics g) {
        g.drawImage(image, 0, 0, 500, 500, null);
    }


}
