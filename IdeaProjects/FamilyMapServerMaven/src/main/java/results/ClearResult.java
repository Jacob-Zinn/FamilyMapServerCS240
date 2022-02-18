package results;

/**
 * http response contianing info on whether the recent clearing of tables was successful
 */
public class ClearResult {

    /**
     * reasoning behind success or failure
     */
    private String message;

    /**
     * indicates request success or failure
     */
    private Boolean success;


    /**
     *
     * ClearResult constructor instantiating all variables
     *
     * @param message
     * @param success
     */
    public ClearResult(String message, Boolean success) {
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
	"message":"Clear succeeded.",	        // string
        "success":true				// boolean
}

Error Response Body:
{
	"message":"Error:[Description of the error]",	                //string
        "success":false							// boolean
}

 */