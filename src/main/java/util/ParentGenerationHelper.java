package util;

import models.Event;
import models.Person;

import java.util.ArrayList;

public class ParentGenerationHelper {

    private int targetNumGenerations = 4;

    private final ArrayList<Person> persons = new ArrayList<>();
    private final ArrayList<Event> events = new ArrayList<>();

    public ParentGenerationHelper(int targetNumGenerations) {
        this.targetNumGenerations = targetNumGenerations;
    }

    public int getTargetNumGenerations() {
        return targetNumGenerations;
    }

    public void setTargetNumGenerations(int targetNumGenerations) {
        this.targetNumGenerations = targetNumGenerations;
    }

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
