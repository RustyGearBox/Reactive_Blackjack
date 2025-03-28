package edu.blackjack.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import edu.blackjack.enums.GameResult;
import edu.blackjack.enums.GameState;
import edu.blackjack.exceptions.customs.GameAlreadyFinishedException;
import edu.blackjack.exceptions.customs.GameNotFoundException;
import edu.blackjack.models.Game;
import edu.blackjack.models.Request.Game.GameCreateRequest;
import edu.blackjack.models.Request.Game.GameDeleteRequest;
import edu.blackjack.models.Request.Game.GameUpdateRequest;
import edu.blackjack.repositories.GameRepository;
import edu.blackjack.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {
    
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    // Create a new game with the given player name
    public Mono<Game> createGame(GameCreateRequest gameCreateRequest) {
        return playerRepository.findByName(gameCreateRequest.getName())
            .switchIfEmpty(Mono.error(new GameNotFoundException("GameService/createGame: Error Player not found with name: " + gameCreateRequest.getName())))
            .flatMap(player -> {
                Game game = Game.builder()
                    .createdAt(System.currentTimeMillis())
                    .playerName(gameCreateRequest.getName())
                    .deck(Game.createDeck())
                    .playerHand(new ArrayList<>())
                    .dealerHand(new ArrayList<>())
                    .state(GameState.NEW)
                    .result(GameResult.UNDEFINED)
                    .build();
                return gameRepository.save(game);
            });
    }

    // Update a game by its ID
    public Mono<Game> updateGame(GameUpdateRequest gameUpdateRequest) {
        
        if (gameUpdateRequest.getState() == GameState.FINISHED) {
            return Mono.error(new GameAlreadyFinishedException("GameService/updateGame: Error Game is already finished."));
        }

        // Get the game by its ID
        return gameRepository.findByGameId(gameUpdateRequest.getGameId())
            .switchIfEmpty(Mono.error(new GameNotFoundException("GameService/updateGame: Error Game not found with id: " + gameUpdateRequest.getGameId())))
            .flatMap(game -> {
                // If the game is already over, return the game
                if (game.getState() != GameState.IN_PROGRESS && game.getState() != GameState.NEW) {
                    return Mono.just(game);
                }

                switch (gameUpdateRequest.getPlayType()) {
                    
                    // If the player wants to hit, add a card to the player's hand
                    case HIT:
                        game.getPlayerHand().add(game.getDeck().remove(0));
                        if (game.getHandValue(game.getPlayerHand()) > 21) {
                            game.setState(GameState.FINISHED);
                            game.setResult(GameResult.DEFEAT);
                        }
                        break;

                    // If the player wants to stand, add cards to the dealer's hand until the dealer's hand value is 17 or higher
                    case STAND:
                        while (game.getHandValue(game.getDealerHand()) < 17) {
                            game.getDealerHand().add(game.getDeck().remove(0));
                        }
                        game.setResult(game.getWinner(game.getPlayerHand(), game.getDealerHand()));
                        game.setState(GameState.FINISHED);
                        break;

                    default:
                        return Mono.error(new IllegalArgumentException("GameService/updateGame: Error Invalid play type: " + gameUpdateRequest.getPlayType()));
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
    public Mono<Void> deleteGame(GameDeleteRequest gameDeleteRequest) {
        return gameRepository.deleteById(gameDeleteRequest.getGameId())
        .switchIfEmpty(Mono.error(new GameNotFoundException("GameService/deleteGame: Error Game not found with id: " + gameDeleteRequest.getGameId())));
    }

}
