package org.lahrach;

public class HawkEye implements Observer, Emitter {
    private Referee referee;
    private Player player1;
    private Player player2;

    public HawkEye() {

    }

    public Player decideGameWinner() {
        return null;
    }

    public Player decideSetWinner() {
        return null;
    }

    public Player decideMatchWinner() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void notifyObserver(Score score) {

    }
}
