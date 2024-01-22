package me.neptune.impl.module.movement.step;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.util.math.StopWatch;
import me.neptune.api.value.EnumValue;
import me.neptune.api.value.NumberValue;
import me.neptune.impl.module.movement.step.mode.StepMode;
import org.lwjgl.glfw.GLFW;

public class Step extends Module {

    protected final EnumValue<StepMode> mode = new EnumValue<>(
            new String[]{"Mode", "Type", "Method"},
            "Lets you walk onto blocks like stairs",
            StepMode.VANILLA
    );

    public final NumberValue<Float> stepValue = new NumberValue<>(
            new String[]{"Height"},
            "Max height",
            1.0f, 1.0f, 3.0f, 0.1f
    );

    protected final StopWatch timer = new StopWatch();

    public Step() {
        super("Step", new String[]{"Step"}, "Lets you walk onto blocks like stairs", Category.MOVEMENT);
        this.offerValues(stepValue);
        this.offerListeners(new ListenerTick(this));
        this.setBind(GLFW.GLFW_KEY_UNKNOWN);
    }

    @Override
    public void onDisable() {
        if (mc.player == null) {
            return;
        }
        mc.player.setStepHeight(0.6f);
    }

    public double[] getOffset(double height) {
        // enchantment tables
        if (height == 0.75) {
            return new double[] {
                    0.42, 0.753, 0.75
            };
        }
        // end portal frames
        else if (height == 0.8125) {
            return new double[] {
                    0.39, 0.7, 0.8125
            };
        }
        // chests
        else if (height == 0.875) {
            return new double[] {
                    0.39, 0.7, 0.875
            };
        }
        else if (height == 1) {
            return new double[] {
                    0.42, 0.753, 1
            };
        }
        else if (height == 1.5) {
            return new double[] {
                    0.42, 0.75, 1.0, 1.16, 1.23, 1.2
            };
        }
        else if (height == 2) {
            return new double[] {
                    0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43
            };
        }
        else if (height == 2.5) {
            return new double[] {
                    0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907
            };
        }
        return null;
    }
}
