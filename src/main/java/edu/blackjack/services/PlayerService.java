package edu.blackjack.services;

import org.springframework.stereotype.Service;

import edu.blackjack.models.Player;
import edu.blackjack.models.Request.Player.PlayerCreateRequest;
import edu.blackjack.models.Request.Player.PlayergameDeleteRequest;
import edu.blackjack.models.Request.Player.PlayergameFindRequest;
import edu.blackjack.models.Request.Player.PlayergameUpdateRequest;
import edu.blackjack.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    // Create a new player with the given name
    public Mono<Player> createPlayer(PlayerCreateRequest playerCreateRequest) {
        return playerRepository.save(Player.builder()
            .name(playerCreateRequest.getName())
            .build());
    }

    // Delete a player by its name
    public Mono<Void> deletePlayer(PlayergameDeleteRequest playergameDeleteRequest) {
        return playerRepository.deleteByName(playergameDeleteRequest.getName());
    }

    // Get a player by its name
    public Mono<Player> getPlayer(PlayergameFindRequest playergameFindRequest) {
        return playerRepository.findByName(playergameFindRequest.getName());
    }

    // Update a player by its name
    public Mono<Player> updatePlayer(PlayergameUpdateRequest playergameUpdateRequest) {
        Player player = playerRepository.findByName(playergameUpdateRequest.getName()).block();
        player.setName(playergameUpdateRequest.getNewName());
        return playerRepository.save(player);
    }

    // Get all players
    public Flux<Player> getPlayers() {
        return playerRepository.findAll();
    }

}
