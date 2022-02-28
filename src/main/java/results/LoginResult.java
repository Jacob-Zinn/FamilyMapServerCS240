package results;

/**
 * contains success and failure indicators pertaining to the login request
 */
public class LoginResult {

    /**
     * authenticates the request before returning sensitive info
     */
    private String authToken;

    /**
     * username used in login request
     */
    private String username;

    /**
     * person id related to authenticated user
     */
    private String personID;

    /**
     * contains reasoning behind failure of request
     */
    private String message;     //  "Error:[Description of the error]"

    /**
     * indicates request success or failure of request
     */
    private Boolean success;


    /**
     *
     * LoginResult constructor instantiating all variables
     *
     * @param authToken
     * @param username
     * @param personID
     * @param success
     */
    public LoginResult(String authToken, String username, String personID, Boolean success) {
        this.authToken=authToken;
        this.username=username;
        this.personID=personID;
        this.success=success;
    }


    public LoginResult(String message, Boolean success) {
        this.message=message;
        this.success=success;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken=authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID=personID;
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


/*
Success Response Body:
{
	"authtoken":"cf7a368f",	        // string
	"username":"susan",		// string
	"personID":"39f9fe46",	        // string
        "success":true			// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	//string
        "success":false					// boolean
}

 */