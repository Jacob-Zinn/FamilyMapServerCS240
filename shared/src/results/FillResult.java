package results;

/**
 * contains success and failure indicators pertaining to the database population with respective username
 *
 * /fill/[username]/{generations}
 */
public class FillResult {

    /**
     * contains reasoning behind success or failure of request
     */
    private String message;

    /**
     * indicates request success or failure of fill request
     */
    private Boolean success;

    /**
     *
     * FillResult constructor instantiating all variables
     *
     * @param message
     * @param success
     */
    public FillResult(String message, Boolean success) {
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
	"message":"Successfully added X persons and Y events to the database.",			//string
        "success":true		// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	// string
        "success":false							// boolean
}

 */