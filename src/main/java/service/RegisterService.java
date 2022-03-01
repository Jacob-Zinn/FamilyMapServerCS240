package service;


import api.BadRequestException;
import dao.AuthTokenDao;
import dao.PersonDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import models.AuthToken;
import models.Person;
import models.User;
import results.RegisterResult;
import requests.RegisterRequest;
import util.Random;

import java.sql.Connection;

/**
 * holds method for registration
 */
public class RegisterService {

    /**
     * Creates a new user account (user row in the database)
     * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
     * Logs the user in
     * POST
     *
     * @param registerRequest
     * @return authtoken
     */
    public RegisterResult register(RegisterRequest registerRequest) {
        Database db = new Database();

        try {
            // init db
            Connection conn = db.getConnection();

            if (registerRequest.getGender().length() != 1) {
                throw new BadRequestException("gender param not valid");
            } else if (registerRequest.getUsername() == null) {
                throw new BadRequestException("username param not valid");
            }

            // insert authToken
            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken authToken = new AuthToken(Random.generateUUID(), registerRequest.getUsername());
            try {
                authTokenDao.insertAuthToken(authToken);
            } catch (DataAccessException e) {
                throw new BadRequestException("username param not valid");
            }

            // insert new user
            UserDao userDao=new UserDao(conn);
            String rootPersonID = Random.generateUUID();
            User newUser=new User(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getEmail(),
                    registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getGender(),
                    rootPersonID
            );
            userDao.insertUser(newUser);

            // generate root person
            PersonDao personDao = new PersonDao(conn);
            Person newPerson = new Person(rootPersonID, newUser.getUsername(), registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getGender());
            personDao.insertPerson(newPerson);

            db.closeConnection(true);

            return new RegisterResult(authToken.getAuthtoken(), newUser.getUsername(), newUser.getPersonID(), true);

        } catch (DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new RegisterResult("Failed to access db - register service", false);
        } catch (BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new RegisterResult(e.getMessage(), false);
        }

    }
}
