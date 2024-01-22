package me.neptune.asm.mixins;

import me.neptune.Neptune;
import me.neptune.asm.ducks.IClientPlayerInteractionManager;
import me.neptune.impl.events.AttackEntityEvent;
import me.neptune.impl.events.DamageBlockEvent;
import me.neptune.impl.module.combat.Reach;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class MixinClientPlayerInteractionManager implements IClientPlayerInteractionManager {

    @Shadow
    protected abstract void syncSelectedSlot();

    @Shadow
    public abstract void sendSequencedPacket(ClientWorld world, SequencedPacketCreator packetCreator);

    @Inject(method = "attackEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void onAttackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        AttackEntityEvent event = new AttackEntityEvent();
        Neptune.getEventBus().dispatch(event);
    }

    @Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
    private void onAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> info) {
        DamageBlockEvent event = new DamageBlockEvent(pos, direction);
        Neptune.getEventBus().dispatch(event);
        if (event.isCanceled()) {
            info.cancel();
        }
    }

    @Override
    public void syncItem() {
        syncSelectedSlot();
    }

    @Override
    public void sendPacketWithSequence(ClientWorld world, SequencedPacketCreator packetCreator) {
        sendSequencedPacket(world, packetCreator);
    }

    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    public void getReachDistance(CallbackInfoReturnable<Float> cir) {
        if (Reach.INSTANCE.isEnabled()) {
            cir.setReturnValue((Reach.INSTANCE.getReach()));
        }
    }


    @Inject(method = "hasExtendedReach", at = @At("HEAD"), cancellable = true)
    public void hasExtendedReach(CallbackInfoReturnable<Boolean> cir) {
        if (Reach.INSTANCE.isEnabled()) {
            cir.setReturnValue(true);
        }
    }
}
