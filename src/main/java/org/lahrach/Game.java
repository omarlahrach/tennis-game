package org.lahrach;

import java.util.Random;

public class Game {
    private static Referee referee;
    private static HawkEye hawkEye;
    private static Player player1;
    private static Player player2;
    private static Board board;

    public static void main(String[] args) {
        start();
        update();
    }

    private static void start() {
        board = new Board();

        referee = new Referee();
        hawkEye = new HawkEye();
        player1 = new Player("Sarah");
        player2 = new Player("Bernard");

        referee.setSubject(hawkEye);

        hawkEye.setObserver(referee);
        hawkEye.setSubject1(player1);
        hawkEye.setSubject2(player2);

        player1.setObserver(hawkEye);
        player2.setObserver(hawkEye);         
    }

    private static void update() {
        Random random = new Random();
        while (!referee.end) {
            int winner = random.nextInt(2);
            if (winner == 0) player1.winsPoint();
            else player2.winsPoint();
            board.display(player1, player2);
        }
    }
}