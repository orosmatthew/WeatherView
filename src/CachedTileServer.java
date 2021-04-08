import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CachedTileServer extends TileServer {

    private Path cachePath;

    public CachedTileServer() {
        this(".cache");
    }

    public CachedTileServer(String cachePath) {
        this.cachePath = Paths.get(cachePath);
    }

    @Override
    public BufferedImage getTile(TileServerRequest request) {

        if (!Files.exists(cachePath)) {
            try {
                Files.createDirectory(cachePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(cachePath.toString(), getCachedTileName(request) + ".jpg");
        BufferedImage image;

        if (Files.exists(Paths.get(cachePath.toString(), getCachedTileName(request) + ".jpg"))) {
            try {
                image = ImageIO.read(file);
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            image = super.getTile(request);
            try {
                ImageIO.write(image, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

    }

    private static String getCachedTileName(TileServerRequest request) {
        return String.format("%f_%f_%d", request.getLatitude(), request.getLongitude(), request.getZoom());
    }
}