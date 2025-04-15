package edu.blackjack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import edu.blackjack.models.Game;
import edu.blackjack.models.Request.Game.GameCreateRequest;
import edu.blackjack.models.Request.Game.GameDeleteRequest;
import edu.blackjack.models.Request.Game.GameUpdateRequest;
import edu.blackjack.services.GameService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/game")
public class GameController {
 
    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Game>> createGame(@RequestBody GameCreateRequest gameCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.createGame(gameCreateRequest));
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<Mono<Game>> getGameById(@PathVariable("gameId") String gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    @PutMapping
    public ResponseEntity<Mono<Game>> updateGame(@RequestBody GameUpdateRequest gameUpdateRequest) {
        return ResponseEntity.ok(gameService.updateGame(gameUpdateRequest));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Mono<Void>> deleteGame(@RequestBody GameDeleteRequest gameDeleteRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gameService.deleteGame(gameDeleteRequest));
    }
    
}
