package org.lahrach;

import lombok.Data;

@Data
public class Score {
    private int currentScore;
    private int gamesWon;

    public void incrementCurrentScore() {
        currentScore++;
    }

    public void decrementCurrentScore() {
        currentScore--;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public void decrementGamesWon() {
        gamesWon--;
    }
}
