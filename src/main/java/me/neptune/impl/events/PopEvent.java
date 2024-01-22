package me.neptune.impl.events;

import me.neptune.api.event.events.Event;
import net.minecraft.entity.player.PlayerEntity;

public class PopEvent extends Event {
    private final PlayerEntity player;

    public PopEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }
}
