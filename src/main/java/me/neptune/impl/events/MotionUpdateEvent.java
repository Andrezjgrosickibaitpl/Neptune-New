package me.neptune.impl.events;

import me.neptune.api.event.events.Stage;
import me.neptune.api.event.events.StageEvent;

public class MotionUpdateEvent extends StageEvent {
    public MotionUpdateEvent(Stage stage) {
        super(stage);
    }
}
