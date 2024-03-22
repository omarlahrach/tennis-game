package org.lahrach;

import org.lahrach.pattern.Observer;
import org.lahrach.pattern.Subject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HawkEye implements Subject, Observer {
    private Subject subject1;
    private Subject subject2;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Observer observer;

    public static enum State {
        NONE, POINTS_TIED, DEUCE, ADVANTAGE, GAME_WINNER, EXTRA_GAME, TIE_BREAK, SET_WINNER
    };

    private State currentState = State.NONE;

    public HawkEye() {
        this.subject1 = new Player();
        this.subject2 = new Player();
    }

    @Override
    public void update(Subject player) {
        int player1Point = ((Player) subject1).getScore().getCurrentPoint();
        int player2Point = ((Player) subject2).getScore().getCurrentPoint();
        int player1GamesWon = ((Player) subject1).getScore().getGamesWon();
        int player2GamesWon = ((Player) subject2).getScore().getGamesWon();
        
        if (isPointsTied(player1Point, player2Point)) currentState = State.POINTS_TIED;
        if (isDeuce(player1Point, player2Point)) currentState = State.DEUCE;
        if (hasAdvantage(player1Point, player2Point)) currentState = State.ADVANTAGE;
        if (hasGameWinner(player1Point, player2Point)) currentState = State.GAME_WINNER;
        if (isInExtraGame(player1GamesWon, player2GamesWon)) currentState = State.EXTRA_GAME;
        if (isInTieBreak(player1GamesWon, player2GamesWon)) currentState = State.TIE_BREAK;
        if (hasSetWinner(player1GamesWon, player2GamesWon)) currentState = State.SET_WINNER;

        notifyObserver();
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        this.observer.update(this);
    }
    
    boolean isPointsTied(int player1Point, int player2Point) {
        return (player1Point < 3 && player2Point < 3) && (player1Point == player2Point);
    }

    boolean isDeuce(int player1Point, int player2Point) {
        return player1Point >= 3 && player2Point >= 3 && player1Point == player2Point;
    }

    boolean hasAdvantage(int player1Point, int player2Point) {
        return (player1Point >= 3 && player2Point >= 3) && (player1Point > player2Point || player2Point > player1Point);
    }

    boolean hasGameWinner(int player1Point, int player2Point) {
        return (player1Point > 3 || player2Point > 3) && Math.abs(player1Point - player2Point) >= 2;
    }

    boolean isInExtraGame(int player1GamesWon, int player2GamesWon) {
        return (player1GamesWon == 5 && player2GamesWon == 6) || (player1GamesWon == 6 && player2GamesWon == 5);
    }

    boolean isInTieBreak(int player1GamesWon, int player2GamesWon) {
        return player1GamesWon == 6 && player2GamesWon == 6;
    }

    boolean hasSetWinner(int player1GamesWon, int player2GamesWon) {
        return (player1GamesWon >= 6 || player2GamesWon >= 6) && Math.abs(player1GamesWon - player2GamesWon) >= 1;
    }
}