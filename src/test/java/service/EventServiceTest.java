package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.FillRequest;
import requests.RegisterRequest;
import results.EventResult;
import results.EventsResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

    EventService eventService;
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

        eventService = new EventService();
        registerService = new RegisterService();
        fillService = new FillService();

        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        authToken = registerResult.getAuthToken();
        personID = registerResult.getPersonID();
        fillService.fill(new FillRequest(fakeRegisterRequest.getUsername(), 1));
    }

    @Test
    void getEvents() {
        EventsResult result = eventService.getEvents(authToken);
        assertEquals(7, result.getData().length);
    }

    @Test
    void getEvents_fail_notAuthorized() {
        EventsResult result = eventService.getEvents("notanauth");
        assertFalse(result.getSuccess());
    }

    @Test
    void getEvents_fail_nullParam() {
        EventsResult result = eventService.getEvents(null);
        assertFalse(result.getSuccess());
    }

    @Test
    void getEvent() {
        EventsResult eventsResult = eventService.getEvents(authToken);
        assertTrue(eventsResult.getData().length > 0);
        String eventID = eventsResult.getData()[1].getEventID();

        EventResult result = eventService.getEvent(authToken, eventID);
        assertTrue(result.getSuccess());
    }

    @Test
    void getEvent_fail_nullParam() {
        EventResult result = eventService.getEvent(null, null);
        assertFalse(result.getSuccess());
    }
}