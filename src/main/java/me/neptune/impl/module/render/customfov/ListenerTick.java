package me.neptune.impl.module.render.customfov;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.impl.events.TickEvent;

public class ListenerTick extends ModuleListener<CustomFov, TickEvent> {

    public ListenerTick(CustomFov module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        mc.options.getFov().setValue(module.value.getValue());
    }
}