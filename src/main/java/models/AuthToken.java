package models;

import java.util.Objects;

/**
 * Token stored to authenticate user requests to database
 */
public class AuthToken {

    /**
     * unique token used for authentication
     */
    private String authToken;

    /**
     * username associated with the authentication token
     */
    private String username;

    /**
     * constructor used to instantiated new AuthToken object with all params included
     *
     * @param authToken
     * @param username
     */
    public AuthToken(String authToken, String username) {
        this.authToken=authToken;
        this.username=username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken=(AuthToken) o;
        return Objects.equals(AuthToken.this.authToken, authToken.authToken) && Objects.equals(username, authToken.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken, username);
    }

    public String getAuthtoken() {
        return authToken;
    }

    public void setAuthtoken(String authToken) {
        this.authToken=AuthToken.this.authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }
}

