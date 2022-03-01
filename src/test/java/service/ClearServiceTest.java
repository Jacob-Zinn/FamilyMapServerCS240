package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import results.ClearResult;

import static org.junit.jupiter.api.Assertions.*;

class ClearServiceTest {

    ClearService clearService;

    @BeforeEach
    void setUp() {
        clearService = new ClearService();
    }

    @Test
    void clear() {
        ClearResult clearResult = clearService.clear();
        assertTrue(clearResult.getSuccess());
        assertTrue(clearResult.getMessage().length() > 0);
    }
}