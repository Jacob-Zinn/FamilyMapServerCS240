package service;

import api.BadRequestException;
import dao.PersonDao;
import db.DataAccessException;
import db.Database;
import models.Event;
import models.Person;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.LoadRequest;
import results.LoadResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class LoadServiceTest {

    LoadService loadService;
    Event event = new Event("Biking_123A", "jacobzinn", "Gale123A", 35.9f, 140.1f, "Japan", "Ushiku", "Biking_Around", 2016);
    Person person = new Person("YourFavorite_Person", "jacobzinn", "Jacob", "Zinn", "Japan", "Ushiku", "Biking_Around", "spousesID");
    User user = new User("jacobzinn", "greatPass", "jacobpzinn@gmail.com", "jacob", "zinn", "m", "YourFavorite_Person");
    LoadRequest loadRequest = new LoadRequest(new User[]{user}, new Person[]{person}, new Event[]{event});

    @BeforeEach
    void setUp() {
        loadService = new LoadService();
    }

    @Test
    void load() throws DataAccessException, BadRequestException {
        LoadResult loadResult = loadService.load(loadRequest);
        assertTrue(loadResult.getSuccess());


        Database db = new Database();
        Connection conn = db.getConnection();
        PersonDao personDao = new PersonDao(conn);

        assertEquals(person, personDao.getPerson(person.getPersonID(), user.getUsername()));

        db.closeConnection(false);
    }


    @Test
    void load_fail() throws BadRequestException {
        LoadResult loadResult = loadService.load(new LoadRequest(null, null, null));
        assertFalse(loadResult.getSuccess());
    }
}