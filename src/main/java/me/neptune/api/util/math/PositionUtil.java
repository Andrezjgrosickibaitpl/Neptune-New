package me.neptune.api.util.math;

import me.neptune.api.interfaces.Minecraftable;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class PositionUtil implements Minecraftable {

    public static BlockPos getPosition(Entity entity) {
        return getPosition(entity, 0.0);
    }

    public static BlockPos getPosition(Entity entity, double yOffset) {
        double y = entity.getY() + yOffset;
        if (entity.getY() - Math.floor(entity.getY()) > 0.5) {
            y = Math.ceil(entity.getY());
        }

        return new BlockPos((int) entity.getX(), (int) y, (int) entity.getZ());
    }
}
