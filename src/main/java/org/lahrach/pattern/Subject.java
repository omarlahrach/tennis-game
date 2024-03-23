package org.lahrach.pattern;

public interface Subject<T> {
    void setObserver(Observer<T> observer);
    void notifyObserver();
}
