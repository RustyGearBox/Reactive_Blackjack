package edu.blackjack.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import edu.blackjack.models.Game;
import reactor.core.publisher.Mono;

@Repository
public interface GameRepository extends ReactiveMongoRepository<Game, String> {     
        Mono<Game> findByGameId(String gameId);      
}
