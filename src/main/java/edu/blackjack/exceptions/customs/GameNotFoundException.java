package edu.blackjack.exceptions.customs;

public class GameNotFoundException extends RuntimeException {
    
    public GameNotFoundException(String message) {
        super(message);
    }
    
}
