package me.neptune.impl.manager;

import me.neptune.api.event.bus.Listener;
import me.neptune.api.event.bus.SubscriberImpl;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.impl.events.TickEvent;

public class TimerManager extends SubscriberImpl implements Minecraftable {

    private float timer = 1.0f;

    public TimerManager() {
        this.listeners.add(new Listener<>(TickEvent.class) {
            @Override
            public void call(TickEvent event) {
            }
        });
    }

    public void set(float timer) {
        this.timer = timer <= 0.0f ? 0.1f : timer;
    }

    public float getTimer() {
        return timer;
    }

    public void reset() {
        this.timer = 1.0f;
    }

}
