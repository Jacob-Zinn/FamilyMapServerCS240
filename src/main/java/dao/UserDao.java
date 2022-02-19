package dao;

import db.DataAccessException;
import models.User;

import java.sql.*;

/**
 * Interacts with user table
 */
public class UserDao {


    private final Connection conn;

    public UserDao(Connection conn) {
        this.conn=conn;
    }

    /**
     * creates new entry in user table
     *
     * @param user
     */
    public void insertUser(User user) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO user (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an user into the database");
        }
    }

    /**
     * @return a single user with the specified username
     */
    public User getUser(String username) throws DataAccessException {
        User user;
        ResultSet rs;
        String sql="SELECT * FROM user WHERE username = ?;";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs=stmt.executeQuery();
            if (rs.next()) {
                user=new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an user in the database");
        }
    }

    /**
     * removes all entries from the user table
     */
    public void nukeTable() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM user";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
