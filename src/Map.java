import Framework.Object.CanvasObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends CanvasObject {

    BufferedImage image;
    TileServer tileServer;

    @Override
    public void init() {

        tileServer = new MapboxTileServer("pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ", true);

        int zoom = 10;
        double lat = 41.373034d;
        double lon = -81.847799d;

        image = tileServer.getTile(tileServer.getURL(lat, lon, zoom));

    }

    @Override
    public void drawObject(Graphics g) {
        g.drawImage(image, 0, 0, 500, 500, null);
    }


}
