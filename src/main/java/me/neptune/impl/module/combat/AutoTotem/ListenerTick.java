package me.neptune.impl.module.combat.AutoTotem;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.api.util.player.InvUtils;
import me.neptune.impl.events.TickEvent;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class ListenerTick extends ModuleListener<AutoTotem, TickEvent> {
    // 酒酒 basic

    public ListenerTick(AutoTotem module) {
        super(module, TickEvent.class);
    }

    int totem;

    @Override
    public void call(TickEvent event) {
        if (Module.nullCheck()) return;

        if ((totem = InvUtils.findInventory(Items.TOTEM_OF_UNDYING)) != -1) {
            if (totem < 9) {
                totem += 36;
            }
            if (mc.player.getHealth() < module.health.getValue()) {
                if (!mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) {
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, totem, 0, SlotActionType.PICKUP, mc.player);
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                }
            }
        }
    }
}
