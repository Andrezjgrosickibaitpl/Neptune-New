package me.neptune.impl.module.combat.Quiver;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.api.util.player.InvUtils;
import me.neptune.impl.events.TickEvent;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;

public class ListenerTick extends ModuleListener<Quiver, TickEvent> {
    // コードに文句言ったら殺すよ。 basic autoshiootbow

    public ListenerTick(Quiver module) {
        super(module, TickEvent.class);
    }
    int arrow;

    @Override
    public void call(TickEvent event) {
        if (Module.nullCheck()) return;

        if ((arrow = InvUtils.findInventory(Items.TIPPED_ARROW)) == -1) return;

        if (mc.player.getMainHandStack().getItem() == Items.BOW) {
            if (mc.player.getActiveHand() == Hand.MAIN_HAND) {
                Module.send(new PlayerMoveC2SPacket.LookAndOnGround(mc.player.getYaw(), -90, mc.player.isOnGround()));
            }
        }
    }
}
