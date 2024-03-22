package org.lahrach;

import java.util.List;

public class Board {
    private List<String> boardPoints = List.of("0", "15", "30", "40");
    
    public void display(Player player1, Player player2) {
        System.out.println("+----------+------------+----------------+");
        System.out.println("| Player   | Games Won  | Current Point  |");
        System.out.println("+----------+------------+----------------+");

        String player1Row = String.format("| %-8s | %-10d | %-14s |%n",
                player1.getName(),
                player1.getScore().getGamesWon(),
                boardPoints.get(player1.getScore().getCurrentPoint() <= 3 ? player1.getScore().getCurrentPoint() : 3));

        String player2Row = String.format("| %-8s | %-10d | %-14s |%n",
                player2.getName(),
                player2.getScore().getGamesWon(),
                boardPoints.get(player2.getScore().getCurrentPoint() <= 3 ? player2.getScore().getCurrentPoint() : 3));

        System.out.print(player1Row);
        System.out.print(player2Row);
        System.out.println("+----------+------------+----------------+");
    }
}
