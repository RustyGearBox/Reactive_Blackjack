package edu.blackjack.models.Request.Game;

import edu.blackjack.enums.GameState;
import edu.blackjack.enums.PlayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameUpdateRequest {
    
    private String gameId;
    private PlayType playType;
    private GameState state;

}
