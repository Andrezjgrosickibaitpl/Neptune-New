package me.neptune.asm.mixins;

import me.neptune.Neptune;
import me.neptune.api.util.keyboard.KeyPressAction;
import me.neptune.impl.events.KeyPressEvent;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
        if (key != GLFW.GLFW_KEY_UNKNOWN) {
            KeyPressEvent keyPressEvent = new KeyPressEvent(key, KeyPressAction.get(action));
            Neptune.getEventBus().dispatch(keyPressEvent);
            if (keyPressEvent.isCanceled()) info.cancel();
        }
    }
}
