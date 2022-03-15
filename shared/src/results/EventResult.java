package results;

/**
 * http get call containing an event object specified by event ID
 */
public class EventResult {


    /**
     * associatedUsername of Event object
     */
    private String associatedUsername;

    /**
     * eventID of Event object
     */
    private String eventID;

    /**
     * personID of Event object
     */
    private String personID;

    /**
     * latitude of Event object
     */
    private Float latitude;

    /**
     * longitude of Event object
     */
    private Float longitude;

    /**
     * country of Event object
     */
    private String country;

    /**
     * city of Event object
     */
    private String city;

    /**
     * eventType of Event object
     */
    private String eventType;

    /**
     * year of Event object
     */
    private int year;

    /**
     * message of Event object
     */
    private String message;

    /**
     * indicates request success or failure
     */
    private Boolean success;


    /**
     *
     * EventResult constructor instantiating all success variables
     *
     * @param associatedUsername
     * @param eventID
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     * @param success
     */
    public EventResult(String associatedUsername, String eventID, String personID, Float latitude, Float longitude, String country, String city, String eventType, int year, Boolean success) {
        this.associatedUsername=associatedUsername;
        this.eventID=eventID;
        this.personID=personID;
        this.latitude=latitude;
        this.longitude=longitude;
        this.country=country;
        this.city=city;
        this.eventType=eventType;
        this.year=year;
        this.success=success;
    }

    /**
     * instantiates all vars indicative of failure
     *
     * @param message
     * @param success
     */
    public EventResult(String message, Boolean success) {
        this.message=message;
        this.success=success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername=associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID=eventID;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success=success;
    }
}

// /event/[eventID]

/*
Success Response Body:
{
	"associatedUsername":"susan", 	// string
	"eventID":"251837d7",			// string
	"personID":"7255e93e",		// string
	"latitude":65.6833,			// float
	"longitude":-17.9,			// float
	"country":"Iceland",			// string
	"city":"Akureyri",			// string
	"eventType":"birth",			// string
	"year":1912,				// int
        "success":true				// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	// string
        "success":false							// boolean
}

 */