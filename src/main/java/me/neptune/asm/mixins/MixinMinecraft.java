package me.neptune.asm.mixins;

import me.neptune.Neptune;
import me.neptune.impl.events.ScreenEvent;
import me.neptune.impl.events.TickEvent;
import me.neptune.impl.module.other.UnfocusedFPS;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MinecraftClient.class, priority = 1001)
public abstract class MixinMinecraft {

    @Shadow
    public abstract boolean isWindowFocused();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initHook(CallbackInfo info) {
        Neptune.init();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void preTickHook(CallbackInfo info) {
        TickEvent pre = new TickEvent();
        Neptune.getEventBus().dispatch(pre);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void postTickHook(CallbackInfo info) {
        TickEvent post = new TickEvent.Post();
        Neptune.getEventBus().dispatch(post);
    }

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void setScreenHook(Screen screen, CallbackInfo info) {
        ScreenEvent event = new ScreenEvent(screen);
        Neptune.getEventBus().dispatch(event);

        if (event.isCanceled()) {
            info.cancel();
        }
    }

    @Inject(method = "close", at = @At("HEAD"))
    private void closeHook(CallbackInfo info) {
        Neptune.getConfigManager().saveConfig();
        Neptune.getFriendManager().saveFriends();
    }

    @Inject(method = "getFramerateLimit", at = @At("HEAD"), cancellable = true)
    public void getFramerateLimit(CallbackInfoReturnable<Integer> cir) {
        try {
            if (UnfocusedFPS.INSTANCE.isEnabled() && !isWindowFocused()) {
                cir.setReturnValue(UnfocusedFPS.INSTANCE.UnfValue.getValue());
            }
        } catch (Exception var4) {
        }
    }
}
