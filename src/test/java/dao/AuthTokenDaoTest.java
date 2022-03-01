package dao;

import db.DataAccessException;
import db.Database;
import models.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenDaoTest {

    private Database db;
    private AuthToken fakeAuthToken;
    private AuthTokenDao authTokenDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db=new Database();
        fakeAuthToken=new AuthToken(";laksdjf;alskdjf;lakjsdf", "jacobzinn");
        Connection conn=db.getConnection();
        authTokenDao=new AuthTokenDao(conn);
        authTokenDao.nukeTable();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    void insertAuthTokenPass() throws DataAccessException {
        authTokenDao.insertAuthToken(fakeAuthToken);
        AuthToken compareTest=authTokenDao.getAuthToken(fakeAuthToken.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(fakeAuthToken, compareTest);
    }

    @Test
    void insertAuthTokenFail() throws DataAccessException {
        authTokenDao.insertAuthToken(fakeAuthToken);
        assertThrows(DataAccessException.class, () -> authTokenDao.insertAuthToken(fakeAuthToken));
    }

    @Test
    void getAuthTokenPass() throws DataAccessException {
        authTokenDao.insertAuthToken(fakeAuthToken);
        AuthToken authTokenReturned=authTokenDao.getAuthToken(fakeAuthToken.getAuthToken());
        assertNotNull(authTokenReturned);
        assertEquals(fakeAuthToken, authTokenReturned);
    }

    @Test
    void getAuthTokenFail() throws DataAccessException {
        assertNull(authTokenDao.getAuthToken(fakeAuthToken.getAuthToken()));
    }

    @Test
    void nukeTable() throws DataAccessException {
        authTokenDao.insertAuthToken(fakeAuthToken);
        authTokenDao.nukeTable();
        assertNull(authTokenDao.getAuthToken(fakeAuthToken.getAuthToken()));
    }
}