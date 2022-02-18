package service;


import results.PersonResult;
import results.PersonsResult;

/**
 * contains methods that extract person information from database
 */
public class PersonService {

    /**
     * @return the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
     */
    public PersonResult getPerson(String authToken, String personID) {
        return new PersonResult("Error",false);
    }

    /**
     * @return Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     */
    public PersonsResult getPersons(String authToken) {
        return new PersonsResult("Error", false);
    }
}
