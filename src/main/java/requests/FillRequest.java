package requests;

/**
 * includes parameters to be used in requesting the family history for an individual
 *
 * fill/username/{generations}
 */
public class FillRequest {

    /**
     * username used in database query for user family history
     */
    private String username;

    /**
     * number of generations queried for user from database
     *
     * defualt number is 4
     */
    private int generations = 4;


    /**
     *
     * FillRequest constructor instantiating all variables
     *
     * @param username
     * @param generations
     */
    public FillRequest(String username, int generations) {
        this.username=username;
        this.generations=generations;
    }

    /**
     * instantiates username variable. Generations var set to default: 4
     * @param username
     */
    public FillRequest(String username) {
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations=generations;
    }
}
