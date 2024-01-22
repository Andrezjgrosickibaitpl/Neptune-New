package me.neptune.impl.module.player;

import com.google.common.eventbus.Subscribe;
import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.asm.mixins.IExplosionS2CPacket;
import me.neptune.impl.events.PacketEvent;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import org.lwjgl.glfw.GLFW;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", new String[]{"Velocity"}, "Reduce knockback", Category.MOVEMENT);
    }

    @Subscribe
    public void PacketEventReceive(PacketEvent.Receive<?> event) {
        Packet<?> packet = event.getPacket();

        if (packet instanceof ExplosionS2CPacket) {
            event.setCanceled(true);
        }
    }
}
