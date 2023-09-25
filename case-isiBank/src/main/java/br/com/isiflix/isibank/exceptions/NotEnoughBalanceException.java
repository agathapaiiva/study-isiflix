package br.com.isiflix.isibank.exceptions;

public class NotEnoughBalanceException extends RuntimeException{
    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
