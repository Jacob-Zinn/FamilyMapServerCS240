package dao;

import models.AuthToken;
import models.Person;

/**
 * Interacts with person table
 */
public class PersonDao {

    /**
     * @return a single person with the specified personId
     */
    public Person getPerson(AuthToken authToken, String personID) {
        return new Person();
    }

    /**
     * @return all of the persons in person table associated with given authToken
     */
    public Person[] getPersons(AuthToken authToken) {
        Person[] personsArray = {new Person()};
        return personsArray;
    }

    /**
     * creates new entry in person table
     *
     * @param person
     */
    public void insertPerson(Person person) {}

    /**
     * removes all entries from the person table
     */
    public void nukeTable() {}
}
