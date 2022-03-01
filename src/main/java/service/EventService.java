package service;

import api.BadRequestException;
import dao.AuthTokenDao;
import dao.EventDao;
import db.DataAccessException;
import db.Database;
import models.AuthToken;
import models.Event;
import results.EventResult;
import results.EventsResult;

import java.sql.Connection;

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
            // authentication success
            String associatedUsername = userToken.getUsername();

            EventDao eventDao = new EventDao(conn);
            Event event = eventDao.getEvent(eventID, associatedUsername);

            db.closeConnection(true);

            return new EventResult(event.getAssociatedUsername(), event.getEventID(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear(), true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new EventResult("Failed to access db to extract single event", false);
        } catch (BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new EventResult(e.getMessage(), false);
        }
    }

    /**
     *
     * @param authToken
     * @return Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
     */
    public EventsResult getEvents(String authToken) {
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
            // authentication success
            String associatedUsername = userToken.getUsername();

            EventDao eventDao = new EventDao(conn);
            Event[] events = eventDao.getEvents(associatedUsername);

            db.closeConnection(true);

            return new EventsResult(events, true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new EventsResult("Failed to access db to extract events", false);
        } catch (BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new EventsResult(e.getMessage(), false);
        }
    }
}
