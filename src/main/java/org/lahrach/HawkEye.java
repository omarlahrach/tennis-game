package org.lahrach;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HawkEye {
    private Score score1;
    private Score score2;

    public boolean isPointsDifferent() {
        return (score1.getCurrentPoint() <= 3 && score2.getCurrentPoint() <= 3)
                && (score1.getCurrentPoint() != score2.getCurrentPoint());
    }

    public boolean isPointsTied() {
        return (score1.getCurrentPoint() < 3 && score2.getCurrentPoint() < 3)
                && (score1.getCurrentPoint() == score2.getCurrentPoint());
    }

    public boolean isDeuce() {
        return score1.getCurrentPoint() >= 3 && score2.getCurrentPoint() >= 3
                && score1.getCurrentPoint() == score2.getCurrentPoint();
    }

    public boolean hasAdvantage() {
        return (score1.getCurrentPoint() >= 3 && score2.getCurrentPoint() >= 3)
                && (score1.getCurrentPoint() == score2.getCurrentPoint() + 1
                        || score2.getCurrentPoint() == score1.getCurrentPoint() + 1);
    }

    public boolean hasGameWinner() {
        return (score1.getCurrentPoint() > 3 || score2.getCurrentPoint() > 3)
                && Math.abs(score1.getCurrentPoint() - score2.getCurrentPoint()) >= 2;
    }

    public boolean isInExtraGame() {
        return (score1.getGamesWon() == 5 && score2.getGamesWon() == 6)
                || (score1.getGamesWon() == 6 && score2.getGamesWon() == 5);
    }

    public boolean isInTieBreak() {
        return score1.getGamesWon() == 6 && score2.getGamesWon() == 6;
    }

    public boolean hasSetWinner() {
        return (score1.getGamesWon() >= 6 || score2.getGamesWon() >= 6)
                && Math.abs(score1.getGamesWon() - score2.getGamesWon()) >= 1
                && !isInExtraGame();
    }
}