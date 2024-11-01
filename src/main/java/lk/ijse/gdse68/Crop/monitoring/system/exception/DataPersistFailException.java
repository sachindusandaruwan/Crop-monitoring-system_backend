package lk.ijse.gdse68.Crop.monitoring.system.exception;

public class DataPersistFailException extends RuntimeException {
    public DataPersistFailException() {}
    public DataPersistFailException(String message) {
        super(message);
    }
    public DataPersistFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
