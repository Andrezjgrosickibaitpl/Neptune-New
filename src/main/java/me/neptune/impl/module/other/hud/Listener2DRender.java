package me.neptune.impl.module.other.hud;

import me.neptune.Neptune;
import me.neptune.api.event.listener.ModuleListener;
import me.neptune.impl.events.Render2DEvent;
import me.neptune.impl.module.other.colours.Colours;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Formatting;

import java.text.DecimalFormat;

public class Listener2DRender extends ModuleListener<HUD, Render2DEvent> {

    public Listener2DRender(HUD module) {
         super(module, Render2DEvent.class);
    }

    @Override
    public void call(Render2DEvent event) {
        if (mc.player == null)
            return;

        if (module.watermark.getValue())
            event.getContext().drawTextWithShadow(mc.textRenderer,
                    Neptune.NAME  + " - " + Neptune.VERSION,
                    2, 2, Colours.get().getColour().getRGB());


        if (module.coords.getValue()) {

            DecimalFormat df = new DecimalFormat("###.#");
            String coords = "XYZ: " + Formatting.WHITE + (df.format(mc.player.getX()) +
                    ", " +
                    df.format(mc.player.getY()) +
                    ", " + df.format(mc.player.getZ())) +
                    ((" (" + (df.format(mc.player.getX() * 8) + ", " + df.format(mc.player.getZ() * 8) + "]")));

            event.getContext().drawTextWithShadow(mc.textRenderer, coords, 2, event.getContext().getScaledWindowHeight() - 10, Colours.get().getColour().getRGB());
        }

        if (module.ping.getValue()) {
            PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
            if (playerListEntry == null) return;

            event.getContext().drawTextWithShadow(mc.textRenderer, "Ping: " + Formatting.GRAY + playerListEntry.getLatency(), 2, event.getContext().getScaledWindowHeight() - 20, Colours.get().getColour().getRGB());
        }
    }
}
