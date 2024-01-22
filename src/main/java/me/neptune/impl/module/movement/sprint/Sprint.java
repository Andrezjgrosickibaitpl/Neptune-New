package me.neptune.impl.module.movement.sprint;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.EnumValue;
import me.neptune.api.value.Value;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class Sprint extends Module {

    public final EnumValue<SprintMode> mode = new EnumValue<>(
            new String[]{"Mode", "modes", "back"},
            "Toggle sprint",
            SprintMode.Vanilla
    );

    protected final Value<Boolean> instant = new Value<>(
            new String[]{"Instant", "speed", "back"},
            "Enhance movement",
            false
    );


    public Sprint() {
        super("Sprint", new String[]{"Sprint"}, "Toggle sprint", Category.MOVEMENT);
        this.offerValues(mode);
        this.offerListeners(new ListenerTick(this));
        this.setBind(GLFW_KEY_UNKNOWN);
    }
}