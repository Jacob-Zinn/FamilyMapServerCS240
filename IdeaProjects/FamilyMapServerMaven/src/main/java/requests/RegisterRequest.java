package requests;

/**
 * includes parameters to be used in the login method within LoginService
 */
public class RegisterRequest {

    /**
     * username to be used to identify user in database in subsequent api calls
     */
    private String username;

    /**
     * password used to authenticate user in the future
     */
    private String password;

    /**
     * email address of the registering user
     */
    private String email;

    /**
     * first name of the registering user
     */
    private String firstName;

    /**
     * last name of the registering user
     */
    private String lastName;

    /**
     * gender of the registering user represented as string
     */
    private String gender;                // string "f" or "m"


    /**
     *
     * RegisterRequest constructor with all params
     *
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender=gender;
    }
}
