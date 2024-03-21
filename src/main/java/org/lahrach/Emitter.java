package org.lahrach;

public interface Emitter {
    void addObserver(Observer observer);

    void notifyObserver(Score score);
}
