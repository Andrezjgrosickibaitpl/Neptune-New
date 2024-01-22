package me.neptune.impl.events;

import me.neptune.api.event.events.Event;
import net.minecraft.block.entity.BlockEntity;

public class RenderBlockEntityEvent extends Event {
    private final BlockEntity blockEntity;

    public RenderBlockEntityEvent(BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public BlockEntity getEntity() {
        return blockEntity;
    }
}
