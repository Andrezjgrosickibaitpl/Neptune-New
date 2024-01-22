package me.neptune.impl.module.movement.sprint;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.impl.events.TickEvent;

public class ListenerTick extends ModuleListener<Sprint, TickEvent> {

    public ListenerTick(Sprint module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        if (Module.nullCheck())
            return;
        switch (module.mode.getValue()) {
            case Vanilla -> {
                if (mc.options.forwardKey.isPressed()) {
                    assert mc.player != null;
                    mc.player.setSprinting(true);
                }
            }
            case Rage -> {
                assert mc.player != null;
                if (mc.player.getHungerManager().getFoodLevel() <= 6.0F || mc.player == null || mc.player.isSneaking())
                {
                    return;
                }
                mc.player.setSprinting(true);
            }
        }

    }
}
