package util;

public class Location {

    private static final Location[] locations = {
            new Location(20.8f, -156.4f, "United States", "Kahului"),
            new Location(32.4f, -116.2f, "United States", "San Diego"),
            new Location(39.8f, -105.4f, "United States", "Denver"),
            new Location(33.9f, -118.4f, "United States", "Los Angeles"),
            new Location(51.1f, -87.4f, "Canada", "Ontario"),
            new Location(45.1f, -71.4f, "Canada", "Quebec"),
            new Location(42.1f, -75.4f, "United States", "New York"),
            new Location(48.1f, 3.7f, "France", "Canton De Huriel")
    };

    private Float lon;
    private Float lat;
    private String country;
    private String city;

    public Location(Float lon, Float lat, String country, String city) {
        this.lon=lon;
        this.lat=lat;
        this.country=country;
        this.city=city;
    }

    public static Location getRandomLocation() {
        return locations[Random.getRandomInt(0, locations.length)];
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon=lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat=lat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country=country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city=city;
    }
}


