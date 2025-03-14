package edu.blackjack.exceptions.customs;

public class GameFinishedException extends RuntimeException {
    
    public GameFinishedException(String message) {
        super(message);
    }
    
}
