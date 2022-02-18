package models;

/**
 * root user whose information is used as data is extracted.
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender; // f or m
    private String personID;

    /**
     *
     * constructor used to instantiated new User object with all params included
     *
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.personID=personID;
    }
}


