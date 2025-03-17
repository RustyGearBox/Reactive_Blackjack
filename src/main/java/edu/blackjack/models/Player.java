package edu.blackjack.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import edu.blackjack.exceptions.customs.GameResultNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "players")
public class Player {
    
    @Id
    private String id;
    private String name;
    @Builder.Default
    private int gamesPlayed = 0;
    @Builder.Default
    private int victories = 0;
    @Builder.Default
    private int defeats = 0;
    @Builder.Default
    private int draws = 0;
    @Builder.Default
    private int score = 0;
    
    //Adds a game result to the player's statistics
    public void addGameResult(Game game) {
        gamesPlayed++;
        switch (game.getResult()) {
            case VICTORY -> victories++;
            case DEFEAT -> defeats++;
            case DRAW -> draws++;
            case UNDEFINED -> {
            throw new GameResultNotFoundException("Game result is undefined.");
            }
        }
    }

    //Calculates the player's score
    public void calculateScore() {
        score = victories * 3 + draws;
    }

    //Resets the player's statistics
    public void resetStats() {
        gamesPlayed = 0;
        victories = 0;
        defeats = 0;
        draws = 0;
        score = 0;
    }

}
