package service;


import dao.AuthTokenDao;
import dao.PersonDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import models.AuthToken;
import models.Person;
import models.User;
import requests.FillRequest;
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
        try {
            // init db
            Database db=new Database();
            Connection conn=db.getConnection();

            // insert authToken
            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken authToken = new AuthToken(Random.generateUUID(), registerRequest.getUsername());
            authTokenDao.insertAuthToken(authToken);

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
            personDao.insertPerson(Person.generateRandomPerson(newUser.getUsername(), true, rootPersonID));

            // generate new user data
            FillRequest fillRequest = new FillRequest(registerRequest.getUsername());
            FillService fillService = new FillService();
            fillService.fill(fillRequest);

            return new RegisterResult(authToken.getAuthtoken(), newUser.getUsername(), newUser.getPersonID(), true);

        } catch (DataAccessException e) {
            e.printStackTrace();
            return new RegisterResult("Failed to access db", false);
        }

    }
}
