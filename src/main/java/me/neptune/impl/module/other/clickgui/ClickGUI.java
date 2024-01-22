package me.neptune.impl.module.other.clickgui;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.ColorValue;
import me.neptune.api.value.NumberValue;
import me.neptune.api.value.Value;
import me.neptune.impl.gui.NeptuneGui;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;

public class ClickGUI extends Module {

    private final ColorValue color = new ColorValue(
            new String[]{"Color", "colour", "clolor", "colr"},
            new Color(255, 0, 0, 179),
            true
    );

    private final NumberValue<Float> scale = new NumberValue<>(
            new String[]{"Scale", "SIZE"},
            "yoppp",
            1.0f, 0.0f, 3.0f, 0.1f
    );

    private final Value<Boolean> background = new Value<>(
            new String[]{"Background", "darkness", "back"},
            "Draws gui background",
            true
    );

    private final Value<Boolean> descriptionBackground = new Value<>(
            new String[]{"DescBackground", "DescriptionBackground", "DescBG"},
            "Draws a background on descriptions",
            true
    );

    private final Value<Boolean> periods = new Value<>(
            new String[]{"Periods", ".", "period"},
            "Puts periods at the end of descriptions",
            true
    );

    private static ClickGUI CLICK_GUI;

    protected NeptuneGui NEPTUNE_GUI = null;

    public ClickGUI() {
        super("ClickGUI", new String[]{"ClickGUI", "Gui"}, "Interface of the client", Category.OTHER);
        this.offerValues(color, background, descriptionBackground, periods);
        this.offerListeners(new ListenerTick(this));
        this.setBind(GLFW_KEY_P);
        CLICK_GUI = this;
    }

    @Override
    public void onEnable() {
        NEPTUNE_GUI = new NeptuneGui();
    }

    @Override
    public void onDisable() {
        if (mc.currentScreen instanceof NeptuneGui) {
            mc.setScreen(null);
        }
    }

    public static ClickGUI get() {
        return CLICK_GUI;
    }

    public Color getColor() {
        return color.getColor();
    }

    public Color getColorOff() {
        return new Color(color.getColor().getRed(), color.getColor().getGreen(), color.getColor().getBlue(), 65);
    }

    public Boolean background() {
        return background.getValue();
    }

    public Boolean descriptionBackground() {
        return descriptionBackground.getValue();
    }

    public String period() {
        return periods.getValue() ? "." : "";
    }
}
