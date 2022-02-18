package requests;

/**
 * includes parameters to be used in the login method within LoginService
 */
public class LoginRequest {

    /**
     * identifies user to be logged in
     */
    private String username;

    /**
     * authenticates user to be logged in
     */
    private String password;


    /**
     *
     * LoginRequest constructor with all params
     *
     * @param username
     * @param password
     */
    public LoginRequest(String username, String password) {
        this.username=username;
        this.password=password;
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
}
