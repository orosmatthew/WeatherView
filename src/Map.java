import Framework.Object.CanvasObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Map extends CanvasObject {

    BufferedImage image;

    @Override
    public void init() {

        int zoom = 10;
        double lat = 41.373034d;
        double lon = -81.847799d;

        URL url = null;

        try {
            url = new URL("https://api.mapbox.com/v4/mapbox.satellite/" + getTileNumber(lat, lon, zoom) + "@2x.jpg?access_token=pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void drawObject(Graphics g) {
        g.drawImage(image, 0, 0, 500, 500, null);
    }

    public static String getTileNumber(final double lat, final double lon, final int zoom) {
        int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
        int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1 << zoom));
        if (xtile < 0)
            xtile = 0;
        if (xtile >= (1 << zoom))
            xtile = ((1 << zoom) - 1);
        if (ytile < 0)
            ytile = 0;
        if (ytile >= (1 << zoom))
            ytile = ((1 << zoom) - 1);
        return ("" + zoom + "/" + xtile + "/" + ytile);
    }

}
