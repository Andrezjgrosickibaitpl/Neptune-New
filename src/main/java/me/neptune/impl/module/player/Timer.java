package me.neptune.impl.module.player;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class Timer extends Module {
    public static Timer INSTANCE;

    public static final NumberValue<Float> value = new NumberValue<>(
            new String[]{"Timer"},
            "timer value.",
            1.f, 1.f, 10.f, 0.1f
    );

    public Timer() {
        super("Timer", new String[]{"Timer"}, "Module to speed up or slow down the game", Category.PLAYER);
        this.offerValues(value);

        INSTANCE = this;
    }
}
