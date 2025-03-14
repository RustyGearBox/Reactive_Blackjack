package edu.blackjack.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import edu.blackjack.enums.GameState;

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
public class Game {
    
    @Id
    private String id;
    private Long createdAt;
    private String playerName;
    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private GameState state;
    private String result;

}
