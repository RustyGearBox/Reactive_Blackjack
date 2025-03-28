package edu.blackjack.exceptions.customs;

public class GameAlreadyFinishedException extends RuntimeException {
    public GameAlreadyFinishedException(String message) {
        super(message);
    }
    
}
