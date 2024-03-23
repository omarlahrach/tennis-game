package org.lahrach;

import org.lahrach.pattern.Observer;
import org.lahrach.pattern.Subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Player implements Subject<Player> {
    private final String name;
    private Score score;
    private Observer<Player> observer;

    public Player(String name) {
        this.name = name;
        score = new Score(0, 0);
    }

    public void winsPoint() {
        score.incrementCurrentPoint();
        this.notifyObserver();
    }

    public void winsGame() {
        score.incrementGamesWon();
    }

    @Override
    public void setObserver(Observer<Player> observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }
}
