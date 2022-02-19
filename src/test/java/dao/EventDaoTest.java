package dao;

import db.DataAccessException;
import db.Database;
import models.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class EventDaoTest {
    private Database db;
    private Event dummyEvent;
    private EventDao eventDao;


    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        dummyEvent= new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        Connection conn = db.getConnection();
        eventDao = new EventDao(conn);
        eventDao.nukeTable();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    void insertEventPass() throws DataAccessException {
        eventDao.insertEvent(dummyEvent);
        Event compareTest = eventDao.getEvent(dummyEvent.getEventID());
        assertNotNull(compareTest);
        assertEquals(dummyEvent, compareTest);
    }

    @Test
    void insertEventFail() throws DataAccessException {
        eventDao.insertEvent(dummyEvent);
        assertThrows(DataAccessException.class, () -> eventDao.insertEvent(dummyEvent));
    }

    @Test
    void getEventPass() throws DataAccessException {
        eventDao.insertEvent(dummyEvent);
        Event eventReturned = eventDao.getEvent(dummyEvent.getEventID());
        assertNotNull(eventReturned);
        assertEquals(dummyEvent, eventReturned);
    }

    @Test
    void getEventFail() throws DataAccessException {
        assertNull(eventDao.getEvent(dummyEvent.getEventID()));
    }

    @Test
    void getEvents() {
    }

    @Test
    void nukeTable() throws DataAccessException {
        eventDao.insertEvent(dummyEvent);
        eventDao.nukeTable();
        assertNull(eventDao.getEvent(dummyEvent.getEventID()));
    }
}