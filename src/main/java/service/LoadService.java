package service;


import api.BadRequestException;
import dao.AuthTokenDao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import models.Event;
import models.Person;
import models.User;
import requests.LoadRequest;
import results.ClearResult;
import results.LoadResult;
import results.LoginResult;

import java.sql.Connection;

/**
 * contains method to load information into database
 */
public class LoadService {

    /**
     * Clears all data from the database (just like the /clear API)
     * Loads the user, person, and event data from the request body into the database.
     *
     * @param loadRequest
     * @return success/failure information
     */
    public LoadResult load(LoadRequest loadRequest) throws BadRequestException {
        Database db = new Database();

        try {
            Connection conn = db.getConnection();

            if (loadRequest.getPersons() == null || loadRequest.getUsers() == null || loadRequest.getEvents() == null) {
                throw new BadRequestException("Error: Invalid params - cannot load data");
            }

            // clearing data from api
            AuthTokenDao authTokenDao = new AuthTokenDao(conn);
            EventDao eventDao = new EventDao(conn);
            PersonDao personDao = new PersonDao(conn);
            UserDao userDao = new UserDao(conn);

            authTokenDao.nukeTable();
            eventDao.nukeTable();
            personDao.nukeTable();
            userDao.nukeTable();

            for (User user : loadRequest.getUsers()) {
                userDao.insertUser(user);
            }
            for (Person person : loadRequest.getPersons()) {
                personDao.insertPerson(person);
            }
            for (Event event : loadRequest.getEvents()) {
                eventDao.insertEvent(event);
            }

            db.closeConnection(true);

            return new LoadResult("Successfully loaded data into db", true);
        }  catch (DataAccessException | BadRequestException e) {
            db.closeConnection(false);
            return new LoadResult(e.getMessage(), false);
        }
    }

}
