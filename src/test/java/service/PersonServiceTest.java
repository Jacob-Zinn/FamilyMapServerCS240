package service;

import org.junit.jupiter.api.AfterEach;
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
    void setUp() {
        clearService = new ClearService();
        clearService.clear();


        personService = new PersonService();
        registerService = new RegisterService();
        fillService = new FillService();

        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        authToken = registerResult.getAuthToken();
        personID = registerResult.getPersonID();
        fillService.fill(new FillRequest(fakeRegisterRequest.getUsername(), 2));
    }

    @Test
    void getPerson() {
        PersonResult result = personService.getPerson(authToken, personID);
        assertTrue(result.getSuccess());
        assertEquals(fakeRegisterRequest.getFirstName(), result.getFirstName());
    }

    @Test
    void getPerson_fail_notAuthorized() {
        PersonResult result = personService.getPerson("notanauth", personID);
        assertFalse(result.getSuccess());
    }

    @Test
    void getPerson_fail_nullParam() {
        PersonResult result = personService.getPerson(null, null);
        assertFalse(result.getSuccess());
    }

    @Test
    void getPersons() {
        PersonsResult result = personService.getPersons(authToken);
        assertEquals(7, result.getData().length);
    }

    @Test
    void getPersons_fail_notAuthorized() {
        PersonsResult result = personService.getPersons("notanauth");
        assertFalse(result.getSuccess());
    }

    @Test
    void getPersons_fail_nullParam() {
        PersonsResult result = personService.getPersons(null);
        assertFalse(result.getSuccess());
    }
}