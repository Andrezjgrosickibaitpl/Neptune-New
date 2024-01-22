package me.neptune.impl.module.movement;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import org.lwjgl.glfw.GLFW;

public class FastFall extends Module {
    public FastFall() {
        super("FastFall", new String[]{"FastFall"}, "Fall quicker", Category.MOVEMENT);
        this.offerValues();
        this.offerListeners();
        this.setBind(GLFW.GLFW_KEY_UNKNOWN);
    }
}
