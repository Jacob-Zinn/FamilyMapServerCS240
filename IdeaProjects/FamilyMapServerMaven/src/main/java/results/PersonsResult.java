package results;

import models.Person;

/**
 * Returns an object containing the family members of the current user.
 */
public class PersonsResult {

    /**
     * A json array of all the persons related to current user
     */
    private Person[] data;

    /**
     * Contains the error response in the event of failed call
     */
    private String message;

    /**
     * indicates the success of the response
     */
    private Boolean success;


    /**
     *
     * PersonsResult constructor instantiating all success variables
     *
     * @param data
     * @param success
     */
    public PersonsResult(Person[] data, Boolean success) {
        this.data=data;
        this.success=success;
    }

    /**
     * instantiates all vars indicative of failure
     *
     * @param message
     * @param success
     */
    public PersonsResult(String message, Boolean success) {
        this.message=message;
        this.success=success;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data=data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success=success;
    }
}

// /person
/*
{
	"data":[json array of Person objects],		// Person[]
        "success":true						// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	// string
        "success":false							// boolean
}

 */