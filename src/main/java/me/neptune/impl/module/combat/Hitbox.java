package me.neptune.impl.module.combat;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;

public class Hitbox extends Module {
    public static Hitbox INSTANCE;

    public  final NumberValue<Float> size = new NumberValue<>(
            new String[]{"Size", "size"},
            "Hitbox size",
            0.1f, 0.1f, 7.0f, 0.1f
    );

    public Hitbox() {
        super("Hitbox", new String[]{"Hitbox"}, "Enlarges hitboxes", Category.COMBAT);
        this.offerValues(size);

        INSTANCE = this;
    }
}
