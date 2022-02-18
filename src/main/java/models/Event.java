package models;

/**
 * Events represent points in family history and contain info related to event
 */
public class Event {

    /**
     * Unique identifier for the event
     */
    private String eventID;

    /**
     * username associated with the event - each event has one user
     */
    private String associatedUsername;

    /**
     * used to identify the user associated with the event
     */
    private String personID;

    /**
     * x coordinate of event on global map
     */
    private Float latitude;

    /**
     * y coordinate of event on global map
     */
    private Float longitude;

    /**
     * country that the event took place in
     */
    private String country;

    /**
     * city that the event took place in
     */
    private String city;

    /**
     * Type of event that occured
     */
    private String eventType;

    /**
     * year that the event occured
     */
    private int year;

    /**
     * constructor used to instantiated new event object with all params included
     *
     * @param eventID
     * @param associatedUsername
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     */
    public Event(String eventID, String associatedUsername, String personID, Float latitude, Float longitude, String country, String city, String eventType, int year) {
        this.eventID=eventID;
        this.associatedUsername=associatedUsername;
        this.personID=personID;
        this.latitude=latitude;
        this.longitude=longitude;
        this.country=country;
        this.city=city;
        this.eventType=eventType;
        this.year=year;
    }

    /**
     * Default constructor
     */
    public Event(){}

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID=eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername=associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID=personID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude=latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude=longitude;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType=eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year=year;
    }
}

