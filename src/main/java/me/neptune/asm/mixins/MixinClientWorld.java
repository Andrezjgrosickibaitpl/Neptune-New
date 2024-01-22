package me.neptune.asm.mixins;

import me.neptune.Neptune;
import me.neptune.impl.events.EntityWorldEvent;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld {
    @Shadow
    @Nullable
    public abstract Entity getEntityById(int id);

    @Inject(method = "addEntityPrivate", at = @At("TAIL"))
    private void onAddEntityPrivate(int id, Entity entity, CallbackInfo info) {
        if (entity != null) {
            EntityWorldEvent.Add entityWorldEvent = new EntityWorldEvent.Add(entity);
            Neptune.getEventBus().dispatch(entityWorldEvent);
        }
    }

    @Inject(method = "removeEntity", at = @At("HEAD"))
    private void onRemoveEntity(int entityId, Entity.RemovalReason removalReason, CallbackInfo info) {
        if (getEntityById(entityId) != null) {
            EntityWorldEvent.Remove entityWorldEvent = new EntityWorldEvent.Remove(getEntityById(entityId));
            Neptune.getEventBus().dispatch(entityWorldEvent);
        }
    }
}
