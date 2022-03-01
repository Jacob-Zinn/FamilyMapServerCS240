package service;


import dao.AuthTokenDao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import results.ClearResult;

import java.sql.Connection;

/**
 * contains method for clearing information from database
 */
public class ClearService {

    /**
     * Deletes ALL data from the database, including user, authtoken, person, and event data
     */
    public ClearResult clear() {
        Database db=new Database();

        try {
            Connection conn=db.getConnection();

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            EventDao eventDao=new EventDao(conn);
            PersonDao personDao=new PersonDao(conn);
            UserDao userDao=new UserDao(conn);

            authTokenDao.nukeTable();
            eventDao.nukeTable();
            personDao.nukeTable();
            userDao.nukeTable();

            db.closeConnection(true);

            return new ClearResult("clear succeeded", true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new ClearResult("Failed to clear db",false);
        }
    }
}
