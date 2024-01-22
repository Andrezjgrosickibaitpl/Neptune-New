package me.neptune.impl.module.other;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;

public class UnfocusedFPS extends Module {
    public static UnfocusedFPS INSTANCE;

    public final NumberValue<Integer> UnfValue = new NumberValue<>(
            new String[]{"Fps"},
            "uhm",
            5, 1, 30, 0
    );

    public UnfocusedFPS() {
        super("UnfocusedFPS", new String[]{"UnfocusedFPS"}, "Decreases your framerate when minecraft isn't in focus", Category.OTHER);

        INSTANCE = this;
    }
}
