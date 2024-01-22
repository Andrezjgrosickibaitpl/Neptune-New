package me.neptune.impl.module.movement.speed;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.impl.events.TickEvent;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class ListenerTick extends ModuleListener<Speed, TickEvent> {

    public ListenerTick(Speed module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        if(mc.player == null || mc.world == null) {
            return;
        }

        double diffX = mc.player.getX() - mc.player.prevX;
        double diffZ = mc.player.getZ() - mc.player.prevZ;

        module.distance = Math.sqrt(diffX * diffX + diffZ * diffZ);
    }
}