package me.neptune.asm.mixins;

import me.neptune.Neptune;
import me.neptune.impl.events.MouseEvent;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MixinMouse {

    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouseButtonHook(long window, int button, int action, int mods, CallbackInfo info) {
        MouseEvent event = new MouseEvent(button);
        Neptune.getEventBus().dispatch(event);
    }
}
