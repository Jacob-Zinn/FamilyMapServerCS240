package dao;

import models.AuthToken;
import models.Event;

/**
 * Interacts with events table
 */
public class EventDao {

    /**
     * @return a single event with the specified eventId
     */
    public Event getEvent(AuthToken authToken, String eventID) {
        return new Event();
    }

    /**
     * @return all of the events in event table associated with given authToken
     */
    public Event[] getEvents(AuthToken authToken) {
        Event[] eventsArray = {new Event()};
        return eventsArray;
    }

    /**
     * creates new entry in event table
     *
     * @param event
     */
    public void insertEvent(Event event) {}

    /**
     * removes all entries from the event table
     */
    public void nukeTable() {}
}
