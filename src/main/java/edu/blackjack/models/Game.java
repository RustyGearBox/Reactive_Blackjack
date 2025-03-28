package edu.blackjack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.blackjack.enums.Figures;
import edu.blackjack.enums.GameResult;
import edu.blackjack.enums.GameState;
import edu.blackjack.enums.Values;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__({@PersistenceConstructor}))
@Document(collection = "games")
public class Game {
    
    @Id
    private String gameId;
    private Long createdAt;
    private String playerName;
    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private GameState state;
    private GameResult result;

    //Generates a deck of cards and shuffles it
    public static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Figures figure : Figures.values()) {
            for (Values value : Values.values()) {
                deck.add(new Card(figure, value));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

    //Deals a card from the deck, deleting it from the deck
    public Card dealCard(List<Card> deck) {
        return deck.remove(0);
    }

    //Calculates the value of a hand
    public int getHandValue(List<Card> hand) {
        int handValue = 0;

        for (Card card : hand) {
            switch (card.getValue()) {
                case A -> handValue += card.getValue().getValue();
                case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE -> handValue += card.getValue().getValue();
                case J, Q, K -> handValue += card.getValue().getValue();
            }
        }
        return handValue;
    }

    //Determines the winner of the game
    public GameResult getWinner(List<Card> playerHand, List<Card> dealerHand) {
        int playerHandValue = getHandValue(playerHand);
        int dealerHandValue = getHandValue(dealerHand);

        //Interger.compare returns -1 if the first value is less than the second, 0 if they are equal and 1 if the first value is greater than the second
        return switch (Integer.compare(playerHandValue, dealerHandValue)) {
            case 1 -> playerHandValue > 21 ? GameResult.DEFEAT : GameResult.VICTORY;
            case -1 -> dealerHandValue > 21 ? GameResult.VICTORY : GameResult.DEFEAT;
            case 0 -> playerHandValue == 21 && dealerHandValue == 21 ? GameResult.DRAW : GameResult.DRAW;
            default -> GameResult.DRAW;
        };
    }

}
