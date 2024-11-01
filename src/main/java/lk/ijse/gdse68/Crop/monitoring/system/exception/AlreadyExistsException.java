package lk.ijse.gdse68.Crop.monitoring.system.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {}
    public AlreadyExistsException(String message) {
        super(message);
    }
    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
