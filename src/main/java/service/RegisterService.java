package service;


import results.RegisterResult;
import requests.RegisterRequest;

/**
 * holds method for registration
 */
public class RegisterService {

    /**
     *
     * Creates a new user account (user row in the database)
     * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
     * Logs the user in
     *  POST
     * @param registerRequest
     * @return authtoken
     */
    public RegisterResult register(RegisterRequest registerRequest) {
        return new RegisterResult("Error", false);
    }
}
