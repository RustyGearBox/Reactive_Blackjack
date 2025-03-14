package edu.blackjack.enums;

public enum Figures {
    HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣"), SPADES("♠");

    private final String figure;

    Figures(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }
}
