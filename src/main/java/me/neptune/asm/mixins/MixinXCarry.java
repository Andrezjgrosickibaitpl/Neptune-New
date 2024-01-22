package me.neptune.asm.mixins;

import me.neptune.impl.module.exploit.xcarry.XCarry;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class MixinXCarry {
    @Mixin(ClientPlayNetworkHandler.class)
    public static class MixinClientPlayNetworkHandler {
        @Inject(method = "sendPacket(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
        public void onSendPacket(Packet<?> packet, CallbackInfo ci) {
            if (packet instanceof CloseHandledScreenC2SPacket && XCarry.XCEnabled) {
                ci.cancel();
            }
        }
    }
}