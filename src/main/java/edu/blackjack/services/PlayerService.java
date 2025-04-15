package edu.blackjack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.blackjack.exceptions.customs.PlayerAlreadyExistsException;
import edu.blackjack.exceptions.customs.PlayerNotFoundException;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    // Create a new player with the given name
    public Mono<Player> createPlayer(PlayerCreateRequest playerCreateRequest) {
        return playerRepository.findByName(playerCreateRequest.getName())
            .flatMap(existingPlayer -> Mono.<Player>error(new PlayerAlreadyExistsException(
                "PlayerService/createPlayer: The player with the name " + playerCreateRequest.getName() + " already exists.")))
            .switchIfEmpty(Mono.defer(() -> playerRepository.save(Player.builder().name(playerCreateRequest.getName()).build())));
    }

    // Delete a player by its name
    public Mono<Void> deletePlayer(PlayerDeleteRequest playerDeleteRequest) {
        return playerRepository.findByName(playerDeleteRequest.getName())
            .switchIfEmpty(Mono.error(new PlayerNotFoundException(
                "PlayerService/deletePlayer: The player with the name " + playerDeleteRequest.getName() + " was not found."
            )))
            .flatMap(player -> playerRepository.deleteByName(player.getName()));
    }

    // Get a player by its name
    public Mono<Player> getPlayer(PlayerFindRequest playerFindRequest) {
        return playerRepository.findByName(playerFindRequest.getName())
        .switchIfEmpty(Mono.error(new PlayerNotFoundException(
            "PlayerService/getPlayer: The player with the name " + playerFindRequest.getName() + " was not found.")));
    }

    // Update a player by its name
    public Mono<Player> updatePlayer(PlayerUpdateRequest playerUpdateRequest) {
        return playerRepository.findByName(playerUpdateRequest.getName())
        .flatMap(foundPlayer -> {
            foundPlayer.setName(playerUpdateRequest.getNewName());
            return playerRepository.save(foundPlayer);
        })
        .switchIfEmpty(Mono.error(new PlayerNotFoundException(
            "PlayerService/updatePlayer: The player with the name " + playerUpdateRequest.getName() + " was not found.")));
    }

    // Get all players
    public Flux<Player> getPlayers() {
        return playerRepository.findAll()
        .switchIfEmpty(Mono.error(new PlayerNotFoundException(
            "PlayerService/getPlayers: There are no players.")));
    }

}
