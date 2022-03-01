package dao;

import db.DataAccessException;
import models.AuthToken;

import java.sql.*;

/**
 * Interacts with authentication token table
 */
public class AuthTokenDao {

    private final Connection conn;

    public AuthTokenDao(Connection conn) {
        this.conn=conn;
    }

    /**
     * creates new entry in authToken table
     *
     * @param authToken holds params to be inserted into db
     */
    public void insertAuthToken(AuthToken authToken) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql="INSERT INTO auth_token (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, authToken.getAuthToken());
            stmt.setString(2, authToken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an authToken into the database");
        }
    }


    /**
     * @param authToken - authToken to be queried
     * @return the authToken to prove that it exists
     */
    public AuthToken getAuthToken(String authToken) throws DataAccessException {
        AuthToken newAuthToken;
        ResultSet rs;
        String sql="SELECT * FROM auth_token WHERE authtoken = ?;";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            rs=stmt.executeQuery();
            if (rs.next()) {
                newAuthToken=new AuthToken(rs.getString("authtoken"), rs.getString("username"));
                return newAuthToken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an authToken in the database");
        }
    }

    /**
     * removes all entries from the auth_token table
     */
    public void nukeTable() throws DataAccessException {
        try (Statement stmt=conn.createStatement()) {
            String sql="DELETE FROM auth_token";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

}
