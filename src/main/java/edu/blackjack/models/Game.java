package edu.blackjack.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import edu.blackjack.enums.Figures;
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

    //Generates a deck of cards
    public static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Figures figure : Figures.values()) {
            for (Values value : Values.values()) {
                deck.add(new Card(figure, value));
            }
        }
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
    public String getWinner(List<Card> playerHand, List<Card> dealerHand) {
        int playerHandValue = getHandValue(playerHand);
        int dealerHandValue = getHandValue(dealerHand);

        return switch (Integer.compare(playerHandValue, dealerHandValue)) {
            case 1 -> playerHandValue > 21 ? "Dealer wins!" : "Player wins!";
            case -1 -> dealerHandValue > 21 ? "Player wins!" : "Dealer wins!";
            default -> "It's a tie!";
        };
    }

}
