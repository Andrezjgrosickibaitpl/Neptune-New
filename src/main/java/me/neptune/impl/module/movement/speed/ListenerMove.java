package me.neptune.impl.module.movement.speed;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.impl.events.MoveEvent;
import me.neptune.impl.utils.MovementUtils;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class ListenerMove extends ModuleListener<Speed, MoveEvent> {
    private static final double BUNNY_DIV_FRICTION = 159.0;
    private static final double BUNNY_FRICTION = 0.01;
    private static final double BUNNY_SLOPE = 0.66;
    private static final double JUMP_HEIGHT = 0.4;
    private static final double CC_JUMP_HEIGHT = 0.4000000059604645;
    private static final double STAGE2_SPEED = 1.395;
    private static final double BOOST_STAGE2_SPEED = 1.6835;
    private static final double CC_STRAGE2_SPEED = 1.535;


    public ListenerMove(Speed module) {
        super(module, MoveEvent.class);
    }

    @Override
    public void call(MoveEvent event) {
        if(event.getType() != MovementType.SELF) {
            return;
        }

        if(mc.player.fallDistance <= 5.0 && MovementUtils.isMoving()) {
            double velocityY = mc.player.getVelocity().y;

            if(module.stage == 1) {
                module.speed *= 1.35 * MovementUtils.SPRINTING_SPEED - BUNNY_FRICTION;
            } else if(module.stage == 2 && mc.player.verticalCollision) {
                if(module.cc.getValue()) {
                    velocityY = CC_JUMP_HEIGHT;
                    module.speed *= CC_STRAGE2_SPEED;
                } else {
                    velocityY = JUMP_HEIGHT;

                    if(module.boost) {
                        module.speed *= BOOST_STAGE2_SPEED;
                    } else {
                        module.speed *= STAGE2_SPEED;
                    }
                }

                module.boost = !module.boost;
            } else if(module.stage == 3) {
                module.speed = module.distance - BUNNY_SLOPE * (module.distance - MovementUtils.SPRINTING_SPEED);
            } else {
                if(mc.player.verticalCollision || mc.world.getCollisions(mc.player, mc.player.getBoundingBox().offset(0.0, velocityY, 0.0)).iterator().hasNext()) {
                    module.stage = 1;
                }

                module.speed = module.distance - module.distance / BUNNY_DIV_FRICTION;
            }

            module.speed = Math.max(module.speed, MovementUtils.SPRINTING_SPEED);
            module.stage++;

            mc.player.setVelocity(0.0, velocityY, 0.0);

            double[] motions = MovementUtils.strafe(MovementUtils.SPRINTING_SPEED);

            event.setVec(new Vec3d(motions[0], velocityY, motions[1]));
        } else {
            event.setVec(new Vec3d(0.0, event.getVec().y, 0.0));
        }
    }
}