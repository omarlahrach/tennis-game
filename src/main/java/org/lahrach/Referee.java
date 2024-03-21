package org.lahrach;

import java.util.Map;

public class Referee implements Observer {
    private Map<Integer, String> statements;

    public Referee() {
        statements = Map.of(
                0, "Love",
                15, "Fifteen",
                30, "Thirty",
                40, "Forty");
    }

    public void announceScore(Score scorePlayer1, Score scorePlayer2) {

    }

    @Override
    public void update() {

    }
}
