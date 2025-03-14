package edu.blackjack.models;

import edu.blackjack.enums.Figures;
import edu.blackjack.enums.Values;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Card {
    
    private final Figures figure;
    private final Values value;

}