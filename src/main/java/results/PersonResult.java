package results;

/**
 * Returns a http response containing info for a single person object
 */
public class PersonResult {

    /**
     * associatedUsername of Person object
     */
    private String associatedUsername;

    /**
     * personID of Person object
     */
    private String personID;

    /**
     * firstName of Person object
     */
    private String firstName;

    /**
     * lastName of Person object
     */
    private String lastName;

    /**
     * string gender of Person object expressed as either "f" or "m"
     */
    private String gender;              // "f" or "m"

    /**
     * nullable fatherID of Person object
     */
    private String fatherID;            // nullable

    /**
     * nullable motherID of Person object
     */
    private String motherID;            // nullable

    /**
     * nullable spouseID 0f Person object
     */
    private String spouseID;            // nullable

    /**
     * contains reasoning behind failure of request
     */
    private String message;

    /**
     * indicates request success or failure of request
     */
    private Boolean success;


    /**
     *
     * PersonResult constructor instantiating all variables
     *
     * @param associatedUsername
     * @param personID
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     * @param success
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, String message, Boolean success) {
        this.associatedUsername=associatedUsername;
        this.personID=personID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.fatherID=fatherID;
        this.motherID=motherID;
        this.spouseID=spouseID;
        this.success=success;
    }

    public PersonResult(String message, Boolean success) {
        this.message=message;
        this.success=success;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender=gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID=fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID=motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID=spouseID;
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

// /person/[personID]
/*
Success Response Body:
{
	"associatedUsername":"susan",	// string
	"personID":"7255e93e",		// string
	"firstName":"Stuart",			// string
	"lastName":"Klocke",			// string
	"gender":"m",				// string: "f" or "m"
	"fatherID": "7255e93e",	// string [OPTIONAL, can be missing]
	"motherID":"d3gz214j",	// string [OPTIONAL, can be missing]
	"spouseID":"f42126c8"	,	// string [OPTIONAL, can be missing]
        "success":true				// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	// string
        "success":false							// boolean
}

 */