package org.lahrach;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.lahrach.pattern.Observer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Referee implements Observer<Player> {
    private Set<Player> players;
    private Iterator<Player> iterator;
    private Map<Integer, String> pointsMap;
    private HawkEye hawkEye;
    private boolean matchEnd;

    public Referee() {
        pointsMap = Map.of(
                0, "love",
                1, "fifteen",
                2, "thirty",
                3, "forty");
        players = new LinkedHashSet<>();
        hawkEye = new HawkEye();
    }

    @Override
    public void update(Player player) {
        players.add(player);

        iterator = players.iterator();
        Player player1 = iterator.next();
        Player player2 = iterator.next();

        announceStatement(player1, player2);
    }

    public void announceStatement(Player player1, Player player2) {
        Score player1Score = player1.getScore();
        Score player2Score = player2.getScore();

        hawkEye.setScore1(player1Score);
        hawkEye.setScore2(player2Score);

        Player currentPointWinner = player1Score.getCurrentPoint() > player2Score.getCurrentPoint()
                ? player1
                : player2;
        Player currentGameWinner = player1Score.getGamesWon() > player2Score.getGamesWon()
                ? player1
                : player2;

        if (hawkEye.isPointsDifferent())
            System.out.format("%s, %s%n",
                    pointsMap.get(player1Score.getCurrentPoint()),
                    pointsMap.get(player2Score.getCurrentPoint()));
        if (hawkEye.isPointsTied())
            System.out.format("%s all%n", pointsMap.get(player1Score.getCurrentPoint()));
        if (hawkEye.isDeuce())
            System.out.println("Deuce");
        if (hawkEye.hasAdvantage())
            System.out.format("Advantage: %s%n", currentPointWinner.getName());
        if (hawkEye.hasGameWinner()) {
            System.out.format("Game: %s%n", currentPointWinner.getName());
            currentPointWinner.winsGame();
            player1.getScore().resetPoints();
            player2.getScore().resetPoints();
        }
        if (hawkEye.isInExtraGame())
            System.out.println("Extra Game");
        if (hawkEye.isInTieBreak())
            System.out.println("Tie Break");
        if (hawkEye.hasSetWinner()) {
            System.out.format("Game and Set: %s%n", currentGameWinner.getName());
            matchEnd = true;
        }

    }
}
