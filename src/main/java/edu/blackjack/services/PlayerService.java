package edu.blackjack.services;

import org.springframework.stereotype.Service;

import edu.blackjack.models.Player;
import edu.blackjack.models.Request.Player.PlayerCreateRequest;
import edu.blackjack.models.Request.Player.PlayerDeleteRequest;
import edu.blackjack.models.Request.Player.PlayerFindRequest;
import edu.blackjack.models.Request.Player.PlayerUpdateRequest;
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
    public Mono<Void> deletePlayer(PlayerDeleteRequest playerDeleteRequest) {
        return playerRepository.deleteByName(playerDeleteRequest.getName());
    }

    // Get a player by its name
    public Mono<Player> getPlayer(PlayerFindRequest playerFindRequest) {
        return playerRepository.findByName(playerFindRequest.getName());
    }

    // Update a player by its name
    public Mono<Player> updatePlayer(PlayerUpdateRequest playerUpdateRequest) {
        Player player = playerRepository.findByName(playerUpdateRequest.getName()).block();
        player.setName(playerUpdateRequest.getNewName());
        return playerRepository.save(player);
    }

    // Get all players
    public Flux<Player> getPlayers() {
        return playerRepository.findAll();
    }

}
