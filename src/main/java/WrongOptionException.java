public class WrongOptionException extends ReservationCustomException {

    private int errorCode = 101;

    public WrongOptionException(String message) {
        super(message);
    }

    int getErrorCode() {
        return errorCode;
    }
}
