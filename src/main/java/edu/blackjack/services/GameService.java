package edu.blackjack.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import edu.blackjack.enums.GameState;
import edu.blackjack.exceptions.customs.GameNotFoundException;
import edu.blackjack.models.Game;
import edu.blackjack.models.Request.PlayRequest;
import edu.blackjack.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {
    
    private final GameRepository gameRepository;

    // Create a new game with the given player name
    public Mono<Game> createGame(String playerName) {
        return gameRepository.save(
            Game.builder()
                .createdAt(System.currentTimeMillis())
                .playerName(playerName)
                .deck(Game.createDeck())
                .playerHand(new ArrayList<>())
                .dealerHand(new ArrayList<>())
                .state(GameState.NEW)
                .result("Undetermined")
                .build());
    }

    // Update a game by its ID
    public Mono<Game> updateGame(PlayRequest playRequest) {
        
        // Get the game by its ID
        return gameRepository.findByGameId(playRequest.getGameId())
            .switchIfEmpty(Mono.error(new GameNotFoundException("GameService/updateGame: Error Game not found with id: " + playRequest.getGameId())))
            .flatMap(game -> {
                // If the game is already over, return the game
                if (game.getState() != GameState.IN_PROGRESS && game.getState() != GameState.NEW) {
                    return Mono.just(game);
                }

                switch (playRequest.getPlayType()) {
                    
                    // If the player wants to hit, add a card to the player's hand
                    case "HIT":
                        game.getPlayerHand().add(game.getDeck().remove(0));
                        if (game.getHandValue(game.getPlayerHand()) > 21) {
                            game.setState(GameState.FINISHED);
                            game.setResult("Dealer wins");
                        }
                        break;

                    // If the player wants to stand, add cards to the dealer's hand until the dealer's hand value is 17 or higher
                    case "STAND":
                        while (game.getHandValue(game.getDealerHand()) < 17) {
                            game.getDealerHand().add(game.getDeck().remove(0));
                        }
                        game.setResult(game.getWinner(game.getPlayerHand(), game.getDealerHand()));
                        game.setState(GameState.FINISHED);
                        break;

                    default:
                        return Mono.error(new IllegalArgumentException("GameService/updateGame: Error Invalid play type: " + playRequest.getPlayType()));
                }

                // Save the updated game
                return gameRepository.save(game);
            });
    }

    // Get a game by its ID
    public Mono<Game> getGame(String gameId) {
        return gameRepository.findByGameId(gameId)
        .switchIfEmpty(Mono.error(new GameNotFoundException("GameService/getGame: Error Game not found with id: " + gameId)));
    }

    // Delete a game by its ID
    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.deleteById(gameId)
        .switchIfEmpty(Mono.error(new GameNotFoundException("GameService/deleteGame: Error Game not found with id: " + gameId)));
    }

}
