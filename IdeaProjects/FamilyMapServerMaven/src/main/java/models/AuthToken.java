package models;

/**
 * Token stored to authenticate user requests to database
 */
public class AuthToken {

    /**
     * unique token used for authentication
     */
    private String authtoken;

    /**
     * username associated with the authentication token
     */
    private String username;

    /**
     * constructor used to instantiated new AuthToken object with all params included
     *
     * @param authtoken
     * @param username
     */
    public AuthToken(String authtoken, String username) {
        this.authtoken=authtoken;
        this.username=username;
    }

    /**
     * default constructor for authToken. used for providing dummy data
     */
    public AuthToken(){};

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken=authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }
}

