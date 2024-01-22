package me.neptune.impl.module.other.clickgui;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.impl.events.TickEvent;
import me.neptune.impl.gui.NeptuneGui;

public class ListenerTick extends ModuleListener<ClickGUI, TickEvent> {
    public ListenerTick(ClickGUI module) {
        super(module, TickEvent.class);
    }

    @Override
    public void call(TickEvent event) {
        if (module.NEPTUNE_GUI != null) {
            mc.setScreen(module.NEPTUNE_GUI);
            module.NEPTUNE_GUI = null;
            return;
        }

        if (!(mc.currentScreen instanceof NeptuneGui)) {
            module.disable();
        }
    }
}
