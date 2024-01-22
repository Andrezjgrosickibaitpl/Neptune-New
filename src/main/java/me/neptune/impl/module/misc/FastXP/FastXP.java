package me.neptune.impl.module.misc.FastXP;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;
import org.lwjgl.glfw.GLFW;

public class FastXP extends Module {
    public static final NumberValue<Integer> delay = new NumberValue<>(
            new String[]{"Delay"},
            "delay throw",
            0, 0, 6, 0
    );
    public FastXP() {
        super("FastXP", new String[]{"FastXP"}, "Throws XP without delay", Category.MISC);
        this.offerValues(delay);
        this.offerListeners(new ListenerTick(this));
        this.setBind(GLFW.GLFW_KEY_UNKNOWN);
    }
}
