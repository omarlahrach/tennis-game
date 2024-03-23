package org.lahrach;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private static Referee referee;
    private static Player player1;
    private static Player player2;
    private static Board board;
    private static Random random;

    public static void main(String[] args) {
        start();
        update();
    }

    private static void start() {
        player1 = new Player("Sarah");
        player2 = new Player("Bernard");
        referee = new Referee();
        board = new Board();  

        player1.setObserver(referee);
        player2.setObserver(referee);  
      
        Set<Player> players = new LinkedHashSet<>();
        players.add(player1);
        players.add(player2);
        referee.setPlayers(players); 
        
        random = new Random();
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        while (!referee.isMatchEnd()) {
            board.display(player1, player2);

            int winner = random.nextInt(2);

            if (winner == 0) player1.winsPoint();
            else player2.winsPoint();

            sleep(0);
        }
    }
}