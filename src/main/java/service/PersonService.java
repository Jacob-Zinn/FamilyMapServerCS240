package service;


import api.BadRequestException;
import dao.AuthTokenDao;
import dao.PersonDao;
import db.DataAccessException;
import db.Database;
import models.AuthToken;
import models.Person;
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
    public PersonResult getPerson(String authToken, String personID) {
        Database db=new Database();

        try {
            // init db
            Connection conn=db.getConnection();

            if (authToken == null || personID == null) {
                throw new BadRequestException("Invalid params: null");
            }

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken userToken=authTokenDao.getAuthToken(authToken);
            if (userToken == null || !userToken.getAuthtoken().equals(authToken)) {
                throw new BadRequestException("Not Authorized");
            }
            System.out.println("auth success");

            PersonDao personDao = new PersonDao(conn);
            Person person = personDao.getPerson(personID, userToken.getUsername());

            db.closeConnection(true);

            return new PersonResult(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new PersonResult("Failed to access db to extract single person", false);
        } catch (BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new PersonResult(e.getMessage(), false);
        }
    }

    /**
     * @return Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     */
    public PersonsResult getPersons(String authToken) {
        Database db=new Database();

        try {
            // init db
            Connection conn=db.getConnection();

            if (authToken == null) {
                throw new BadRequestException("Invalid params: authToken null");
            }

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken userToken=authTokenDao.getAuthToken(authToken);
            if (userToken == null || !userToken.getAuthtoken().equals(authToken)) {
                throw new BadRequestException("Not Authorized");
            }
            System.out.println("auth success");
            String associatedUsername = userToken.getUsername();

            PersonDao personDao = new PersonDao(conn);
            Person[] persons = personDao.getPersons(associatedUsername);

            db.closeConnection(true);

            return new PersonsResult(persons, true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new PersonsResult("Failed to access db to extract persons", false);
        } catch (BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new PersonsResult(e.getMessage(), false);
        }
    }
}
