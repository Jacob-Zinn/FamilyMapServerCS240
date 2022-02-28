package util;

import models.Event;
import models.Person;

import java.util.ArrayList;

public class ParentGenerationHelper {

    private ArrayList<Person> persons;
    private ArrayList<Event> events;

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
