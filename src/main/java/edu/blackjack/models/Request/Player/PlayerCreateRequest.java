package edu.blackjack.models.Request.Player;

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
public class PlayerCreateRequest {

    @NotBlank(message = "Player name is required")
    @Size(min = 2, max = 50, message = "Player name must be between 2 and 50 characters")
    private String name;

}
