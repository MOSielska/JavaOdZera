public class OnlyNumberException extends ReservationCustomException {

    private int errorCode = 102;

    public OnlyNumberException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
