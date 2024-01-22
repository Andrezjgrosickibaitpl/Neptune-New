package me.neptune.impl.utils;

import me.neptune.api.interfaces.Minecraftable;
import net.minecraft.util.math.MathHelper;

public class MovementUtils implements Minecraftable {
    public static double SPRINTING_SPEED = 0.2873;

    public static boolean isMoving() {
        return mc.player.input.movementForward != 0f || mc.player.input.movementSideways != 0f;
    }

    public static double[] strafe(double speed) {
        float forward = /*Math.signum*/(mc.player.input.movementForward);
        float sideways = /*Math.signum*/(mc.player.input.movementSideways);
        float yaw = mc.player.getYaw() + 90f;

        if(forward != 0f) {
            if(forward > 0f) {
                yaw += sideways * -45f;
            } else {
                yaw += sideways * 45f;
            }

            sideways = 0f;
        }

        yaw *= MathUtils.RAD;

        return new double[] {
                forward * speed * MathHelper.cos(yaw) + sideways * speed * MathHelper.sin(yaw),
                forward * speed * MathHelper.sin(yaw) - sideways * speed * MathHelper.cos(yaw)
        };
    }
}
