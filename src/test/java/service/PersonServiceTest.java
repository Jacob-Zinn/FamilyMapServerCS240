package service;

import api.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.FillRequest;
import requests.RegisterRequest;
import results.PersonResult;
import results.PersonsResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    PersonService personService;
    ClearService clearService;
    FillService fillService;
    RegisterService registerService;
    RegisterRequest fakeRegisterRequest = new RegisterRequest(
            "username",
            "password",
            "email",
            "firstName",
            "lastName",
            "m"
    );
    String authToken;
    String personID;

    @BeforeEach
    void setUp() throws BadRequestException {
        clearService = new ClearService();
        clearService.clear();


        personService = new PersonService();
        registerService = new RegisterService();
        fillService = new FillService();

        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        authToken = registerResult.getAuthtoken();
        personID = registerResult.getPersonID();
        fillService.fill(new FillRequest(fakeRegisterRequest.getUsername(), 2));
    }

    @Test
    void getPerson() throws BadRequestException {
        PersonResult result = personService.getPerson(authToken, personID);
        assertTrue(result.getSuccess());
        assertEquals(fakeRegisterRequest.getFirstName(), result.getFirstName());
    }

    @Test
    void getPerson_fail_notAuthorized() throws BadRequestException {
        PersonResult result = personService.getPerson("notanauth", personID);
        assertFalse(result.getSuccess());
    }

    @Test
    void getPerson_fail_nullParam() throws BadRequestException {
        PersonResult result = personService.getPerson(null, null);
        assertFalse(result.getSuccess());
    }

    @Test
    void getPersons() throws BadRequestException {
        PersonsResult result = personService.getPersons(authToken);
        assertEquals(7, result.getData().length);
    }

    @Test
    void getPersons_fail_notAuthorized() throws BadRequestException {
        PersonsResult result = personService.getPersons("notanauth");
        assertFalse(result.getSuccess());
    }

    @Test
    void getPersons_fail_nullParam() throws BadRequestException {
        PersonsResult result = personService.getPersons(null);
        assertFalse(result.getSuccess());
    }
}