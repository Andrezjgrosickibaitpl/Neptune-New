package me.neptune.impl.module.render;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class FullBright extends Module {
    public static FullBright INSTANCE;

    public FullBright() {
        super("FullBright", new String[]{"FullBright"}, "Everyone knows what fullbright is", Category.RENDER);
        this.setBind(GLFW_KEY_UNKNOWN);

        INSTANCE = this;
    }
}
