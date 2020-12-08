package pl.javaCwiczenia2020.exceptions;

public class WrongOptionException extends ReservationCustomException {

    private final int errorCode = 101;

    public WrongOptionException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
