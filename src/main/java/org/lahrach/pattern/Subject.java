package org.lahrach.pattern;

public interface Subject {
    void setObserver(Observer observer);
    void notifyObserver();
}
