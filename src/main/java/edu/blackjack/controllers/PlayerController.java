package edu.blackjack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.blackjack.models.Player;
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
    public Mono<Player> createPlayer(@RequestBody String playerName) {
        return playerService.createPlayer(playerName);
    }

    // Get a player by its name
    @GetMapping
    public Mono<Player> getPlayer(@RequestParam String name) {
        return playerService.getPlayer(name);
    }
    
    // Delete a player by its name
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePlayer(@RequestParam String name) {
        return playerService.deletePlayer(name);
    }

    // Update a player by its name
    @PutMapping
    public Mono<Player> updatePlayer(@RequestBody String name) {
        return playerService.updatePlayer(name);
    }

    // Get all players
    @GetMapping("/all")
    public Iterable<Player> getPlayers() {
        return playerService.getPlayers();
    }
    
}
