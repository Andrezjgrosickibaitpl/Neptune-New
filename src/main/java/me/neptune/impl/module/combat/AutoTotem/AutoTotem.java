package me.neptune.impl.module.combat.AutoTotem;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class AutoTotem extends Module {

    public final NumberValue<Float> health = new NumberValue<>(
            new String[]{"Health", "health"},
            "Player HP",
            18f, 5.0f, 23.0f, 0.1f
    );

    public AutoTotem() {
        super("AutoTotem", new String[]{"AutoTotem"}, "testmodule", Category.COMBAT);
        this.offerListeners(new ListenerTick(this));
        this.offerValues(health);
        this.setBind(GLFW_KEY_UNKNOWN);
    }
}