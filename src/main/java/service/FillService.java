package service;


import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import models.Event;
import models.Person;
import models.User;
import requests.FillRequest;
import requests.LoadRequest;
import results.FillResult;
import util.ParentGenerationHelper;
import util.Random;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Contains methods used to fill database given correct user path
 */
public class FillService {

    /**
     * Populates the server's database with generated data for the specified username. The required "username" parameter must be a user already registered with the server. If there is any data in the database already associated with the given username, it is deleted.
     * The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
     *
     * url path: / fill
     *
     * @param fillRequest
     *
     * ex:
     * /fill/susan
     * /fill/susan/3
     *
     * @return success/failure information
     */
    public FillResult fill(FillRequest fillRequest) {

        try {
            // init db
            Database db=new Database();
            Connection conn=db.getConnection();
            UserDao userDao=new UserDao(conn);
            PersonDao personDao=new PersonDao(conn);
            EventDao eventDao=new EventDao(conn);

            User user=userDao.getUser(fillRequest.getUsername());
            Person person=personDao.getPerson(user.getPersonID());

            // create object to collect all recursively generated people and events
            ParentGenerationHelper parentGenerationHelper=new ParentGenerationHelper();

            // linking parent and child
            String newFatherID = Random.generateUUID();
            String newMotherID = Random.generateUUID();
            person.setFatherID(newFatherID);
            person.setMotherID(newMotherID);

            // mother father marriage sync
            Event parentMarriageEvent = Event.generateRandomEvent(person, 1990, Event.EventType.marriage);
            parentGenerationHelper.addEvent(parentMarriageEvent);

            // start generating ancestry and events (recursive)
            person.generateParent(person.getAssociatedUsername(), 1, fillRequest.getGenerations(), newFatherID, true, parentGenerationHelper, parentMarriageEvent);
            person.generateParent(person.getAssociatedUsername(), 1,  fillRequest.getGenerations(), newMotherID, false, parentGenerationHelper, parentMarriageEvent);

            // clearing data associated with username
            personDao.deleteEventsForUser(fillRequest.getUsername());
            eventDao.deleteEventsForUser(fillRequest.getUsername());

            // populate db
            for (Person dbPerson : parentGenerationHelper.getPersons()) {
                personDao.insertPerson(dbPerson);
            }
            for (Event dbEvent : parentGenerationHelper.getEvents()) {
                eventDao.insertEvent(dbEvent);
            }

            return new FillResult("Successfully filled db", true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new FillResult("Error occurred while filling db",  false);
        }
    }




}
