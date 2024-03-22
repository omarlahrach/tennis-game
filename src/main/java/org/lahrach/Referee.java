package org.lahrach;

import java.util.Map;
import java.util.logging.Logger;

import org.lahrach.pattern.Observer;
import org.lahrach.pattern.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Referee implements Observer {
    private static final Logger logger = Logger.getLogger(Referee.class.getName());

    private Subject subject;
    public static Map<Integer, String> pointsMap;
    public boolean end;

    public Referee() {
        pointsMap = Map.of(
                0, "love",
                1, "fifteen",
                2, "thirty",
                3, "forty");
    }

    @Override
    public void update(Subject subject) {
        this.subject = subject;
        announceStatement();
    }

    public void announceStatement() {
        HawkEye hawkEye = (HawkEye) this.subject;
        Player player1 = (Player) hawkEye.getSubject1();
        Player player2 = (Player) hawkEye.getSubject2();

        Score player1Score = player1.getScore();
        Score player2Score = player2.getScore();

        Player pointWinner = player1Score.getCurrentPoint() > player2Score.getCurrentPoint()
                ? player1
                : player2;
        Player gameWinner = player1Score.getGamesWon() > player2Score.getGamesWon()
                ? player1
                : player2;

        switch (hawkEye.getCurrentState()) {
            case POINTS_TIED:
                System.out.format("%s all%n", pointsMap.get(player1Score.getCurrentPoint()));
                break;
            case DEUCE:
                System.out.println("Deuce");
                break;
            case ADVANTAGE:
                System.out.format("Advantage: %s%n", pointWinner.getName());
                break;
            case GAME_WINNER:
                System.out.format("Game: %s%n", pointWinner.getName());
                pointWinner.winsGame();
                break;
            case EXTRA_GAME:
                System.out.println("Extra Game");
                break;
            case TIE_BREAK:
                System.out.println("Tie Break");
                break;
            case SET_WINNER:
                System.out.format("Game and Set: %s%n", gameWinner.getName());
                end = true;
                break;
            default:
                System.out.format("%s, %s%n",
                        pointsMap.get(player1Score.getCurrentPoint()),
                        pointsMap.get(player2Score.getCurrentPoint()));
                break;
        }
    }
}
