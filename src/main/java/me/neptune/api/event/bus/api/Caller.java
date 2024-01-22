package me.neptune.api.event.bus.api;

public interface Caller<E> {
    void call(E event);
}
