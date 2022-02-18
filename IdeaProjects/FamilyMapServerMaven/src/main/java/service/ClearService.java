package service;


import results.ClearResult;

/**
 * contains method for clearing information from database
 */
public class ClearService {

    /**
     * Deletes ALL data from the database, including user, authtoken, person, and event data
     */
    public ClearResult clear() {
        return new ClearResult("Error",false);
    }
}
