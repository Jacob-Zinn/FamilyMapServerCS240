package requests;

import models.Event;
import models.Person;
import models.User;

/**
 * includes parameters to be used in the load method within LoadService
 */
public class LoadRequest {

    /**
     * array of users to be loaded into database
     */
    private User[] users;

    /**
     * array of persons to be loaded into database
     */
    private Person[] persons;

    /**
     * array of events to be loaded into database
     */
    private Event[] events;


    /**
     *
     * LoadRequest constructor with all params
     *
     * @param users
     * @param persons
     * @param events
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users=users;
        this.persons=persons;
        this.events=events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users=users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons=persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events=events;
    }
}
