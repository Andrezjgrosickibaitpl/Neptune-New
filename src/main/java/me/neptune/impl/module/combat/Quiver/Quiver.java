package me.neptune.impl.module.combat.Quiver;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class Quiver extends Module {

    public Quiver() {
        super("Quiver", new String[]{"Quiver"}, " 弓持ったら上向く ", Category.COMBAT);
        this.offerListeners(new ListenerTick(this));
        this.setBind(GLFW_KEY_UNKNOWN);
    }
}
