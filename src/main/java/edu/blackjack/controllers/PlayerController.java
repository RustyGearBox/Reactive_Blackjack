package edu.blackjack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.blackjack.models.Player;
import edu.blackjack.models.Request.Player.PlayerCreateRequest;
import edu.blackjack.models.Request.Player.PlayerDeleteRequest;
import edu.blackjack.models.Request.Player.PlayerFindRequest;
import edu.blackjack.models.Request.Player.PlayerUpdateRequest;
import edu.blackjack.services.PlayerService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class PlayerController {
    
    private final PlayerService playerService;

    // Create a new player with the given name
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Player> createPlayer(@RequestBody PlayerCreateRequest playerCreateRequest) {
        return playerService.createPlayer(playerCreateRequest);
    }

    // Get a player by its name
    @GetMapping
    public Mono<Player> getPlayer(@RequestParam PlayerFindRequest playerFindRequest) {
        return playerService.getPlayer(playerFindRequest);
    }
    
    // Delete a player by its name
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePlayer(@RequestParam PlayerDeleteRequest playerDeleteRequest) {
        return playerService.deletePlayer(playerDeleteRequest);
    }

    // Update a player by its name
    @PutMapping
    public Mono<Player> updatePlayer(@RequestBody PlayerUpdateRequest playerUpdateRequest) {
        return playerService.updatePlayer(playerUpdateRequest);
    }

    // Get all players
    @GetMapping("/all")
    public Iterable<Player> getPlayers() {
        return playerService.getPlayers();
    }
    
}
