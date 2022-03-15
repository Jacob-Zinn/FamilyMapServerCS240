package results;


/**
 * contains success and failure indicators pertaining to the database population
 */
public class LoadResult {

    /**
     * contains reasoning behind success or failure of request
     */
    private String message;

    /**
     * indicates request success or failure
     */
    private Boolean success;

    /**
     *
     * LoadResult constructor instantiating all variables
     *
     * @param message
     * @param success
     */
    public LoadResult(String message, Boolean success) {
        this.message=message;
        this.success=success;
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

/*
Success Response Body:
{
	"message":"Successfully added X users, Y persons, and Z events to the database.",			//string
        "success":true 		// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	//string
        "success":false							// boolean
}

 */