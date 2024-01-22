package me.neptune.impl.module.player.autorespawn;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.impl.events.TickEvent;

public class ListenerTick extends ModuleListener<AutoRespawn, TickEvent> {

    public ListenerTick(AutoRespawn module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        if (Module.nullCheck()) {
            return;
        }
        if (mc.player.isDead()) {
            mc.player.requestRespawn();
            mc.setScreen(null);
        }
    }
}
