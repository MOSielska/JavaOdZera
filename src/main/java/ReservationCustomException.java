abstract class ReservationCustomException extends RuntimeException{

    public ReservationCustomException(String message) {
        super(message);
    }

    abstract int getErrorCode();
}
