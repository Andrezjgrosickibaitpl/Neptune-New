package me.neptune.asm.mixins;

import me.neptune.Neptune;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.asm.ducks.ILivingEntity;
import me.neptune.impl.events.DeathEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements ILivingEntity, Minecraftable {

    @Shadow
    @Final
    private static TrackedData<Float> HEALTH;

    @Inject(method = "onTrackedDataSet", at = @At("RETURN"))
    public void onTrackedDataSet(TrackedData<?> key, CallbackInfo info) {
        if (key.equals(HEALTH) && mc.world != null && mc.world.isClient()) {
            DeathEvent deathEvent = new DeathEvent(LivingEntity.class.cast(this));
            Neptune.getEventBus().dispatch(deathEvent);
        }
    }
}
