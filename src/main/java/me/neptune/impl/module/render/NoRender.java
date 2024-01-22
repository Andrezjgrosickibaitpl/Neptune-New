package me.neptune.impl.module.render;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.Value;
import org.lwjgl.glfw.GLFW;

public class NoRender extends Module {
    public static NoRender INSTANCE;

    public final Value<Boolean> explosion = new Value<>(
            new String[]{"Explosion"},
            "when explosion",
            true
    );
    public final Value<Boolean> fireOverlay = new Value<>(
            new String[]{"FireOverlay"},
            "when fire",
            true
    );
    public final Value<Boolean> nausea = new Value<>(
            new String[]{"Nausea"},
            "when u get nausea effect",
            true
    );
    public final Value<Boolean> darkness = new Value<>(
            new String[]{"Darkness"},
            "Removes the darkness effect",
            true
    );

    public NoRender() {
        super("NoRender", new String[]{"NoRender"}, "Prevent effects and particles from rendering", Category.RENDER);
        this.offerValues(explosion, fireOverlay, nausea, darkness);
        this.offerListeners();
        this.setBind(GLFW.GLFW_KEY_UNKNOWN);

        INSTANCE = this;
    }
}
