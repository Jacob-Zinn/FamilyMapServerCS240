package service;

import api.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import results.ClearResult;

import static org.junit.jupiter.api.Assertions.*;

class ClearServiceTest {

    ClearService clearService;
    RegisterService registerService;
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
        clearService = new ClearService();
        registerService = new RegisterService();
    }

    @Test
    void clear_withData() throws BadRequestException {
        registerService.register(fakeRegisterRequest);
        ClearResult clearResult = clearService.clear();
        assertTrue(clearResult.getSuccess());
    }

    @Test
    void clear_noData() {
        ClearResult clearResult = clearService.clear();
        assertTrue(clearResult.getSuccess());
        assertTrue(clearResult.getMessage().length() > 0);
    }

}