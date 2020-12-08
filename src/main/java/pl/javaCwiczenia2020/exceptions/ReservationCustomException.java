package pl.javaCwiczenia2020.exceptions;

abstract public class ReservationCustomException extends RuntimeException{

    public ReservationCustomException(String message) {
        super(message);
    }

    abstract public int getErrorCode();
}
