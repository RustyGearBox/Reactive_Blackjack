package edu.blackjack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.blackjack.models.Game;
import edu.blackjack.models.Request.Game.CreateRequest;
import edu.blackjack.models.Request.Game.DeleteRequest;
import edu.blackjack.models.Request.Game.FindRequest;
import edu.blackjack.models.Request.Game.PlayRequest;
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
    public Mono<Game> createGame(@RequestBody CreateRequest createRequest) {
        return gameService.createGame(createRequest.getPlayerName());
    }
    
    @GetMapping
    public Mono<Game> getGameById(@RequestBody FindRequest findRequest) {
        return gameService.getGame(findRequest.getGameId());
    }

    @PutMapping
    public Mono<Game> updateGame(@RequestBody PlayRequest playRequest) {
        return gameService.updateGame(playRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGame(@RequestBody DeleteRequest deleteRequest) {
        return gameService.deleteGame(deleteRequest.getGameId());
    }
}
