package me.neptune.impl.module.render.customfov;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class CustomFov extends Module {

    protected final NumberValue<Integer> value = new NumberValue<>(
            new String[]{"Fov"},
            "fov value.",
            100, 50, 140, 0
    );
    protected int oldValue;

    public CustomFov() {
        super("CustomFov", new String[]{"CustomFov"}, "Custom fov", Category.RENDER);
        this.offerListeners(new ListenerTick(this));
        this.offerValues(value);
        this.setBind(GLFW_KEY_UNKNOWN);
    }

    @Override
    public void onEnable() {
        oldValue = mc.options.getFov().getValue();
    }

    @Override
    public void onDisable() {
        mc.options.getFov().setValue(oldValue);
    }
}
