package pl.javaCwiczenia2020.exceptions;

public class OnlyNumberException extends ReservationCustomException {

    private final int errorCode = 102;

    public OnlyNumberException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
