package service;

import api.BadRequestException;
import dao.PersonDao;
import db.DataAccessException;
import db.Database;
import models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.FillRequest;
import requests.RegisterRequest;
import results.FillResult;
import results.RegisterResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class FillServiceTest {

    FillService fillService;
    RegisterService registerService;
    ClearService clearService;
    RegisterResult registerResult;
    RegisterRequest fakeRegisterRequest = new RegisterRequest(
            "username",
            "password",
            "email",
            "firstName",
            "lastName",
            "m"
    );

    Database db;

    @BeforeEach
    void setUp() throws BadRequestException {
        fillService = new FillService();
        clearService = new ClearService();
        clearService.clear();

        registerService = new RegisterService();
        registerResult = registerService.register(fakeRegisterRequest);

        db=new Database();
    }

    @Test
    void fill_pass_zeroGen() throws DataAccessException, BadRequestException {
        FillResult fillResult = fillService.fill(new FillRequest("username", 0));
        assertTrue(fillResult.getMessage().length() > 0);
        assertTrue(fillResult.getSuccess());

        Connection conn=db.getConnection();
        PersonDao personDao=new PersonDao(conn);

        Person rootPerson = personDao.getPerson(registerResult.getPersonID(), fakeRegisterRequest.getUsername());
        assertNull(rootPerson.getFatherID());

        db.closeConnection(false);
    }

    @Test
    void fill_pass_fourGen() throws DataAccessException, BadRequestException {
        FillResult fillResult = fillService.fill(new FillRequest("username", 4));

        assertTrue(fillResult.getMessage().length() > 0);
        assertTrue(fillResult.getSuccess());

        Connection conn=db.getConnection();
        PersonDao personDao=new PersonDao(conn);

        Person rootPerson = personDao.getPerson(registerResult.getPersonID(), fakeRegisterRequest.getUsername());
        assertNotNull(rootPerson.getFatherID());

        Person[] persons = personDao.getPersons(fakeRegisterRequest.getUsername());
        assertEquals(31, persons.length);

        db.closeConnection(false);
    }

    @Test
    void fill_fail_invaidNumGenerations() throws BadRequestException {
        FillResult result = fillService.fill(new FillRequest("username", -1));
        assertFalse(result.getSuccess());
    }

    @Test
    void fill_fail_userNull() throws BadRequestException {
        clearService.clear();
        FillResult result = fillService.fill(new FillRequest("username", 2));
        assertFalse(result.getSuccess());
    }

}