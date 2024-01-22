package me.neptune.impl.events;

import me.neptune.api.event.events.Event;

public class MouseEvent extends Event {
    private final int key;

    public MouseEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
