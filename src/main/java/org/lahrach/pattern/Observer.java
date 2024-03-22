package org.lahrach.pattern;

@FunctionalInterface
public interface Observer {
    void update(Subject subject);
}
