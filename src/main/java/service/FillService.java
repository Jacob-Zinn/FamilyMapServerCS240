package service;


import api.BadRequestException;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import db.DataAccessException;
import db.Database;
import models.Event;
import models.Person;
import models.User;
import requests.FillRequest;
import results.FillResult;
import results.LoginResult;
import util.ParentGenerationHelper;
import util.Random;

import java.sql.Connection;

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
    public FillResult fill(FillRequest fillRequest) throws BadRequestException {
        Database db=new Database();

        try {
            // init db
            Connection conn=db.getConnection();

            if (fillRequest.getGenerations() < 0 || fillRequest.getGenerations() > 8) {
                throw new BadRequestException("Error: Specified number of generations is out of bounds");
            }

            UserDao userDao=new UserDao(conn);
            PersonDao personDao=new PersonDao(conn);
            EventDao eventDao=new EventDao(conn);

            User user=userDao.getUser(fillRequest.getUsername());
            if (user == null) {
                throw new BadRequestException("Error: Specified user does not exist in db");
            }
            Person person=personDao.getPerson(user.getPersonID(), user.getUsername());

            // create object to collect all recursively generated people and events
            ParentGenerationHelper parentGenerationHelper=new ParentGenerationHelper(fillRequest.getGenerations());

            // root user will be deleted from db below, so making sure root user person gets added back (with the appropriate parent ids)
            parentGenerationHelper.addPerson(person);

            // creating birth event for root person
            Event rootUserBirthEvent = Event.generateRandomEvent(person, 2001, Event.EventType.birth);
            parentGenerationHelper.addEvent(rootUserBirthEvent);

            // mother father marriage and spouse sync
            Event parentMarriageEvent = Event.generateRandomEvent(person, 2000, Event.EventType.marriage);
            String fatherID = Random.generateUUID();
            String motherID = Random.generateUUID();

            // start generating ancestry and events (recursive)
            person.generateParent(fatherID, motherID, 0, true, parentGenerationHelper, parentMarriageEvent);
            person.generateParent(motherID, fatherID,0, false, parentGenerationHelper, parentMarriageEvent);

            // clearing data associated with username
            personDao.deletePersonsForUser(fillRequest.getUsername());
            eventDao.deleteEventsForUser(fillRequest.getUsername());

            // populate db
            for (Person dbPerson : parentGenerationHelper.getPersons()) {
                personDao.insertPerson(dbPerson);
            }
            for (Event dbEvent : parentGenerationHelper.getEvents()) {
                eventDao.insertEvent(dbEvent);
            }

            db.closeConnection(true);

            return new FillResult("Successfully filled db", true);
        } catch (DataAccessException | BadRequestException e) {
            db.closeConnection(false);
            e.printStackTrace();
            return new FillResult(e.getMessage(), false);
        }
    }




}
