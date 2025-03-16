package edu.blackjack.services;

import org.springframework.stereotype.Service;

import edu.blackjack.models.Player;
import edu.blackjack.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    // Create a new player with the given name
    public Mono<Player> createPlayer(String name) {
        return playerRepository.save(Player.builder()
            .name(name)
            .build());
    }

    // Delete a player by its name
    public Mono<Void> deletePlayer(String name) {
        return playerRepository.deleteByName(name);
    }

    // Get a player by its name
    public Mono<Player> getPlayer(String name) {
        return playerRepository.findByName(name);
    }

    // Update a player by its name
    public Mono<Player> updatePlayer(String name) {
        Player player = playerRepository.findByName(name).block();
        player.setName(name);
        return playerRepository.save(player);
    }

    // Get all players
    public Iterable<Player> getPlayers() {
        return playerRepository.findAll().toIterable();
    }

}
