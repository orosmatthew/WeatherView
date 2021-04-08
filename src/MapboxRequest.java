public class MapboxRequest implements TileServerRequest {

    private String accessToken;
    private boolean isDoubleResolution;
    private double latitude;
    private double longitude;
    private int zoom;

    public MapboxRequest(double latitude, double longitude, int zoom, String accessToken, boolean isDoubleResolution) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoom = zoom;
        this.accessToken = accessToken;
        this.isDoubleResolution = isDoubleResolution;
    }

    public MapboxRequest(double latitude, double longitude, int zoom, String accessToken) {
        this(latitude, longitude, zoom, accessToken, false);
    }

    public String getURL() {

        String doubleResolutionString;

        if (isDoubleResolution) {
            doubleResolutionString = "@2x";
        } else {
            doubleResolutionString = "";
        }

        return String.format("https://api.mapbox.com/v4/mapbox.satellite/"
                + getTileNumber(latitude, longitude, zoom)
                + doubleResolutionString
                + ".jpg?access_token="
                + accessToken);
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public int getZoom() {
        return zoom;
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
