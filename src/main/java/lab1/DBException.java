package lab1;

public class DBException extends RuntimeException {
    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }
}