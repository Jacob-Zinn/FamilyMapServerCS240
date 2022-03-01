package service;


import api.BadRequestException;
import dao.AuthTokenDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import models.AuthToken;
import models.User;
import requests.LoginRequest;
import results.LoginResult;
import util.Random;

import java.sql.Connection;
import java.util.Objects;

/**
 * holds method for login
 */
public class LoginService {

    /**
     * logs the user in
     *
     * @param loginRequest
     * @return authtoken
     */
    public LoginResult login(LoginRequest loginRequest) throws BadRequestException {
        Database db = new Database();

        try {
            Connection conn = db.getConnection();

            if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                throw new BadRequestException("Error: Invalid params - login failed");
            }

            // clearing data from api
            AuthTokenDao authTokenDao = new AuthTokenDao(conn);
            UserDao userDao = new UserDao(conn);

            User user = userDao.getUser(loginRequest.getUsername());
            if (user == null) {
                throw new BadRequestException("Error: Login failed - User with provided username does not exist");
            } else if (!Objects.equals(user.getPassword(), loginRequest.getPassword())) {
                throw new BadRequestException("Error: Login failed - incorrect password");
            }

            String authToken = Random.generateUUID();
            authTokenDao.insertAuthToken(new AuthToken(authToken, user.getUsername()));

            db.closeConnection(true);

            return new LoginResult(authToken, user.getUsername(), user.getPersonID(), true);
        } catch (DataAccessException | BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new LoginResult(e.getMessage(), false);
        }
    }
}
