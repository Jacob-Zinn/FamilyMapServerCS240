package results;

import models.Event;

/**
 * Get - Returns an http response containing an array of Events
 */
public class EventsResult {

    /**
     * A json array of all the persons related to current user
     */
    private Event[] data;

    /**
     * Contains the error response in the event of failed call
     */
    private String message;

    /**
     * indicates request success or failure of events request
     */
    private Boolean success;


    /**
     *
     * EventsResult constructor instantiating all success variables
     *
     * @param data
     * @param success
     */
    public EventsResult(Event[] data, Boolean success) {
        this.data=data;
        this.success=success;
    }

    /**
     * instantiates all failure related vars
     *
     * @param message
     * @param success
     */
    public EventsResult(String message, Boolean success) {
        this.message=message;
        this.success=success;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
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

// /event

/*
Success Response Body:
Note: Each entry in the json array has the same attributes as given for the Event model in the Data Definitions section.
{
	"data":[json array of Event objects],		// Event[]
        "success":true						// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	// string
        "success":false							// boolean
}

 */