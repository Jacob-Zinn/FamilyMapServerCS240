package service;


import api.BadRequestException;
import dao.AuthTokenDao;
import dao.PersonDao;
import db.DataAccessException;
import db.Database;
import models.AuthToken;
import models.Person;
import results.LoginResult;
import results.PersonResult;
import results.PersonsResult;

import java.sql.Connection;

/**
 * contains methods that extract person information from database
 */
public class PersonService {

    /**
     * @return the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
     */
    public PersonResult getPerson(String authToken, String personID) throws BadRequestException {
        Database db=new Database();

        try {
            // init db
            Connection conn=db.getConnection();

            if (authToken == null || personID == null) {
                throw new BadRequestException("Error: Invalid params: null");
            }

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken userToken=authTokenDao.getAuthToken(authToken);
            if (userToken == null || !userToken.getAuthToken().equals(authToken)) {
                throw new BadRequestException("Error: Not Authorized");
            }

            PersonDao personDao = new PersonDao(conn);
            Person person = personDao.getPerson(personID, userToken.getUsername());

            db.closeConnection(true);

            return new PersonResult(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
        }  catch (DataAccessException | BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new PersonResult(e.getMessage(), false);
        }
    }

    /**
     * @return Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     */
    public PersonsResult getPersons(String authToken) throws BadRequestException {
        Database db=new Database();

        try {
            // init db
            Connection conn=db.getConnection();

            if (authToken == null) {
                throw new BadRequestException("Error: Invalid params: authToken null");
            }

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken userToken=authTokenDao.getAuthToken(authToken);
            if (userToken == null || !userToken.getAuthToken().equals(authToken)) {
                throw new BadRequestException("Error: Not Authorized");
            }
            String associatedUsername = userToken.getUsername();

            PersonDao personDao = new PersonDao(conn);
            Person[] persons = personDao.getPersons(associatedUsername);

            db.closeConnection(true);

            return new PersonsResult(persons, true);
        } catch (DataAccessException | BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new PersonsResult(e.getMessage(), false);
        }
    }
}
