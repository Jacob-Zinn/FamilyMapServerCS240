package service;

import results.EventResult;
import results.EventsResult;

/**
 * contains methods to extract events from database
 */
public class EventService {

    /**
     * @param authToken
     * @param eventID
     * @return Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken.
     */
    public EventResult getEvent(String authToken, String eventID) {
        return new EventResult("Error", false);
    }

    /**
     *
     * @param authToken
     * @return Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
     */
    public EventsResult getEvents(String authToken) {
        return new EventsResult("Error", false);
    }
}
