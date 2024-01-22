package me.neptune.impl.module.movement.speed;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.BooleanValue;

public class Speed extends Module  {
    public final BooleanValue cc = register(new BooleanValue("CC", false));

    public double speed = 0.0;
    public double distance = 0.0;
    public int stage = 0;
    public boolean boost = false;

    public Speed() {
        super("Speed", Category.MOVEMENT);
        offerListeners(new ListenerMove(this), new ListenerTick(this));
    }

    @Override
    public void onEnable() {
        speed = 0.0;
        distance = 0.0;
        stage = 0;
        boost = false;
    }
}
