package me.neptune.api.event.bus;

import me.neptune.api.event.bus.api.Subscriber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubscriberImpl implements Subscriber {
    protected final List<Listener<?>> listeners = new ArrayList<>();

    @Override
    public Collection<Listener<?>> getListeners() {
        return listeners;
    }

}