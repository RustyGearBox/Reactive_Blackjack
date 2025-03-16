package edu.blackjack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.blackjack.models.Game;
import edu.blackjack.models.Request.Game.GameCreateRequest;
import edu.blackjack.models.Request.Game.GameDeleteRequest;
import edu.blackjack.models.Request.Game.GameFindRequest;
import edu.blackjack.models.Request.Game.GameUpdateRequest;
import edu.blackjack.services.GameService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
 
    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Game> createGame(@RequestBody GameCreateRequest createRequest) {
        return gameService.createGame(createRequest.getPlayerName());
    }
    
    @GetMapping
    public Mono<Game> getGameById(@RequestBody GameFindRequest findRequest) {
        return gameService.getGame(findRequest.getGameId());
    }

    @PutMapping
    public Mono<Game> updateGame(@RequestBody GameUpdateRequest updateRequest) {
        return gameService.updateGame(updateRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGame(@RequestBody GameDeleteRequest deleteRequest) {
        return gameService.deleteGame(deleteRequest.getGameId());
    }
}
