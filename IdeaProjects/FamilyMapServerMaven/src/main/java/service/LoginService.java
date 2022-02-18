package service;


import requests.LoginRequest;
import results.LoginResult;

/**
 * holds method for login
 */
public class LoginService {

    /**
     * logs the user in
     * @param loginRequest
     * @return authtoken
     */
    public LoginResult login(LoginRequest loginRequest) {
        return new LoginResult("Error",false);
    }
}
