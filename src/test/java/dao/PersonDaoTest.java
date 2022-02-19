package dao;

import db.DataAccessException;
import db.Database;
import models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;


class PersonDaoTest {

    private Database db;
    private Person fakePerson;
    private PersonDao personDao;


    @BeforeEach
    public void setUp() throws DataAccessException {
        db=new Database();
        fakePerson=new Person("YourFavorite_Person", "jacobzinn", "Jacob",
                "Zinn", "Japan", "Ushiku",
                "Biking_Around", "spousesID");
        Connection conn=db.getConnection();
        personDao=new PersonDao(conn);
        personDao.nukeTable();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    void insertPersonPass() throws DataAccessException {
        personDao.insertPerson(fakePerson);
        Person compareTest=personDao.getPerson(fakePerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(fakePerson, compareTest);
    }

    @Test
    void insertPersonFail() throws DataAccessException {
        personDao.insertPerson(fakePerson);
        assertThrows(DataAccessException.class, () -> personDao.insertPerson(fakePerson));
    }

    @Test
    void getPersonPass() throws DataAccessException {
        personDao.insertPerson(fakePerson);
        Person personReturned=personDao.getPerson(fakePerson.getPersonID());
        assertNotNull(personReturned);
        assertEquals(fakePerson, personReturned);
    }

    @Test
    void getPersonFail() throws DataAccessException {
        assertNull(personDao.getPerson(fakePerson.getPersonID()));
    }

    @Test
    void getPersons() {
    }

    @Test
    void nukeTable() throws DataAccessException {
        personDao.insertPerson(fakePerson);
        personDao.nukeTable();
        assertNull(personDao.getPerson(fakePerson.getPersonID()));
    }


}