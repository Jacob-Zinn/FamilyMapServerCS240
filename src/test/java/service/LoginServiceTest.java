package service;

import api.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.FillRequest;
import requests.LoginRequest;
import requests.RegisterRequest;
import results.LoginResult;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    LoginService loginService;
    RegisterService registerService;
    ClearService clearService;

    @BeforeEach
    void setUp() throws BadRequestException {
        clearService = new ClearService();
        clearService.clear();

        loginService = new LoginService();
        registerService = new RegisterService();
        registerService.register(new RegisterRequest("jacobZinn","goodPassword", "email", "jacob", "zinn", "m"));
    }

    @Test
    void login() throws BadRequestException {
        LoginRequest loginRequest = new LoginRequest("jacobZinn", "goodPassword");
        LoginResult loginResult = loginService.login(loginRequest);

        assertTrue(loginResult.getSuccess());
        assertEquals("jacobZinn", loginResult.getUsername());
    }


    @Test
    void login_fail_nullParams() throws BadRequestException {
        LoginResult result = loginService.login(new LoginRequest(null, null));
        assertFalse(result.getSuccess());
    }

    @Test
    void login_fail_incorrectPassword() throws BadRequestException {
        LoginResult result = loginService.login(new LoginRequest("jacobZinn",  "incorrect"));
        assertFalse(result.getSuccess());
    }
}