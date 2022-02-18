package dao;

import models.AuthToken;

/**
 * Interacts with authentication token table
 */
public class AuthTokenDao {

    /**
     * @param authToken - authToken associated with user in User table
     * @return the authToken associated with the provided username
     */
    public AuthToken getAuthToken(String authToken) {
        return new AuthToken();
    }

    /**
     * creates new entry in AuthToken table
     * @param authToken verifies that the user is authenticated
     */
    public void insertAuthToken(AuthToken authToken) {}

    /**
     * removes all entries from the AuthToken table
     */
    public void nukeTable() {}
}
