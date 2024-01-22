package me.neptune.impl.module.misc.FastXP;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.api.module.Module;
import me.neptune.asm.mixins.IMinecraft;
import me.neptune.impl.events.TickEvent;
import net.minecraft.item.Items;

public class ListenerTick extends ModuleListener<FastXP, TickEvent> {
    // Hook FastXP

    public ListenerTick(FastXP module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        if (Module.nullCheck()) {
            return;
        }
        if(mc.player.getMainHandStack().getItem() == Items.EXPERIENCE_BOTTLE) {
            int timer = Math.min(((IMinecraft)mc).getItemUseCooldown(), FastXP.delay.getValue());
            ((IMinecraft)mc).setItemUseCooldown(timer);
        }
    }
}