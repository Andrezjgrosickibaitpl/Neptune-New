package me.neptune.impl.manager;

import me.neptune.api.event.bus.Listener;
import me.neptune.api.event.bus.SubscriberImpl;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.api.util.math.MathUtil;
import me.neptune.api.util.math.StopWatch;
import me.neptune.impl.events.TickEvent;
import net.minecraft.util.math.Vec3d;

public class SpeedManager extends SubscriberImpl implements Minecraftable {

    private final StopWatch timer = new StopWatch();
    private Vec3d last = new Vec3d(0, 0, 0);
    private double speed = 0.0f;

    public SpeedManager() {
        this.listeners.add(new Listener<>(TickEvent.class) {
            @Override
            public void call(TickEvent event) {
                if (timer.passed(40) && mc.player != null) {
                    speed = MathUtil.distance2D(mc.player.getPos(), last);
                    last = mc.player.getPos();
                    timer.reset();
                }
            }
        });
    }

    public double getSpeed() {
        return getSpeedBpS() * 3.6;
    }

    public double getSpeedBpS() {
        return speed * 20;
    }
}