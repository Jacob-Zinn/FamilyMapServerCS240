package dao;

import db.DataAccessException;
import models.Event;
import models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with person table
 */
public class PersonDao {

    private final Connection conn;

    public PersonDao(Connection conn) {
        this.conn=conn;
    }

    /**
     * creates new entry in person table
     * @param person - the person info object to be inserted
     */
    public void insertPerson(Person person) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql="INSERT INTO person (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    /**
     * @return a single person with the specified personId
     */
    public Person getPerson(String personID, String associatedUsername) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql="SELECT * FROM person WHERE personID = ? AND associatedUsername = ?;";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            stmt.setString(2, associatedUsername);
            rs=stmt.executeQuery();
            if (rs.next()) {
                person=new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an person in the database");
        }
    }

    /**
     * @return all of the persons in person table
     */
    public Person[] getPersons(String associatedUsername) throws DataAccessException {
        List<Person> persons = new ArrayList<>();
        ResultSet rs;
        String sql="SELECT * FROM person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            rs=stmt.executeQuery();
            while (rs.next()) {
                persons.add(new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"))
                );
            }
            Person[] personsArray = new Person[persons.size()];
            personsArray = persons.toArray(personsArray);

            return personsArray;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an person in the database");
        }
    }

    /**
     * removes all entries from the person table associated to username
     */
    public void deletePersonsForUser(String username) throws DataAccessException {
        String sql= "DELETE FROM person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing persons for user");
        }
    }

    /**
     * removes all entries from the person table
     */
    public void nukeTable() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM person";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
