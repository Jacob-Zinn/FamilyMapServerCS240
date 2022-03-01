package api;

public class BadMethodException extends Exception {
    public BadMethodException() {
        super("Incorrect http method type");
    }
}
