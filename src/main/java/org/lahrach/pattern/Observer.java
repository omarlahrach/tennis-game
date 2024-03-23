package org.lahrach.pattern;

@FunctionalInterface
public interface Observer<T> {
    void update(T data);
}
