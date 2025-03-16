package edu.blackjack.models.Request.Game;

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
public class PlayRequest {
    
    private String gameId;
    private PlayType playType;

}
