package org.lahrach;

import java.util.Set;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Player implements Emitter {
    private final String name;
    private final Score score;
    private Set<Observer> observers;

    public void winsPoint() {
        score.incrementCurrentScore();
    }

    public void losesPoint() {
        score.decrementCurrentScore();
    }

    public void winsGame() {
        score.incrementGamesWon();
    }

    public void losesGame() {
        score.decrementGamesWon();
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void notifyObserver(Score score) {

    }
}
