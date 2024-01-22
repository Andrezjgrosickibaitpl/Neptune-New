package me.neptune.impl.module.other.hud;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.Value;

public class HUD extends Module {

    protected final Value<Boolean> watermark = new Value<>(
            new String[]{"Watermark", "watermark", "back"},
            "Displays Mod Name",
            true
    );

    protected final Value<Boolean> coords = new Value<>(
            new String[]{"Coordinates", "coordinates", "back"},
            "Shows Coords",
            true
    );

    protected final Value<Boolean> ping = new Value<>(
            new String[]{"Ping", "ping", "back"},
            "Shows Server Ping",
            true
    );

    public HUD() {
        super("HUD", new String[]{"HUD"}, "HUD settings", Category.OTHER);
        this.offerValues(watermark, coords, ping);
        this.offerListeners(new Listener2DRender(this));
    }

}
