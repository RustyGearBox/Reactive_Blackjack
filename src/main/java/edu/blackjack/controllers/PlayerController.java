package edu.blackjack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.blackjack.models.Player;
import edu.blackjack.models.Request.Player.PlayerCreateRequest;
import edu.blackjack.models.Request.Player.PlayergameDeleteRequest;
import edu.blackjack.models.Request.Player.PlayergameFindRequest;
import edu.blackjack.models.Request.Player.PlayergameUpdateRequest;
import edu.blackjack.services.PlayerService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    
    private final PlayerService playerService;

    // Create a new player with the given name
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Player>> createPlayer(@RequestBody PlayerCreateRequest playerCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(playerCreateRequest));
    }

    // Get a player by its name
    @GetMapping
    public ResponseEntity<Mono<Player>> getPlayer(@RequestBody PlayergameFindRequest playergameFindRequest) {
        return ResponseEntity.ok(playerService.getPlayer(playergameFindRequest));
    }
    
    // Delete a player by its name
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Mono<Void>> deletePlayer(@RequestBody PlayergameDeleteRequest playergameDeleteRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(playerService.deletePlayer(playergameDeleteRequest));
    }

    // Update a player by its name
    @PutMapping
    public ResponseEntity<Mono<Player>> updatePlayer(@RequestBody PlayergameUpdateRequest playergameUpdateRequest) {
        return ResponseEntity.ok(playerService.updatePlayer(playergameUpdateRequest));
    }

    // Get all players
    @GetMapping("/all")
    public ResponseEntity<Flux<Player>> getPlayers() {
        return ResponseEntity.ok(playerService.getPlayers());
    }
    
}
