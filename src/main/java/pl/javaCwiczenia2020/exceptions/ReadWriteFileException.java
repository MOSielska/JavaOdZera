package pl.javaCwiczenia2020.exceptions;

public class ReadWriteFileException extends ReservationCustomException {

    private final int errorCode = 103;

    public ReadWriteFileException(String fileName, String operationType, String message) {
        super(String.format("Unable to perform %s on %s (%s)", fileName, operationType, message));
    }

    public int getErrorCode() {
        return  this.errorCode;
    }
}
