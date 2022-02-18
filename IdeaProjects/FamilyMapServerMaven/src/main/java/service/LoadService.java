package service;


import requests.LoadRequest;
import results.LoadResult;

/**
 * contains method to load information into database
 */
public class LoadService {

    /**
     * Clears all data from the database (just like the /clear API)
     * Loads the user, person, and event data from the request body into the database.
     *
     * @param loadRequest
     * @return success/failure information
     */
    public LoadResult load(LoadRequest loadRequest) {
        return new LoadResult("Error",false);
    }

}
