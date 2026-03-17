package controller;

import exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleException(UserNotFoundException ex){
        return new ErrorResponse("user.not.found", ex.getMessage());
    }

    // USER active deyil
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotActiveException.class)
    public ErrorResponse handleException(UserNotActiveException ex){
        return new ErrorResponse("user.not.active", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handleException(BookNotFoundException ex){
        return new ErrorResponse("book.not.found", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(StockNotEnoughException.class)
    public ErrorResponse handleException(StockNotEnoughException ex){
        return new ErrorResponse("stock.not.enough", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotEnoughException.class)
    public ErrorResponse handleException(ReservationNotEnoughException ex){
        return new ErrorResponse("reservation.not.found", ex.getMessage());
    }
}


