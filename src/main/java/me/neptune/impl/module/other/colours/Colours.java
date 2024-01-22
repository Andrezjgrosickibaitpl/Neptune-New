package me.neptune.impl.module.other.colours;

import me.neptune.api.module.Category;
import me.neptune.api.module.PersistentModule;
import me.neptune.api.util.color.ColorUtil;
import me.neptune.api.value.NumberValue;
import me.neptune.impl.module.other.colours.util.HSLColor;

import java.awt.*;

public class Colours extends PersistentModule {

    public final NumberValue<Float> hue = new NumberValue<>(
            new String[]{"Hue", "h"},
            "Hue of the global colours",
            0.0f, 0.0f, 360.0f, 3.0f
    );

    public final NumberValue<Float> saturation = new NumberValue<>(
            new String[]{"Saturation", "sat", "satur"},
            "Saturation of the global colours",
            100.0f, 0.0f, 100.0f, 1.0f
    );

    public final NumberValue<Float> lightness = new NumberValue<>(
            new String[]{"Lightness", "light", "l", "brightness"},
            "Saturation of the global colours",
            45.0f, 0.0f, 100.0f, 1.0f
    );

    private static Colours COLOURS;

    public Colours() {
        super("Colours", new String[]{"Colours", "Color", "Colors"}, "Changes the clients colors", Category.OTHER);
        this.offerValues(hue, saturation, lightness);
        COLOURS = this;
    }

    public static Colours get() {
        return COLOURS;
    }

    public int color() {
        return getColour().getRGB();
    }

    public Color getColour() {
        return HSLColor.toRGB(hue.getValue(), saturation.getValue(), lightness.getValue());
    }

    public Color getColourCustomAlpha(int alpha) {
        return ColorUtil.changeAlpha(getColour(), alpha);
    }
}
