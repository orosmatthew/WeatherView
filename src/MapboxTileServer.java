public class MapboxTileServer extends TileServer {

    private String accessToken;
    private boolean isDoubleResolution;

    public MapboxTileServer(String accessToken, boolean isDoubleResolution) {
        this.accessToken = accessToken;
        this.isDoubleResolution = isDoubleResolution;
    }

    public MapboxTileServer(String accessToken) {
        this(accessToken, false);
    }

    @Override
    public String getURL(double lat, double lon, int zoom) {

        String doubleResolutionString;

        if (isDoubleResolution) {
            doubleResolutionString = "@2x";
        } else {
            doubleResolutionString = "";
        }

        return String.format("https://api.mapbox.com/v4/mapbox.satellite/"
                + getTileNumber(lat, lon, zoom)
                + doubleResolutionString
                + ".jpg?access_token="
                + accessToken);
    }
}
