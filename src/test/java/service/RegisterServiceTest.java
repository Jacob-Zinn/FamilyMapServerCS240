package service;

import api.BadRequestException;
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
    void register() throws BadRequestException {
        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        assertTrue(registerResult.getAuthtoken().length() > 0);
        assertTrue(registerResult.getPersonID().length() > 0);
        assertEquals("username", registerResult.getUsername());
        assertTrue(registerResult.getSuccess());
    }

    @Test
    void register_fail_usernameTaken() throws BadRequestException {
        RegisterResult registerResult = registerService.register(fakeRegisterRequest);
        assertTrue(registerResult.getSuccess());
        RegisterResult result = registerService.register(fakeRegisterRequest);
        assertFalse(result.getSuccess());
    }

    @Test
    void register_fail_genderIncorrect() throws BadRequestException {
        fakeRegisterRequest.setGender("m/f");
        RegisterResult result = registerService.register(fakeRegisterRequest);
        assertFalse(result.getSuccess());
    }
}