package dao;

import db.DataAccessException;
import models.Event;

import java.sql.*;

/**
 * Interacts with events table
 */
public class EventDao {

    private final Connection conn;

    public EventDao(Connection conn) {
        this.conn=conn;
    }

    /**
     * creates new entry in event table
     *
     * @param event
     */
    public void insertEvent(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO event (eventID, associatedUsername, personID, latitude, longitude, " +
                "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * @return a single event with the specified eventId
     */
    public Event getEvent(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql="SELECT * FROM event WHERE eventID = ?;";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs=stmt.executeQuery();
            if (rs.next()) {
                event=new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
    }

    /**
     * @return all of the events in event table associated with given authToken
     */
    public Event[] getEvents() throws DataAccessException {
        Event[] eventsArray={new Event()};
        return eventsArray;
    }

    /**
     * removes all entries from the event table
     */
    public void nukeTable() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM event";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
