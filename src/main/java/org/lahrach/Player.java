package org.lahrach;

import org.lahrach.pattern.Observer;
import org.lahrach.pattern.Subject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Player implements Subject {
    private final String name;
    private Score score = new Score(0, 0);

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Observer observer;

    public void winsPoint() {
        score.incrementCurrentPoint();
        this.notifyObserver();
    }

    public void winsGame() {
        score.incrementGamesWon();
        score.resetPoints();
        this.notifyObserver();
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }
}
