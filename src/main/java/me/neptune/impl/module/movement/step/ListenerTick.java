package me.neptune.impl.module.movement.step;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.impl.events.TickEvent;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class ListenerTick extends ModuleListener<Step, TickEvent> {

    public ListenerTick(Step module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        if (Module.nullCheck()) {
            return;
        }
        if (module.timer.passed(200)) {
            switch (module.mode.getValue()) {
                case VANILLA -> mc.player.setStepHeight(module.stepValue.getValue());
                case NORMAL -> {
                    double stepHeight = mc.player.getY() - mc.player.prevY;
                    double[] offsets = module.getOffset(stepHeight);

                    if (offsets != null && offsets.length > 1) {
                        for (double offset : offsets) {
                            Module.send(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() + offset, mc.player.getZ(), false));
                        }
                    }
                }
            }
        }
        else {
            mc.player.setStepHeight(0.6f);
        }
    }
}