package edu.blackjack.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import edu.blackjack.models.Game;
import reactor.core.publisher.Mono;

@Repository
public interface GameRepository extends R2dbcRepository<Game, String> {
    Mono<Game> findByGameId(String id);
}
