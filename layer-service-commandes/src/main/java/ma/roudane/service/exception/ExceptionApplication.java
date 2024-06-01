package ma.roudane.service.exception;

public class ExceptionApplication extends RuntimeException{

    public ExceptionApplication(String message) {
        super(message);
    }
}
