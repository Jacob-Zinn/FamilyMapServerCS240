package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

    RegisterService registerService;
    ClearService clearService;
    RegisterRequest fakeRegisterRequest = new RegisterRequest(
            "username",
            "password",
            "email",
            "firstName",
            "lastName",
            "m"
    );

    @BeforeEach
    void setUp() {
        registerService = new RegisterService();
        clearService = new ClearService();
        clearService.clear();

        fakeRegisterRequest = new RegisterRequest(
                "username",
                "password",
                "email",
                "firstName",
                "lastName",
                "m"
        );
    }

    @Test
    void register() {

        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        assertTrue(registerResult.getAuthToken().length() > 0);
        assertTrue(registerResult.getPersonID().length() > 0);
        assertEquals("username", registerResult.getUsername());
        assertTrue(registerResult.getSuccess());

    }

    @Test
    void register_fail_usernameTaken() {
        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        assertTrue(registerResult.getSuccess());
        RegisterResult failedResult = registerService.register(fakeRegisterRequest);
        assertFalse(failedResult.getSuccess());
    }

    @Test
    void register_fail_genderIncorrect() {
        fakeRegisterRequest.setGender("m/f");
        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        assertFalse(registerResult.getSuccess());
    }
}