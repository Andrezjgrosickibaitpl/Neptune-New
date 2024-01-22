package me.neptune.impl.module.player.autorespawn;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;

public class AutoRespawn extends Module {

    public AutoRespawn() {
        super("AutoRespawn", new String[]{"AutoRespawn"}, "Automatically respawn after dying", Category.MOVEMENT);
        this.offerListeners(new ListenerTick(this));
    }
}
