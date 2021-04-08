import Framework.Object.CanvasObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends CanvasObject {

    BufferedImage image;
    TileServer tileServer;
    String MapboxAccessToken = "pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ";

    @Override
    public void init() {

        tileServer = new CachedTileServer();

        int zoom = 10;
        double lat = 41.347881d;
        double lon = -81.808503d;

        int[] tiles = SlippyMapHelper.getTileNumbers(lat, lon, zoom);

        TileServerRequest request = new MapboxRequest(tiles[0], tiles[1], tiles[2], MapboxAccessToken, true);


        image = tileServer.getTile(request);

    }

    @Override
    public void drawObject(Graphics g) {
        g.drawImage(image, 0, 0, 500, 500, null);
    }


}
