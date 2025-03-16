package edu.blackjack.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import edu.blackjack.models.Player;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerRepository extends R2dbcRepository<Player, String> {
    Mono<Player> findByName(String name);
    Mono<Void> deleteByName(String name);
}
