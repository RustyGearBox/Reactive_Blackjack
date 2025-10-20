package edu.blackjack.models.Request.Game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class GameCreateRequest {
    
    @NotBlank(message = "Game name is required")
    @Size(min = 3, max = 100, message = "Game name must be between 3 and 100 characters")
    private String name;

}
