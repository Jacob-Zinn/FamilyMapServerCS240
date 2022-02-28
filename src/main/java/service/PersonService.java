package service;


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

        try {
            // init db
            Database db=new Database();
            Connection conn=db.getConnection();

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken userToken=authTokenDao.getAuthToken(authToken);
            assert(userToken.getAuthtoken().equals(authToken));
            System.out.println("auth success");

            PersonDao personDao = new PersonDao(conn);
            Person person = personDao.getPerson(personID);

            return new PersonResult(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
        } catch (DataAccessException e) {
            return new PersonResult("Failed to access db to extract single person", false);
        }
    }

    /**
     * @return Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     */
    public PersonsResult getPersons(String authToken) {
        try {
            // init db
            Database db=new Database();
            Connection conn=db.getConnection();

            AuthTokenDao authTokenDao=new AuthTokenDao(conn);
            AuthToken userToken=authTokenDao.getAuthToken(authToken);
            assert(userToken.getAuthtoken().equals(authToken));
            System.out.println("auth success");

            PersonDao personDao = new PersonDao(conn);
            Person[] persons = personDao.getPersons();

            return new PersonsResult(persons, true);
        } catch (DataAccessException e) {
            return new PersonsResult("Failed to access db to extract persons", false);
        }
    }
}
