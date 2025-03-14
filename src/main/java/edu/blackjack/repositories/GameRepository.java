package edu.blackjack.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import edu.blackjack.models.Game;
import reactor.core.publisher.Mono;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
    Mono<Game> findByGameId(String id);
}
