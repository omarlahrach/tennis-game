package org.lahrach;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Score {
    private int currentPoint;
    private int gamesWon;

    public void incrementCurrentPoint() {
        currentPoint++;
    }

    public void decrementCurrentPoint() {
        currentPoint--;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public void decrementGamesWon() {
        gamesWon--;
    }

    public void resetPoints() {
        currentPoint = 0;
    }
}
