package dao;

import db.DataAccessException;
import db.Database;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private Database db;
    private User dummyUser;
    private UserDao userDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        dummyUser= new User("jacobzinn", "greatPass", "jacobpzinn@gmail.com", "jacob", "zinn", "m", "GreatUSerID");
        Connection conn = db.getConnection();
        userDao = new UserDao(conn);
        userDao.nukeTable();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    void insertUserPass() throws DataAccessException {
        userDao.insertUser(dummyUser);
        User compareTest = userDao.getUser(dummyUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(dummyUser, compareTest);
    }

    @Test
    void insertUserFail() throws DataAccessException {
        userDao.insertUser(dummyUser);
        assertThrows(DataAccessException.class, () -> userDao.insertUser(dummyUser));
    }

    @Test
    void getUserPass() throws DataAccessException {
        userDao.insertUser(dummyUser);
        User userReturned = userDao.getUser(dummyUser.getUsername());
        assertNotNull(userReturned);
        assertEquals(dummyUser, userReturned);
    }

    @Test
    void getUserFail() throws DataAccessException {
        assertNull(userDao.getUser(dummyUser.getUsername()));
    }

    @Test
    void nukeTable() throws DataAccessException {
        userDao.insertUser(dummyUser);
        userDao.nukeTable();
        assertNull(userDao.getUser(dummyUser.getUsername()));
    }
}