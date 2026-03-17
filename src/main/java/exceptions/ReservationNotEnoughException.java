package exceptions;

public class ReservationNotEnoughException extends RuntimeException{
    public ReservationNotEnoughException(String message){
        super(message);
    }
}
