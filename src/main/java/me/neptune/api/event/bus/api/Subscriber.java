package me.neptune.api.event.bus.api;

import me.neptune.api.event.bus.Listener;

import java.util.Collection;

public interface Subscriber {
    Collection<Listener<?>> getListeners();
}

